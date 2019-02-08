package com.nio.server;

import com.nio.handle.Handler;
import com.nio.handle.TransmogrifyChannelHandlerImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This is not ideal way of doing as we are single thread spinning all the time
 *
 */
public class SingleThreadedPollingNonBlockingServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind( new InetSocketAddress( 8080 ) );
        ssc.configureBlocking( false );

        Handler<SocketChannel> handler = new TransmogrifyChannelHandlerImpl();
        Collection<SocketChannel> channelCollection = new ArrayList();

        while (true) {

            SocketChannel socketChannel = ssc.accept(); // may get null
            if (socketChannel != null) {
                channelCollection.add( socketChannel );
                System.out.println( "connected to " + socketChannel );
                socketChannel.configureBlocking( false );
            }

            for (SocketChannel sc: channelCollection
                 ) {
                if(sc.isConnected()){
                    handler.handle( sc );
                }
            }
            channelCollection.removeIf( socket->!socket.isConnected() );

            
        }
    }

}
