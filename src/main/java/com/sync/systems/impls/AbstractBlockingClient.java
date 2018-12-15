package com.sync.systems.impls;

import com.sync.systems.Utils.State;
import com.sync.systems.impls.SocketServerImpl.SocketStreamPair;
import com.sync.systems.message.Message;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class AbstractBlockingClient implements Runnable {
	protected State state = State.RUNNING;
	protected volatile int ERROR_CODE = -1;
	protected String userName;
	protected String password;
	protected Socket socket;
	private String host;
	private int port;
	protected SocketStreamPair socketStreamPair;

	public AbstractBlockingClient(String userName, String password, String host, int port)
			throws IOException, InterruptedException {
		super();
		this.userName = userName;
		this.password = password;
		this.host = host;
		this.port = port;
		connect();
	}
	

	public final synchronized Socket connect() throws IOException, InterruptedException {
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			populateMeta(socket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			ERROR_CODE = 0;
		}
		return socket;
	}

	public void populateMeta(Socket socket) throws IOException {
		socketStreamPair = SocketStreamPair.create(socket);
	}

	public abstract Message take();
	
	public void start() throws UnknownHostException, IOException, InterruptedException {
		connect();
	}

	@Override
	public void run() {
		try {
			Socket socket = connect();
			// send(socket, MsgType.LOGIN);
			/*
			 * Message msg = null; do { handleIncoming(receive(socket)); // do
			 * some processing } while (up); receive(socket);
			 */
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	public abstract void send(Message message) throws IOException;

	public String getUserName() {
		return userName;
	}
	
	public boolean isUp() {
		return state == state.RUNNING;
	}
	
	public void shutDown(){
		state = State.STOPPED;
	}
}
