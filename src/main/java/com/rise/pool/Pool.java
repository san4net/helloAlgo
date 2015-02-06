package com.rise.pool;

public interface Pool {
	boolean start() throws NotInitialized;
	boolean shutdown() throws ShutDownException;
}
