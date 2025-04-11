import base64
import io
import time
import clr  # pythonnet
from flask import Flask, jsonify, Response

# Load your compiled DLL:
clr.AddReference("MyGraphicsCaptureLib")

# Now import from that assembly
from MyGraphicsCaptureLib import GraphicsCaptureHelper

app = Flask(__name__)

# We'll store a "cached" list of windows after calling GetAvailableWindows
windows_cache = []

@app.route("/windows", methods=["GET"])
def list_windows():
    global windows_cache
    windows_cache = GraphicsCaptureHelper.GetAvailableWindows()
    results = []
    for w in windows_cache:
        results.append({
            "id": w.Id,
            "title": w.Title,
            "processName": w.ProcessName,
            "x": w.Bounds.X,
            "y": w.Bounds.Y,
            "width": w.Bounds.Width,
            "height": w.Bounds.Height
        })
    return jsonify(results)


@app.route("/screenshot/<int:window_id>", methods=["GET"])
def screenshot_window(window_id):
    """
    Take a *single* screenshot from Windows.Graphics.Capture for the given window ID
    and return it as a PNG base64.
    """
    global windows_cache
    if not windows_cache:
        windows_cache = GraphicsCaptureHelper.GetAvailableWindows()

    target = None
    for w in windows_cache:
        if w.Id == window_id:
            target = w
            break
    if not target:
        return "Window ID not found", 404

    # Create the capture item
    item = GraphicsCaptureHelper.CreateCaptureItemForWindow(target.Handle)
    if item is None:
        return "Could not create capture item", 400

    # Capture a single frame
    bmp = GraphicsCaptureHelper.CaptureFrame(item)
    if bmp is None:
        return "Failed to capture frame", 500

    # Convert to base64 PNG
    with io.BytesIO() as output:
        # We need to access the .NET ImageFormat.Png
        # or just use the System.Drawing wrapper
        from System.Drawing.Imaging import ImageFormat
        bmp.Save(output, ImageFormat.Png)
        data = output.getvalue()
    b64 = base64.b64encode(data).decode('utf-8')

    return jsonify({"base64png": b64})


@app.route("/stream/<int:window_id>", methods=["GET"])
def stream_window(window_id):
    """
    Rudimentary "multipart/x-mixed-replace" streaming of repeated frames.
    Absolutely NOT recommended for high-FPS usage in production,
    but shows the approach for a live feed over HTTP (MJPEG style).
    """
    global windows_cache
    if not windows_cache:
        windows_cache = GraphicsCaptureHelper.GetAvailableWindows()

    target = None
    for w in windows_cache:
        if w.Id == window_id:
            target = w
            break
    if not target:
        return "Window ID not found", 404

    def generate():
        while True:
            # We do the same single-frame capture repeatedly
            item = GraphicsCaptureHelper.CreateCaptureItemForWindow(target.Handle)
            bmp = GraphicsCaptureHelper.CaptureFrame(item)
            if bmp is None:
                # Just yield empty
                yield (b"--frame\r\nContent-Type: image/jpeg\r\n\r\n\r\n")
                time.sleep(0.5)
                continue

            # Convert to JPEG bytes in-memory
            from System.Drawing.Imaging import ImageFormat
            with io.BytesIO() as output:
                bmp.Save(output, ImageFormat.Jpeg)
                data = output.getvalue()

            yield (b"--frame\r\n"
                   b"Content-Type: image/jpeg\r\n\r\n" + data + b"\r\n")

            time.sleep(0.2)  # Sleep briefly to reduce CPU hogging

    return Response(generate(),
                    mimetype="multipart/x-mixed-replace; boundary=frame")


if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5000, debug=True)
