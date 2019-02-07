package com.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void display(String msg) {
        System.out.println( msg );
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress host = InetAddress.getLocalHost();
        display( "starting" );
        try (Socket socket = new Socket( host.getHostName() , 8080 );
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()
             ) {

            display( "Sending request to Socket Server" +socket);

            for (int i = 0; i < 5; i++) {

                if (i == 2) {
                    display( "sending" + "%" );
                    outputStream.write( "%".getBytes() );
                } else {
                    display( "sending " + "hello" );
                    outputStream.write( "hello".getBytes() );
                }

                Thread.sleep( 1_000 );
                int data;
                if ((data = inputStream.read()) != -1) {
                    System.out.println( "got from server " + data );
                }
                Thread.sleep( 1_000 );
            }

        } finally {
            System.out.println( "client disconnected" );
        }


    }

}
