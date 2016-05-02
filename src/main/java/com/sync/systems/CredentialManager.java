package com.sync.systems;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * Credential manager per server
 * 
 * @author santosh kumar
 *
 */
public class CredentialManager implements Credential {
	private static CredentialManager manager = new CredentialManager();
	
	public CredentialManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Map<String, String> credential;
	static {
		credential = new HashMap<>();
		credential.put("san", "");
	}
	
	@Override
	public boolean isAuthorized(String username, String password) throws RemoteException{
		return password.equals(credential.get(username));
	}
	
	public static CredentialManager getInstance(){
		return manager;
	}
}
