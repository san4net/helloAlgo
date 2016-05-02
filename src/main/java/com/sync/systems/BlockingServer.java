package com.sync.systems;

import static com.sync.systems.Utils.log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sync.systems.message.FailedLogin;
import com.sync.systems.message.Login;
import com.sync.systems.message.Message;
import com.sync.systems.message.ProfileRequest;
import com.sync.systems.message.ProfileUpdate;
import com.sync.systems.message.SuccesfullLogin;
import com.sync.systems.message.Success;
import com.sync.systems.message.UserProfile;

public class BlockingServer extends AbstractBlockingServer {
	private Map<Socket, SocketStreamPair> socketStream = new ConcurrentHashMap<>();

	public BlockingServer(InetSocketAddress address) throws IOException {
		super(address);
	}

	@Override
	public void connect(Socket socket) throws IOException {
		log("new connection");
		// start new thread
		service.submit(new NewSocketHandler(socket));
	}

	@Override
	public void incomingMessage(Socket socket) throws IOException, ClassNotFoundException {
		Message response = handleMessage(socket, takeMessage(socket));
		send(socket, response);
		// wait for further messages
		incomingMessage(socket);
	}

	private Message takeMessage(Socket socket) throws IOException, ClassNotFoundException {
		return (Message) socketStream.get(socket).getInputStream().readObject();
		// wait for further messages
	}

	@Override
	public void send(Socket socket, Message response) {
		log("sending response" + response);
		try {
			socketStream.get(socket).getOutputStream().writeObject(response);
		} catch (IOException e) {
			log(e.getMessage());
		}
	}

	private Message handleMessage(Socket socket, Message message) throws IOException {
		Message response = null;
		switch (message.getType()) {
		case LOGIN:
			response = onLogon(socket, message);
			/*
			 * // send response and wait send(socket, response);
			 */
			break;
		case PROFILE_REQUEST:
			response = onProfileRequest((ProfileRequest) message);
			break;
		case PROFILE_UPDATE:
			onProfileUpdate((ProfileUpdate) message);
			break;
		case LOGOUT:
		default:
			break;
		}
		return response;
	}

	private Message onLogon(Socket socket, Message message) throws RemoteException {
		Login login = (Login) message;
		if (CredentialManager.getInstance().isAuthorized(login.getUser(), login.getPassword())) {
			return SuccesfullLogin.createMsg(login);
		}
		return FailedLogin.create(login);
		// log(login.toString());
	}

	private Message onProfileRequest(ProfileRequest message) {
		return UserProfile.create(profileManager.get(message.getUser()));
	}

	private Message onProfileUpdate(ProfileUpdate message) {
		profileManager.update(message);
		return Success.create();
	}

	private static class UserPerMachine {
		String user;
		InetAddress InetAddress;

		public UserPerMachine(String user, InetAddress inetAddress) {
			super();
			this.user = user;
			InetAddress = inetAddress;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((InetAddress == null) ? 0 : InetAddress.hashCode());
			result = prime * result + ((user == null) ? 0 : user.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserPerMachine other = (UserPerMachine) obj;
			if (InetAddress == null) {
				if (other.InetAddress != null)
					return false;
			} else if (!InetAddress.equals(other.InetAddress))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			return true;
		}

	}

	public static void main(String[] args) throws IOException {
		new BlockingServer(new InetSocketAddress("localhost", 6555)).start();
	}

	@Override
	public void handleMessage() {

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
				socketStream.put(socket, SocketStreamPair.create(socket));
				incomingMessage(socket);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				log("thread exiting");
			}
		}

	}

	public static class SocketStreamPair {
		private ObjectOutputStream outputStream;
		private ObjectInputStream inputStream;

		public SocketStreamPair(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
			super();
			this.outputStream = outputStream;
			this.inputStream = inputStream;
		}

		public static SocketStreamPair create(Socket socket) throws IOException {
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			return new SocketStreamPair(outputStream, inputStream);
		}

		public ObjectOutputStream getOutputStream() {
			return outputStream;
		}

		public ObjectInputStream getInputStream() {
			return inputStream;
		}

		public void close() {
			try {
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
