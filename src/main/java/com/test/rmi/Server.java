package com.test.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server  implements Hello {
	private static final long serialVersionUID = 1L;

	protected Server() throws RemoteException {
		super();
	}
	/**
	 * 
	 */
	public String sayHello() {
		return "santi";
	}
	public static void main(String[] args) throws RemoteException {
		 Server serverStub = new Server();
		 Hello stub;
		try {
			stub = (Hello) UnicastRemoteObject.exportObject(serverStub, 0);
		    // Bind the remote object's stub in the registry
		    Registry registry = LocateRegistry.getRegistry();
		    registry.rebind("Hello", stub);
		    System.err.println("Server ready");
		    Thread.sleep(20000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("cause: "+e.getCause());
		}
		}

}
