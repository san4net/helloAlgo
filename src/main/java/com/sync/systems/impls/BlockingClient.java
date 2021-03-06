package com.sync.systems.impls;

import static com.sync.systems.Utils.PORT;
import static com.sync.systems.Utils.SYSTEM_NAME;
import static com.sync.systems.Utils.log;

import java.io.IOException;
import java.net.UnknownHostException;

import com.sync.systems.message.Login;
import com.sync.systems.message.Message;
import com.sync.systems.message.ProfileRequest;
import com.sync.systems.message.UserProfile;

public class BlockingClient extends AbstractBlockingClient {
	
	public BlockingClient(String userName, String password, String host, int port)
			throws UnknownHostException, IOException, InterruptedException {
		super(userName, password, host, port);
	}


	@Override
	public void send(Message message) throws IOException {
		log("sending:"+ message);
		socketStreamPair.getOutputStream().writeObject(message);
		log("done");
	}

	@Override
	public Message take() {
		log("client taking response");
		Message message = null;
		try {
			message = (Message) socketStreamPair.getInputStream().readObject();
			log("client" + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	// this is like handsake
	public boolean validateLogin() {
		Message message = null;
		try {
			send(Login.createMessage(userName, password));
			message = take();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Message.MessageType.SUCCESFULLOGIN == message.getType();
	}

	public static BlockingClient create(String user, String password) {
		try {
			return new BlockingClient(user, password, SYSTEM_NAME, PORT);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public UserProfile getProfile() throws IOException {
		// request profile to server
		// send profile message 
		 send(ProfileRequest.create(userName));
		 return (UserProfile) take();
	}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		new Thread(new BlockingClient("san", "dd", "localhost", 6555)).start();
	}
}
