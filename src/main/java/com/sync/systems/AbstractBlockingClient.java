package com.sync.systems;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.sync.systems.BlockingServer.SocketStreamPair;
import com.sync.systems.message.Message;
import com.sync.systems.message.Message.MsgType;

import static com.sync.systems.Utils.*;

public abstract class AbstractBlockingClient implements Runnable {

	protected volatile int ERROR_CODE = -1;
	private volatile boolean up = true;
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

	// public abstract void send(Socket socket, MsgType type) throws
	// IOException;

	public abstract Message take();

	public void handleIncoming(Message message) {
		switch (message.getType()) {
		case FAILEDLOGIN:
			log("loging out");
			ERROR_CODE = 0;
			break;

		default:
			break;
		}
	}

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
	
}
