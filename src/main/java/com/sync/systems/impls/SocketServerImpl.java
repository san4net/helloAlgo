package com.sync.systems.impls;

import static com.sync.systems.Utils.log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.sync.systems.CredentialManager;
import com.sync.systems.Profile;
import com.sync.systems.Profile.Location;
import com.sync.systems.SyncNotifier;
import com.sync.systems.message.FailedLogin;
import com.sync.systems.message.Login;
import com.sync.systems.message.Message;
import com.sync.systems.message.ProfileRequest;
import com.sync.systems.message.ProfileUpdate;
import com.sync.systems.message.SuccesfullLogin;
import com.sync.systems.message.SyncConfirmReq;
import com.sync.systems.message.SyncConfirmResponse;
import com.sync.systems.message.UserProfile;
import com.sync.systems.transfer.InMemroyCopy;

public class SocketServerImpl extends AbstractSocketServer implements SyncNotifier {

	public SocketServerImpl(InetSocketAddress address) throws IOException {
		super(address);
		profileManager.addSyncNotifier(this);
	}

	@Override
	public void handleMessage(Socket socket) throws IOException, ClassNotFoundException {
		Message response = handleMessageInternal(socket, takeMessage(socket));
		send(socket, response);
		// wait for further messages
		handleMessage(socket);
	}

	private Message takeMessage(Socket socket) throws IOException, ClassNotFoundException {
		return (Message) socketStream.get(socket).getInputStream().readObject();
		// wait for further messages
	}

	@Override
	public void send(Socket socket, Message message) {
		log("sending response" + message);
		try {
			if (message != null) {
				socketStream.get(socket).getOutputStream().writeObject(message);
			}
		} catch (IOException e) {
			log(e.getMessage());
		}
	}

	private Message handleMessageInternal(Socket socket, Message message) throws IOException {
		Message response = null;
		switch (message.getType()) {
		case LOGIN:
			response = onLogon(socket, (Login) message);
			break;
		case PROFILE_REQ:
			response = onProfileRequest((ProfileRequest) message);
			break;
		case PROFILE_UPDATE:
			UserProfile msg = onProfileUpdate((ProfileUpdate) message);
			broadCast(msg);
			break;
		case SYNC_CONFIRM_RESPONSE:
			onSyncConfirm((SyncConfirmResponse) message);
			log("blabla");
		case LOGOUT:

		default:
			break;
		}
		return response;
	}
	
	private List<Future<String>> copyStatusFuture = new ArrayList<>();
	
	private void onSyncConfirm(SyncConfirmResponse message) {
		preprocessPreviousRun();
		List<CopyTask> copies = copyTasks(message);
		ignore(destination(message));  
		for(CopyTask task : copies){
			 copyStatusFuture.add(service.submit(task));
		}
	}
	
	
	private List<CopyTask> copyTasks(SyncConfirmResponse message){
	    List<CopyTask> copyTask = new ArrayList<>();
	    String destinations[] = destination(message);
	    for(String destination : destinations){
	    		copyTask.add(new CopyTask(message.getSrc(), destination));
	    }
		
	    return copyTask;
	}
	
	private void preprocessPreviousRun(){
		try {
			updateNoIgnore(getPreviousCopy());
		} catch (InterruptedException | ExecutionException e) {
			log(e.getMessage());
		}
	}

	private List<String> getPreviousCopy() throws InterruptedException, ExecutionException{
		List<String> doneList = new ArrayList<>();
		for(Future<String> f : copyStatusFuture){
			doneList.add(f.get());
		}
		return doneList;
	}
	
	private void ignore(String[] locations){
		for(String location: locations){
			profileManager.ignore(location);
		}
	}
	
	private void updateNoIgnore(List<String> locations){
		for(String location: locations){
			profileManager.Noignore(location);
		}
	}
	private String[] destination(SyncConfirmResponse message){
		Profile p = profileManager.getOrCreate(message.getUser());
		String[] dest = new String[p.getLocations().size()-1];
		if(p!= null){
			int i = 0;
			for(Location location : p.getLocations()){
				if(location.getLocation().equals(message.getSrc())){
					continue;
				}
				dest[i++] = location.getLocation();
			}
		}
		return dest;
	}

	private Message onLogon(Socket socket, Login message) throws RemoteException {
		addUserSockets(message, socket);
		if (CredentialManager.getInstance().isAuthorized(message.getUser(), message.getPassword())) {
			return SuccesfullLogin.createMsg(message);
		}
		return FailedLogin.create(message);
	}

	private Message onProfileRequest(ProfileRequest message) {
		return UserProfile.create(profileManager.getOrCreate(message.getUser()));
	}

	private UserProfile onProfileUpdate(ProfileUpdate message) {
		profileManager.update(message);
		return UserProfile.create(profileManager.getOrCreate(message.getUser()));
	}

	class CopyTask implements Callable<String> {
		String src;
		String dest;

		public CopyTask(String src, String dest) {
			super();
			this.src = src;
			this.dest = dest;
		}
		@Override
		public String call() {
					try {
						new InMemroyCopy<>(src, dest).copy();
					} catch (Exception e) {
						log(e.getMessage());
					}
					return dest;
		}
	}

	public static void main(String[] args) throws IOException {
		new SocketServerImpl(new InetSocketAddress("localhost", 6555)).start();
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
				log(e.getMessage());
			} finally {
			}
		}
	}

	@Override
	public void onUpdate(String user, String location) {
		// send message
		Message message = SyncConfirmReq.create("updated", location);
		List<Socket> socket = userSockets.get(user);
		send(socket.get(0), message);
	}
}
