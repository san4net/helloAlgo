package com.nio.handle;

import com.nio.ServerSupport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TransmogrifyHandlerImpl implements Handler<Socket> {

    @Override
    public void handle(Socket socket) throws IOException {
        try (
             socket; // java9
             OutputStream outputStream = socket.getOutputStream();
             InputStream inputStream = socket.getInputStream();) {
            int data;
            while ((data = inputStream.read()) != -1) {

                //if(data == '%') throw new IOException( "oopies intentional" );
                outputStream.write( ServerSupport.transmogrify(data));
            }
         } catch (IOException ioe){
            System.out.println(this.getClass().getSimpleName() + " expection"+ioe);
        }
    }

//    @Override
    public void handle(SocketChannel socketChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate( 80 );
        int read = socketChannel.read( byteBuffer );

        if (read < 0) {
            socketChannel.close();
            return;
        }

        ServerSupport.transmogrify( byteBuffer );
        while (byteBuffer.hasRemaining()) {
            socketChannel.write( byteBuffer );

        }

    }
}
