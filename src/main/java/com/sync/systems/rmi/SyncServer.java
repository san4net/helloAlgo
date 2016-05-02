package com.sync.systems.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SyncServer extends Remote {
	public void start()throws RemoteException,AlreadyBoundException;
	public boolean connect()throws RemoteException;
	public boolean login() throws RemoteException;
	public boolean logout()throws RemoteException;
}
