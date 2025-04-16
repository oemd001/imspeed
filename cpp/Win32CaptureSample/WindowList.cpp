#include "pch.h"
#include "WindowList.h"

// Any other headers you need for DwmGetWindowAttribute, etc.
#include <dwmapi.h>
#include <stdexcept>

#pragma comment(lib, "dwmapi.lib")

// Helper functions
bool inline MatchTitleAndClassName(WindowInfo const& window,
    std::wstring const& title,
    std::wstring const& className)
{
    return wcscmp(window.Title.c_str(), title.c_str()) == 0 &&
        wcscmp(window.ClassName.c_str(), className.c_str()) == 0;
}

bool IsKnownBlockedWindow(WindowInfo const& window)
{
    return
        // Task View
        MatchTitleAndClassName(window, L"Task View", L"Windows.UI.Core.CoreWindow") ||
        // XAML Islands
        MatchTitleAndClassName(window, L"DesktopWindowXamlSource", L"Windows.UI.Core.CoreWindow") ||
        // XAML Popups
        MatchTitleAndClassName(window, L"PopupHost", L"Xaml_WindowedPopupClass");
}

bool IsCapturableWindow(WindowInfo const& window)
{
    // Filter out invisible or shell windows
    if (window.Title.empty() ||
        window.WindowHandle == GetShellWindow() ||
        !IsWindowVisible(window.WindowHandle) ||
        GetAncestor(window.WindowHandle, GA_ROOT) != window.WindowHandle)
    {
        return false;
    }

    // Check style
    auto style = GetWindowLongW(window.WindowHandle, GWL_STYLE);
    if (style & WS_DISABLED) return false;

    auto exStyle = GetWindowLongW(window.WindowHandle, GWL_EXSTYLE);
    if (exStyle & WS_EX_TOOLWINDOW) // e.g. tooltips, menus
    {
        return false;
    }

    // Check if it's cloaked (for UWP apps, etc.)
    if (wcscmp(window.ClassName.c_str(), L"Windows.UI.Core.CoreWindow") == 0 ||
        wcscmp(window.ClassName.c_str(), L"ApplicationFrameWindow") == 0)
    {
        DWORD cloaked = FALSE;
        if (SUCCEEDED(DwmGetWindowAttribute(window.WindowHandle, DWMWA_CLOAKED,
            &cloaked, sizeof(cloaked))) &&
            (cloaked == DWM_CLOAKED_SHELL))
        {
            return false;
        }
    }

    if (IsKnownBlockedWindow(window))
    {
        return false;
    }

    return true;
}

// A thread-local pointer so the WinEventHook callback can access this WindowList
static thread_local WindowList* WindowListForThread = nullptr;

WindowList::WindowList()
{
    // Guard: only one WindowList per thread
    if (WindowListForThread)
    {
        throw std::runtime_error("WindowList already exists for this thread!");
    }
    WindowListForThread = this;

    // Initial enumeration of windows
    EnumWindows([](HWND hwnd, LPARAM lParam)
        {
            if (GetWindowTextLengthW(hwnd) > 0)
            {
                WindowInfo window(hwnd);
                if (!IsCapturableWindow(window))
                {
                    return TRUE;
                }

                auto windowList = reinterpret_cast<WindowList*>(lParam);
                windowList->AddWindow(window);
            }
            return TRUE;
        }, reinterpret_cast<LPARAM>(this));

    // Hook events: if windows are shown/destroyed, update dynamically
    // This allows the list to be kept up to date
    m_eventHook.reset(SetWinEventHook(
        EVENT_OBJECT_DESTROY, /*EVENT_OBJECT_SHOW*/ EVENT_OBJECT_UNCLOAKED,
        nullptr,
        [](HWINEVENTHOOK eventHook, DWORD event, HWND hwnd,
            LONG objectId, LONG childId, DWORD eventThreadId,
            DWORD eventTimeInMilliseconds)
        {
            // check the global pointer
            if (!WindowListForThread) return;

            if (event == EVENT_OBJECT_DESTROY && childId == CHILDID_SELF)
            {
                WindowListForThread->RemoveWindow(WindowInfo(hwnd));
                return;
            }

            if (objectId == OBJID_WINDOW && childId == CHILDID_SELF && hwnd != nullptr &&
                GetAncestor(hwnd, GA_ROOT) == hwnd &&
                GetWindowTextLengthW(hwnd) > 0 &&
                (event == EVENT_OBJECT_SHOW || event == EVENT_OBJECT_UNCLOAKED))
            {
                WindowInfo window(hwnd);
                if (IsCapturableWindow(window))
                {
                    WindowListForThread->AddWindow(window);
                }
            }
        },
        0, 0, WINEVENT_OUTOFCONTEXT));
}

WindowList::~WindowList()
{
    m_eventHook.reset();
    WindowListForThread = nullptr;
}

// Register a combo box to show the windows
void WindowList::RegisterComboBoxForUpdates(HWND comboBoxHandle)
{
    m_comboBoxes.push_back(comboBoxHandle);
    ForceUpdateComboBox(comboBoxHandle);
}

// Unregister a combo box
void WindowList::UnregisterComboBox(HWND comboBoxHandle)
{
    m_comboBoxes.erase(
        std::remove(m_comboBoxes.begin(), m_comboBoxes.end(), comboBoxHandle),
        m_comboBoxes.end());
}

// Add a new window if not seen before
void WindowList::AddWindow(WindowInfo const& info)
{
    auto search = m_seenWindows.find(info.WindowHandle);
    if (search == m_seenWindows.end())
    {
        // Make a copy so we can assign an ID
        WindowInfo newWindow = info;
        newWindow.Id = m_nextId++;  // unique ID for this window

        // store
        m_windows.push_back(newWindow);
        m_seenWindows.insert(newWindow.WindowHandle);

        // Update combo boxes
        for (auto& comboBox : m_comboBoxes)
        {
            SendMessageW(comboBox, CB_ADDSTRING, 0,
                (LPARAM)newWindow.Title.c_str());
        }
    }
}

// Remove a window if known
bool WindowList::RemoveWindow(WindowInfo const& info)
{
    auto search = m_seenWindows.find(info.WindowHandle);
    if (search != m_seenWindows.end())
    {
        m_seenWindows.erase(search);

        // find & remove from m_windows
        auto index = 0UL;
        for (; index < m_windows.size(); ++index)
        {
            if (m_windows[index].WindowHandle == info.WindowHandle)
            {
                break;
            }
        }

        if (index < m_windows.size())
        {
            m_windows.erase(m_windows.begin() + index);

            // Update comboBoxes
            for (auto& comboBox : m_comboBoxes)
            {
                SendMessageW(comboBox, CB_DELETESTRING, (WPARAM)index, 0);
            }
        }
        return true;
    }
    return false;
}

// Re-populate a combo box with our current list of windows
void WindowList::ForceUpdateComboBox(HWND comboBoxHandle)
{
    SendMessageW(comboBoxHandle, CB_RESETCONTENT, 0, 0);
    for (auto& window : m_windows)
    {
        SendMessageW(comboBoxHandle, CB_ADDSTRING, 0,
            (LPARAM)window.Title.c_str());
    }
}

// Return the entire list
std::vector<WindowInfo> WindowList::GetWindows() const
{
    return m_windows; // or return a copy if you prefer
}

// Find an HWND by ID
HWND WindowList::GetHwndFromId(uint64_t id)
{
    for (auto const& w : m_windows)
    {
        if (w.Id == id)
        {
            return w.WindowHandle;
        }
    }
    return nullptr; // not found
}
