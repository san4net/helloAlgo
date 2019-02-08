package com.nio.server;

import com.google.common.collect.Iterables;
import com.nio.handle.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * This is not ideal way of doing as we are single thread spinning all the time
 *
 */
public class SingleThreadedSelectorNonBlockingServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind( new InetSocketAddress( 8080 ) );
        ssc.configureBlocking( false );
        Selector selector = Selector.open();
        ssc.register( selector, SelectionKey.OP_ACCEPT );
        Map<SocketChannel, Queue<ByteBuffer>> pendingData = new HashMap<>(  );
        Handler<SelectionKey> acceptHandler = new AcceptHandler(pendingData);
        Handler<SelectionKey> readtHandler = new ReadHandler(pendingData);
        Handler<SelectionKey> writetHandler = new WriteHandler(pendingData);


        while (true) {
            selector.select();
            Collection<SelectionKey> keys = selector.selectedKeys();

            for(Iterator<SelectionKey> iterator = keys.iterator(); ((Iterator) iterator).hasNext();){
                SelectionKey selectionKey =  iterator.next();
                iterator.remove();
                if(selectionKey.isValid()){
                    if(selectionKey.isAcceptable()){
                        acceptHandler.handle( selectionKey );
                    }else if(selectionKey.isReadable()){
                        readtHandler.handle( selectionKey );
                    }else if(selectionKey.isWritable()){
                        writetHandler.handle( selectionKey );
                    }
                }
            }
            
        }
    }

}
