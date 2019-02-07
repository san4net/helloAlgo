package com.nio.server;

import com.nio.handle.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class ExecutoServiceServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket( 8080 );

        Handler<Socket> handler = new ExecutorServiceHandlerImpl<>( new PrintingHandlerImpl<>( new TransmogrifyHandlerImpl() )
                ,
                Executors.newFixedThreadPool( 10 ) ,
                (t , e) -> System.out.println( e )
        );

        while (true) {
            System.out.println( "server started" );
            final Socket socket = serverSocket.accept();
            handler.handle( socket );
        }
    }

}
