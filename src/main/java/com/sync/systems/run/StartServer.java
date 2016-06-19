package com.sync.systems.run;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sync.systems.impls.SocketServerImpl;

public class StartServer {

	public static void main(String[] args) throws IOException {
		new SocketServerImpl(new InetSocketAddress("localhost", 6555)).start();
		//Runtime.getRuntime().addShutdownHook(hook);
	}
}
