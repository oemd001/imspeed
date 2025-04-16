#pragma once
#include <vector>
#include <unordered_set>
#include <algorithm>
#include <windows.h>
#include <wil/resource.h>
#include <stdexcept>

// A simple struct holding window info: handle, title, class, and a unique ID.
struct WindowInfo
{
    WindowInfo(HWND hwnd)
    {
        WindowHandle = hwnd;

        // Fetch the window title
        int length = GetWindowTextLengthW(WindowHandle);
        if (length > 0) length++; // for null terminator
        std::wstring title(length, L'\0');
        GetWindowTextW(WindowHandle, title.data(), length);
        Title = title;

        // Fetch the class name
        const int classMax = 256;
        std::wstring className(classMax, L'\0');
        GetClassNameW(WindowHandle, className.data(), classMax);
        ClassName = className;
    }

    HWND WindowHandle = nullptr;
    std::wstring Title;
    std::wstring ClassName;
    uint64_t Id = 0; // Unique ID assigned by WindowList

    bool operator==(const WindowInfo& other) const { return WindowHandle == other.WindowHandle; }
    bool operator!=(const WindowInfo& other) const { return !(*this == other); }
};

class WindowList
{
public:
    WindowList();
    ~WindowList();

    // Called if you want to show these in a combo box, etc.
    void RegisterComboBoxForUpdates(HWND comboBoxHandle);
    void UnregisterComboBox(HWND comboBoxHandle);

    // Returns the current list of known windows
    std::vector<WindowInfo> GetWindows() const;

    // Looks up an HWND by the assigned ID
    HWND GetHwndFromId(uint64_t id);

private:
    // Internal helpers
    void AddWindow(WindowInfo const& info);
    bool RemoveWindow(WindowInfo const& info);
    void ForceUpdateComboBox(HWND comboBoxHandle);

private:
    std::vector<HWND> m_comboBoxes;         // any combo boxes we update
    std::vector<WindowInfo> m_windows;      // list of known windows
    std::unordered_set<HWND> m_seenWindows; // track which handles we've seen
    wil::unique_hwineventhook m_eventHook;  // for WinEventHook

    uint64_t m_nextId = 1;  // Next ID to assign to newly found windows
};
