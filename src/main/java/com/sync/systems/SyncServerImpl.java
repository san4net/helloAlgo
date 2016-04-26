package com.sync.systems;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import static com.sync.systems.Utils.*;
public class SyncServerImpl implements SyncServer {
//	INSTANCE(load());
	private ServerProperties config;
	
	public SyncServerImpl(ServerProperties config) {
		this.config = config;
	}

	@Override
	public void start() throws RemoteException{
//		ServerSocket servsock = new ServerSocket(config.getPort(),50, config.getServerIp());
	}

	@Override
	public boolean connect() throws RemoteException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login() throws RemoteException{
		// TODO Auto-generated method stub
		trace("got login request");
		return false;
	}

	@Override
	public boolean logout() throws RemoteException{
		// TODO Auto-generated method stub
		return false;
	}

	private static SyncServer getInstance() {
		return null;
	}
	public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {
		 SyncServer obj = new SyncServerImpl(Utils.load());
		 SyncServer stub = (SyncServer) UnicastRemoteObject.exportObject(obj, 0);
		    // Bind the remote object's stub in the registry
		    Registry registry = LocateRegistry.getRegistry();
		    registry.unbind("SyncServer");
		    registry.bind("SyncServer", stub);
		    System.err.println("Server ready");
	}
}
