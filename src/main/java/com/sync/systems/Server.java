package com.sync.systems;

import java.io.IOException;
import java.net.Socket;

import com.sync.systems.message.Message;
import com.sync.systems.message.UserProfile;
/**
 * High level contract for server
 * @author santosh kumar
 *
 */
public interface Server {
	public void connect(Socket socket) throws IOException;
	public void start() throws IOException;
	public void broadCast( UserProfile message) throws IOException;
	public  void handleMessage(Socket socket) throws IOException, ClassNotFoundException;
	public  void send(Socket socket, Message message);
	public void cleanup(Socket socket);
}
