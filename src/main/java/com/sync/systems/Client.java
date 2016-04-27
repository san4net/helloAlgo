package com.sync.systems;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	private Client() {
	}

	public static void main(String[] args) {

		// String host = (args.length < 1) ? null : args[0];
		try {
			Registry registry = LocateRegistry.getRegistry();
			SyncServer stub = (SyncServer) registry.lookup("SyncServer");
			boolean response = stub.login();
			System.out.println("response: " + response);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
	private void login() throws RemoteException, NotBoundException{
		Registry registry = LocateRegistry.getRegistry();
		SyncServer stub = (SyncServer) registry.lookup("SyncServer");
		boolean response = stub.login();
		System.out.println("response: " + response);
		
	}
	
}
