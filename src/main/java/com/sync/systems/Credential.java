package com.sync.systems;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Credential extends Remote{
	boolean isAuthorized(String username, String password) throws RemoteException;
}
