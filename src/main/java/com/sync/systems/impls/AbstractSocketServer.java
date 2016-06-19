package com.sync.systems.impls;

import static com.sync.systems.Utils.log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import com.sync.systems.ProfileManager;
import com.sync.systems.Server;
import com.sync.systems.Utils;
import com.sync.systems.Utils.State;
import com.sync.systems.Utils.SyncThreadFactory;
import com.sync.systems.impls.SocketServerImpl.SocketStreamPair;
import com.sync.systems.message.Login;
import com.sync.systems.message.Message;
import com.sync.systems.message.UserProfile;

public abstract class AbstractSocketServer  implements Server{
	protected ExecutorService service = Executors.newCachedThreadPool(new Utils.SyncThreadFactory());
	protected ProfileManager profileManager;
	protected Map<String, List<Socket>> userSockets = new ConcurrentHashMap<>();
	protected Map<Socket, SocketStreamPair> socketStream = new ConcurrentHashMap<>();
	private final AtomicReference<State> state = new AtomicReference<State>(State.STOPPED);
	private InetSocketAddress address;
	private ServerSocket server;

	public AbstractSocketServer(InetSocketAddress address) throws IOException {
		super();
		this.address = address;
		this.server = new ServerSocket();
		this.profileManager = new ProfileManager();
	}
	
	@Override
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
 
	@Override
	public void connect(Socket socket) throws IOException {
		log("new connection");
		// start new thread
		service.submit(new NewSocketHandler(socket));
	}
	@Override
	public void broadCast( UserProfile message) throws IOException {
		log("broadcast");
		for (Socket socket : userSockets.get(message.getUser())) {
			socketStream.get(socket).getOutputStream().writeObject(message);
		}
	}
	
	protected synchronized void addUserSockets(Login message, Socket socket){
		List<Socket> sockets = userSockets.get(message.getUser());
		if(sockets == null){
			userSockets.put(message.getUser(), sockets = new ArrayList<>());
		}
		sockets.add(socket);
	}
	
	private void addSocketStream(Socket socket) throws IOException{
		socketStream.put(socket, SocketStreamPair.create(socket));
	}
	
	private void removeSocketStream(Socket socket){
		socketStream.remove(socket);
	}
	
	private void removeUserSockets(Login message){
		userSockets.remove(message.getUser());
	}
	
	@Override
	public abstract void handleMessage(Socket socket) throws IOException, ClassNotFoundException;
	@Override
	public abstract void send(Socket socket, Message message);
	
	@Override
	public void cleanup(Socket socket){
		socketStream.remove(socket);
	}
	
	private class NewSocketHandler implements Runnable {
		private Socket socket;

		public NewSocketHandler(Socket socket) {
			super();
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				addSocketStream(socket);
				handleMessage(socket);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				cleanup(socket);
				log("thread exiting");
			}
		}
		
	}
}
