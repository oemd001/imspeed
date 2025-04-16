#pragma once
#include <memory>
#include <functional>
#include <vector>
#include <cstdint>
#include <windows.h>

// Forward-declare your SimpleCapture class.
// Assume it has: 
//   - a constructor taking (GraphicsCaptureItem, maybe a device), 
//   - StartCapture(), Close(), 
//   - an OnFrameArrived(...) callback for new frames.
class SimpleCapture;

// This callback signature will be called whenever a new encoded frame is ready.
// If you prefer raw BGRA data, you can change it accordingly.
using FrameArrivedCallback = std::function<void(const std::vector<uint8_t>& encodedFrame)>;

class CaptureManager
{
public:
    CaptureManager();
    ~CaptureManager();

    // Start capturing a given window by HWND
    bool StartCaptureFromHwnd(HWND hwnd);

    // Stop any active capture
    void StopCapture();

    // Register a callback to receive new frame data (e.g., for streaming)
    void SetFrameCallback(FrameArrivedCallback callback);

private:
    std::unique_ptr<SimpleCapture> m_capture;     // Holds the active capture session
    FrameArrivedCallback m_onFrameArrived;        // Callback for new frames
};
