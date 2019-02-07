package com.nio.server;

import com.nio.handle.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;

import static java.nio.channels.SocketChannel.*;

public class BlockingNIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind( new InetSocketAddress( 8080 ) );

        Handler<SocketChannel> handler =
                new ExecutorServiceHandlerImpl<>(
                        new PrintingHandlerImpl<>(
                                new BlockingSocketChannelHandler(
                                        new TransmogrifyChannelHandlerImpl() )

                        ) ,
                        Executors.newFixedThreadPool( 10 )
                );

        while (true) {
            System.out.println( "server started" );
            final SocketChannel socket = ssc.accept();
            handler.handle( socket );
        }
    }

}
