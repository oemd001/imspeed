#include <boost/asio.hpp>
#include <iostream>
#include <winrt/base.h> // For init_apartment
#include "WindowList.h"
#include "CaptureManager.h"
#include "ApiEndpoints.h"

int main()
{
    try
    {
        // Initialize the COM/WinRT environment
        winrt::init_apartment();

        // Create the window enumerator
        WindowList windowList;

        // Create our headless capture manager
        CaptureManager captureManager;

        // Set up Boost.Asio
        boost::asio::io_context ioc;
        boost::asio::ip::tcp::acceptor acceptor(
            ioc,
            boost::asio::ip::tcp::endpoint(boost::asio::ip::make_address("0.0.0.0"), 8080));

        std::cout << "Server listening on 8080...\n";

        for (;;)
        {
            boost::asio::ip::tcp::socket socket(ioc);
            acceptor.accept(socket);

            std::cout << "New connection from "
                << socket.remote_endpoint().address().to_string() << std::endl;

            // Pass references to our capture manager & windowList
            std::make_shared<webserver::ApiEndpoints>(
                std::move(socket),
                captureManager,
                windowList
            )->run();

            // If hooking with WinEventHook in WindowList, pump messages
            MSG msg = {};
            while (PeekMessage(&msg, nullptr, 0, 0, PM_REMOVE))
            {
                TranslateMessage(&msg);
                DispatchMessage(&msg);
            }
        }
    }
    catch (std::exception& e)
    {
        std::cerr << "Main error: " << e.what() << std::endl;
    }

    return 0;
}
