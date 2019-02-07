package com.nio.server;

import com.nio.handle.Handler;
import com.nio.handle.PrintingHandlerImpl;
import com.nio.handle.ThreadedHandlerImpl;
import com.nio.handle.TransmogrifyHandlerImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);

        Handler<Socket> handler = new ThreadedHandlerImpl( new PrintingHandlerImpl<>( new TransmogrifyHandlerImpl() ));

        while (true) {
            final Socket socket = serverSocket.accept();
            handler.handle( socket );
        }
    }

}
