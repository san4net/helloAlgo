package com.sync.systems;

import static com.sync.systems.Utils.log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import com.sync.systems.message.Message;

public abstract class AbstractBlockingServer {
	protected ExecutorService service = Executors.newCachedThreadPool(new Utils.SyncThreadFactory()); 
	protected ProfileManager profileManager;
	
	public enum State {
		STOPPED, RUNNING;
	}

	private final AtomicReference<State> state = new AtomicReference<State>(State.STOPPED);
	private InetSocketAddress address;
	private ServerSocket server;

	public AbstractBlockingServer(InetSocketAddress address) throws IOException {
		super();
		this.address = address;
		this.server = new ServerSocket();
		this.profileManager = new ProfileManager();
	}

	public void start() throws IOException {
		if (!state.compareAndSet(State.STOPPED, State.RUNNING)) {
			log("server already running");
			return;
		}
		log("server started");
		server.bind(address);
		
		while (state.get() == State.RUNNING) {
			connect(server.accept());
		}
	}
	
	public abstract void connect(Socket accept) throws IOException;
	
	public abstract void incomingMessage(Socket socket) throws IOException, ClassNotFoundException;

	public abstract void send(Socket socket, Message response);
	
	public abstract void handleMessage();
}
