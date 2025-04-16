#include "ApiEndpoints.h"
#include <boost/asio.hpp>
#include <boost/beast.hpp>
#include <iostream>
// If you need JSON:
#include "include/json.hpp"

#include "CaptureManager.h"
#include "WindowList.h"
#include "pch.h" // if you have PCH

namespace webserver
{
    namespace beast = boost::beast;
    namespace http = beast::http;
    namespace net = boost::asio;

    // --------------------------------------
    // Constructor
    // --------------------------------------
    ApiEndpoints::ApiEndpoints(net::ip::tcp::socket socket,
        CaptureManager& captureManager,
        WindowList& windowList)
        : m_socket(std::move(socket))
        , m_captureManager(captureManager)
        , m_windowList(windowList)
    {
    }

    // --------------------------------------
    // run: entry point
    // --------------------------------------
    void ApiEndpoints::run()
    {
        doRead();
    }

    // --------------------------------------
    // doRead: start an async_read
    // --------------------------------------
    void ApiEndpoints::doRead()
    {
        auto self = shared_from_this();
        m_buffer.consume(m_buffer.size());

        // Make a shared pointer to hold the request
        auto reqPtr = std::make_shared<http::request<http::string_body>>();

        // Read directly into *reqPtr
        http::async_read(
            m_socket,
            m_buffer,
            *reqPtr,
            [this, self, reqPtr](beast::error_code ec, std::size_t bytes_transferred)
            {
                // Once read completes, call onRead
                // We pass in reqPtr so 'onRead' can handle it
                onRead(ec, bytes_transferred, reqPtr);
            }
        );
    }

    // --------------------------------------
    // onRead: invoked after async_read
    // --------------------------------------
    void ApiEndpoints::onRead(
        beast::error_code ec,
        std::size_t bytes_transferred,
        std::shared_ptr<http::request<http::string_body>> reqPtr)
    {
        if (ec == http::error::end_of_stream)
        {
            // Graceful close
            beast::error_code ignored;
            m_socket.shutdown(net::ip::tcp::socket::shutdown_send, ignored);
            return;
        }
        if (ec)
        {
            std::cerr << "[ApiEndpoints] Read error: " << ec.message() << std::endl;
            return;
        }

        // We have a complete request in *reqPtr
        handleRequest(std::move(*reqPtr));

        // If not a WebSocket session, go read again (keep serving HTTP requests)
        if (!m_isWebSocketSession)
        {
            doRead();
        }
    }

    // --------------------------------------
    // handleRequest
    // --------------------------------------
    void ApiEndpoints::handleRequest(http::request<http::string_body>&& req)
    {
        std::string path = std::string(req.target());
        auto qpos = path.find('?');
        if (qpos != std::string::npos) path.resize(qpos);

        // Simple routing
        if (req.method() == http::verb::get && path == "/windows")
        {
            auto resp = handleGetWindows();
            http::write(m_socket, resp);
        }
        else if (req.method() == http::verb::post
            && path.rfind("/windows/", 0) == 0
            && path.find("/capture") != std::string::npos)
        {
            auto sub = path.substr(strlen("/windows/"));
            auto slashPos = sub.find('/');
            auto idStr = sub.substr(0, slashPos);
            uint64_t windowId = std::stoull(idStr);

            auto resp = handleStartCapture(windowId);
            http::write(m_socket, resp);
        }
        else if (req.method() == http::verb::get
            && path.rfind("/windows/", 0) == 0
            && path.find("/stream") != std::string::npos)
        {
            auto sub = path.substr(strlen("/windows/"));
            auto slashPos = sub.find('/');
            auto idStr = sub.substr(0, slashPos);
            uint64_t windowId = std::stoull(idStr);

            handleWebSocketUpgrade(std::move(req), windowId);
        }
        else
        {
            http::response<http::string_body> resp(http::status::not_found, req.version());
            resp.set(http::field::content_type, "text/plain");
            resp.body() = "Not Found";
            resp.prepare_payload();
            http::write(m_socket, resp);
        }
    }

    // --------------------------------------
    // handleGetWindows
    // --------------------------------------
    http::response<http::string_body> ApiEndpoints::handleGetWindows()
    {
        auto wList = m_windowList.GetWindows();  // was "allWindows"

        nlohmann::json jarr = nlohmann::json::array();
        for (const auto& item : wList) 
        {
            nlohmann::json obj;
            obj["id"] = item.Id;
            // Convert std::wstring -> UTF-8
            std::string titleUtf8(item.Title.begin(), item.Title.end());
            obj["title"] = titleUtf8;

            jarr.push_back(obj);
        }

        http::response<http::string_body> res(http::status::ok, 11);
        res.set(http::field::content_type, "application/json");
        res.body() = jarr.dump();
        res.prepare_payload();
        return res;
    }



    // --------------------------------------
    // handleStartCapture
    // --------------------------------------
    http::response<http::string_body> ApiEndpoints::handleStartCapture(uint64_t windowId)
    {
        HWND hwnd = m_windowList.GetHwndFromId(windowId);
        if (!hwnd)
        {
            http::response<http::string_body> res(http::status::bad_request, 11);
            res.body() = "Invalid window ID";
            res.prepare_payload();
            return res;
        }

        bool success = m_captureManager.StartCaptureFromHwnd(hwnd);
        if (!success)
        {
            http::response<http::string_body> res(http::status::internal_server_error, 11);
            res.body() = "Failed to start capture.";
            res.prepare_payload();
            return res;
        }

        http::response<http::string_body> res(http::status::ok, 11);
        res.body() = "Capture started.";
        res.prepare_payload();
        return res;
    }

    // --------------------------------------
    // handleWebSocketUpgrade
    // --------------------------------------
    void ApiEndpoints::handleWebSocketUpgrade(http::request<http::string_body>&& req,
        uint64_t windowId)
    {
        beast::websocket::stream<net::ip::tcp::socket> ws(std::move(m_socket));
        ws.set_option(beast::websocket::stream_base::timeout::suggested(beast::role_type::server));

        beast::error_code ec;
        ws.accept(req, ec);
        if (ec)
        {
            std::cerr << "[ApiEndpoints] WebSocket accept error: " << ec.message() << std::endl;
            return;
        }

        m_isWebSocketSession = true;
        runWebSocket(windowId, std::move(ws));
    }

    // --------------------------------------
    // runWebSocket
    // --------------------------------------
    void ApiEndpoints::runWebSocket(uint64_t windowId,
        beast::websocket::stream<net::ip::tcp::socket> ws)
    {
        // (A) Attach a callback for new frames
        m_captureManager.SetFrameCallback(
            [&, this](const std::vector<uint8_t>& encodedFrame)
            {
                try
                {
                    ws.binary(true);
                    ws.write(net::buffer(encodedFrame));
                }
                catch (const beast::system_error& se)
                {
                    if (se.code() != beast::websocket::error::closed)
                    {
                        std::cerr << "[ApiEndpoints] WS write error: " << se.code().message() << std::endl;
                    }
                }
                catch (const std::exception& ex)
                {
                    std::cerr << "[ApiEndpoints] WS exception: " << ex.what() << std::endl;
                }
            }
        );

        // (B) Block reading from client until they disconnect or error
        try
        {
            for (;;)
            {
                beast::flat_buffer buffer;
                ws.read(buffer); // blocking read
                // Optionally parse messages in `buffer`
            }
        }
        catch (const beast::system_error& se)
        {
            if (se.code() != beast::websocket::error::closed)
            {
                std::cerr << "[ApiEndpoints] WS read error: " << se.code().message() << std::endl;
            }
        }

        // (C) Clean up
        m_captureManager.SetFrameCallback(nullptr);
        m_isWebSocketSession = false;
    }

} // namespace webserver
