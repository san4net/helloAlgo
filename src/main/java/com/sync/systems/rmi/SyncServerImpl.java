package com.sync.systems.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.sync.systems.Credential;
import com.sync.systems.CredentialManager;
import com.sync.systems.ServerProperties;
import com.sync.systems.Utils;

import static com.sync.systems.Utils.*;
public class SyncServerImpl implements SyncServer {
//	INSTANCE(load());
	private ServerProperties config;
	
	public SyncServerImpl(ServerProperties config) {
		this.config = config;
	}

	@Override
	public void start() throws RemoteException, AlreadyBoundException{
		SyncServer obj = new SyncServerImpl(Utils.load());
	
		 SyncServer stub = (SyncServer) UnicastRemoteObject.exportObject(obj, 1021);
		 Registry registry = LocateRegistry.getRegistry();
		 try {
			registry.unbind("SyncServer");
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 registry.bind("SyncServer", stub);
		 
		 Credential credential = new CredentialManager();
		 Credential credStub = (Credential) UnicastRemoteObject.exportObject(credential, 1022);
		  registry = LocateRegistry.getRegistry();
		 registry.bind("credentialManager", credStub);
		 System.err.println("Server ready");
	}

	@Override
	public boolean connect() throws RemoteException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login() throws RemoteException{
		// TODO Auto-generated method stub
		log("got login request");
		return false;
	}

	@Override
	public boolean logout() throws RemoteException{
		return false;
	}

	private static SyncServer getInstance() {
		return null;
	}
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {
		 SyncServer obj = new SyncServerImpl(Utils.load());
		 obj.start();
		 /*SyncServer stub = (SyncServer) UnicastRemoteObject.exportObject(obj, 0);
		    // Bind the remote object's stub in the registry
		 Registry registry = LocateRegistry.getRegistry();
		 registry.unbind("SyncServer");
		 registry.bind("SyncServer", stub);
		 System.err.println("Server ready");*/
	}
	
}
