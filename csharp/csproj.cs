using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Imaging;
using System.Numerics;
using System.Runtime.InteropServices;
using System.Text;
using Windows.Graphics.Capture;
using Windows.Graphics.DirectX;
using Windows.Graphics.DirectX.Direct3D11;
using WinRT;                    // For ActivationFactory
using SharpDX;                  // For ComObject, etc.
using SharpDX.Direct3D11;       // For Device, Texture2D
using System.Runtime.InteropServices.WindowsRuntime;
using Device = SharpDX.Direct3D11.Device;

namespace assistant_v3
{
    public static class GraphicsCaptureHelper
    {
        #region Win32 Interop: window enumeration, handle -> GraphicsCaptureItem

        [StructLayout(LayoutKind.Sequential)]
        private struct RECT
        {
            public int Left;
            public int Top;
            public int Right;
            public int Bottom;
        }

        [DllImport("user32.dll", SetLastError = true)]
        private static extern bool EnumWindows(EnumWindowsProc lpEnumFunc, IntPtr lParam);

        private delegate bool EnumWindowsProc(IntPtr hWnd, IntPtr lParam);

        [DllImport("user32.dll", SetLastError = true)]
        private static extern bool IsWindowVisible(IntPtr hWnd);

        [DllImport("user32.dll", SetLastError = true)]
        private static extern int GetWindowText(IntPtr hWnd, StringBuilder text, int maxCount);

        [DllImport("user32.dll", SetLastError = true)]
        private static extern int GetWindowTextLength(IntPtr hWnd);

        [DllImport("user32.dll", SetLastError = true)]
        private static extern bool GetWindowRect(IntPtr hWnd, out RECT rect);

        [DllImport("user32.dll", SetLastError = true)]
        private static extern uint GetWindowThreadProcessId(IntPtr hWnd, out uint processId);

        // The COM interface for creating a GraphicsCaptureItem from an HWND
        [ComImport]
        [InterfaceType(ComInterfaceType.InterfaceIsIUnknown)]
        [Guid("79c3f95b-31f7-4ec2-a464-632ef5d30760")]
        private interface IGraphicsCaptureItemInterop
        {
            GraphicsCaptureItem CreateForWindow(IntPtr hwnd, ref Guid iid);
            GraphicsCaptureItem CreateForMonitor(IntPtr hMonitor, ref Guid iid);
        }

        // Container for enumerated window info
        public class WindowInfo
        {
            public int Id { get; set; }
            public IntPtr Handle { get; set; }
            
            // Provide default empty string to satisfy non-nullable
            public string Title { get; set; } = string.Empty;
            public string ProcessName { get; set; } = string.Empty;

            public Rectangle Bounds { get; set; }
        }

        /// <summary>
        /// Enumerate visible top-level windows and gather info about them.
        /// </summary>
        public static List<WindowInfo> GetAvailableWindows()
        {
            var result = new List<WindowInfo>();
            EnumWindows((hWnd, lParam) =>
            {
                if (!IsWindowVisible(hWnd)) return true;

                int length = GetWindowTextLength(hWnd);
                if (length == 0) return true;

                var sb = new StringBuilder(length + 1);
                GetWindowText(hWnd, sb, sb.Capacity);
                string title = sb.ToString();

                GetWindowThreadProcessId(hWnd, out uint pid);
                string processName = "";
                try
                {
                    var proc = Process.GetProcessById((int)pid);
                    processName = proc.ProcessName;
                }
                catch { }

                if (!GetWindowRect(hWnd, out RECT rect))
                    return true;

                int width = rect.Right - rect.Left;
                int height = rect.Bottom - rect.Top;
                if (width < 50 || height < 50)
                    return true; // skip tiny or minimized windows

                var info = new WindowInfo
                {
                    Id = result.Count,
                    Handle = hWnd,
                    Title = title,
                    ProcessName = processName,
                    Bounds = Rectangle.FromLTRB(rect.Left, rect.Top, rect.Right, rect.Bottom)
                };
                result.Add(info);

                return true;
            }, IntPtr.Zero);

            return result;
        }

        /// <summary>
        /// Creates a GraphicsCaptureItem for a given HWND, using CsWinRT's ActivationFactory.
        /// </summary>
        public static GraphicsCaptureItem CreateCaptureItemForWindow(IntPtr hWnd)
        {
            // This is the CsWinRT approach: ActivationFactory.As<T>
            var factory = ActivationFactory.As<IGraphicsCaptureItemInterop>(typeof(GraphicsCaptureItem));

            // We'll use typeof(GraphicsCaptureItem).GUID as the interface ID:
            var iid = typeof(GraphicsCaptureItem).GUID;

            return factory.CreateForWindow(hWnd, ref iid);
        }

        #endregion

        #region Capture Logic (Frame Pool, Single Frame)

        private static Device? _device;
        private static IDirect3DDevice? _d3dDevice;

        /// <summary>
        /// Lazy-init a SharpDX D3D11 device, then wrap it as an IDirect3DDevice for WinRT APIs.
        /// </summary>
        private static void EnsureDeviceCreated()
        {
            if (_device != null) return;

            // Create a D3D11 device (BGRA support is required for Windows.Graphics.Capture)
            _device = new Device(SharpDX.Direct3D.DriverType.Hardware, DeviceCreationFlags.BgraSupport);

            // Convert that SharpDX device into a WinRT-projected IDirect3DDevice:
            _d3dDevice = CreateDirect3DDeviceFromSharpDX(_device);
        }

        /// <summary>
        /// Instantiate a WinRT Direct3D11Device from a SharpDX device pointer.
        /// </summary>
        private static IDirect3DDevice CreateDirect3DDeviceFromSharpDX(Device device)
        {
            using var dxgiDevice = device.QueryInterface<SharpDX.DXGI.Device>();

            // CsWinRT exposes a public 'Direct3D11Device' type that implements IDirect3DDevice
            // We can create it via its constructor that takes an IntPtr
            return new Direct3D11Device(dxgiDevice.NativePointer);
        }

        /// <summary>
        /// Capture a single frame from the given GraphicsCaptureItem as a System.Drawing.Bitmap.
        /// </summary>
        public static Bitmap? CaptureFrame(GraphicsCaptureItem item)
        {
            EnsureDeviceCreated();
            if (_d3dDevice == null || _device == null)
                return null;

            // Create a frame pool for 1 frame
            using var framePool = Direct3D11CaptureFramePool.Create(
                _d3dDevice,
                DirectXPixelFormat.B8G8R8A8UIntNormalized,
                1,
                item.Size
            );
            using var session = framePool.CreateCaptureSession(item);

            Direct3D11CaptureFrame? frame = null;
            bool frameArrived = false;

            framePool.FrameArrived += (Direct3D11CaptureFramePool sender, object args) =>
            {
                frame = sender.TryGetNextFrame();
                frameArrived = true;
            };

            // Start capture
            session.StartCapture();

            // Wait briefly for a frame
            var sw = Stopwatch.StartNew();
            while (!frameArrived && sw.ElapsedMilliseconds < 2000)
            {
                System.Threading.Thread.Sleep(50);
            }

            // session.Close() doesn’t exist in CsWinRT; call Dispose() instead
            // session.Dispose() is already being called by the 'using' statement above, 
            // but we can do it explicitly if we want:
            // session.Dispose();

            if (frame == null)
                return null;

            return ConvertFrameToBitmap(_device, frame);
        }

        /// <summary>
        /// Convert a single D3D11CaptureFrame to a CPU-based Bitmap (BGRA → ARGB).
        /// </summary>
        private static Bitmap ConvertFrameToBitmap(Device device, Direct3D11CaptureFrame frame)
        {
            // The WinRT frame.Surface is a IDirect3DSurface. We want its underlying D3D11 texture:
            var tex = GetDXGIInterfaceFromObject<Texture2D>(frame.Surface);

            var desc = tex.Description;
            int width = desc.Width;
            int height = desc.Height;

            // Create a CPU staging texture to copy into
            var stagingDesc = new Texture2DDescription
            {
                CpuAccessFlags = CpuAccessFlags.Read,
                BindFlags = BindFlags.None,
                Format = desc.Format,
                Width = width,
                Height = height,
                MipLevels = 1,
                ArraySize = 1,
                SampleDescription = new SharpDX.DXGI.SampleDescription(1, 0),
                Usage = ResourceUsage.Staging,
                OptionFlags = ResourceOptionFlags.None
            };

            using var stagingTex = new Texture2D(device, stagingDesc);
            device.ImmediateContext.CopyResource(tex, stagingTex);

            var dataBox = device.ImmediateContext.MapSubresource(
                stagingTex,
                0,
                MapMode.Read,
                MapFlags.None
            );

            // Create a .NET Bitmap and copy the bytes row by row
            var bmp = new Bitmap(width, height, PixelFormat.Format32bppArgb);
            var bmpData = bmp.LockBits(
                new Rectangle(0, 0, width, height),
                ImageLockMode.WriteOnly,
                PixelFormat.Format32bppArgb
            );

            unsafe
            {
                byte* destPtr = (byte*)bmpData.Scan0;
                byte* srcPtr = (byte*)dataBox.DataPointer;
                int rowBytes = width * 4;

                for (int y = 0; y < height; y++)
                {
                    Buffer.MemoryCopy(srcPtr, destPtr, rowBytes, rowBytes);
                    srcPtr += dataBox.RowPitch;
                    destPtr += bmpData.Stride;
                }
            }

            bmp.UnlockBits(bmpData);
            device.ImmediateContext.UnmapSubresource(stagingTex, 0);

            return bmp;
        }

        /// <summary>
        /// Utility: get a SharpDX COM object from a WinRT IDirect3DSurface/IDirect3DResource.
        /// </summary>
        private static T GetDXGIInterfaceFromObject<T>(object surface) where T : ComObject
        {
            IntPtr pUnknown = Marshal.GetIUnknownForObject(surface);
            try
            {
                return (T)Activator.CreateInstance(typeof(T), pUnknown)!;
            }
            finally
            {
                Marshal.Release(pUnknown);
            }
        }

        #endregion
    }
}
