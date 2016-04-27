package com.sync.systems;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Utils {
	public static String TITLE = "SyncServer v1.0";
	static enum LEVEL {
		INFO, ERRO;
	}
	
	public static void trace(String msg){
		trace(LEVEL.INFO, msg);
	}
	
	public static void trace(Throwable t){
		trace(LEVEL.ERRO, t.getMessage());
	}
	
	public static void trace(LEVEL level, String msg) {
		if (LEVEL.INFO == level) {
			System.out.println(msg);
		} else if (LEVEL.ERRO == level) {
			System.err.println(msg);
		}
	}
	
	public static ServerProperties load(){
		 ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		 InputStream is = classloader.getResourceAsStream("config.properties");
		 ServerProperties defaultProps = new ServerProperties();
		 try {
			defaultProps.load(is);
		} catch (IOException e) {
			trace(e);
		}
		 return  defaultProps;
	}
	
	public static void main(String[] args) {
	load();	
	}
	
	public static SyncServer getServerStub() throws RemoteException, NotBoundException{
		Registry registry = LocateRegistry.getRegistry();
		SyncServer stub = (SyncServer) registry.lookup("SyncServer");
		return stub;
	}
	
	public static Credential getCredentialStub() throws RemoteException, NotBoundException{
		Registry registry = LocateRegistry.getRegistry();
		Credential stub = (Credential) registry.lookup("credentialManager");
		return stub;
	}
	
	
	public static boolean authenticate(String userName, String password) throws RemoteException, NotBoundException{
		return true;
//		return getCredentialStub().isAuthorized(userName, password);
	}
}
