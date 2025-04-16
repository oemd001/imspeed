#pragma once
#include <boost/asio.hpp>
#include <boost/beast.hpp>
#include <memory>
#include <string>
#include "CaptureManager.h"
#include "WindowList.h"

namespace webserver
{
    namespace beast = boost::beast;
    namespace http = beast::http;

    class ApiEndpoints : public std::enable_shared_from_this<ApiEndpoints>
    {
    public:
        ApiEndpoints(boost::asio::ip::tcp::socket socket,
            CaptureManager& captureManager,
            WindowList& windowList);

        // Begin handling the connection (entry point)
        void run();

    private:
        // 1) Internal read loop
        void doRead();

        // 2) Callback once async_read finishes
        //    NOTE: no "ApiEndpoints::" here, just 'void onRead(...)'
        void onRead(beast::error_code ec,
            std::size_t bytes_transferred,
            std::shared_ptr<http::request<http::string_body>> reqPtr);

        // 3) Route dispatch
        void handleRequest(http::request<http::string_body>&& req);

        // Endpoint handlers
        boost::beast::http::response<boost::beast::http::string_body> handleGetWindows();
        boost::beast::http::response<boost::beast::http::string_body> handleStartCapture(uint64_t windowId);

        // WebSocket
        void handleWebSocketUpgrade(boost::beast::http::request<boost::beast::http::string_body>&& req,
            uint64_t windowId);
        void runWebSocket(uint64_t windowId,
            beast::websocket::stream<boost::asio::ip::tcp::socket> ws);

    private:
        boost::asio::ip::tcp::socket m_socket;
        beast::flat_buffer m_buffer;

        CaptureManager& m_captureManager;
        WindowList& m_windowList;
        bool m_isWebSocketSession{ false };
    };
}
