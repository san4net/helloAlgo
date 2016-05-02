package com.sync.systems;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.sync.systems.rmi.SyncServer;

public class Utils {
	public static final InetSocketAddress server = new InetSocketAddress("localhost", 6555);
	public static final int PORT = 6555;
	public static final String SYSTEM_NAME = "localhost";
    private static String[] errors = {"wrong username", "server not availiable"};
	
	public static String TITLE = "SyncClient v1.0";
	static enum LEVEL {
		INFO, ERRO;
	}
	
	public static void log(String msg){
		trace(LEVEL.INFO, msg);
	}
	
	public static void log(Throwable t){
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
			log(e);
		}
		 return  defaultProps;
	}
	
	public static void main(String[] args) throws SocketException, UnknownHostException {
//	load();	
		System.out.println(getMacAddress());		
Thread t = new SyncThreadFactory().newThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
System.out.println(t);
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
	
	public static byte[] getMacAddress() throws SocketException, UnknownHostException{
		NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
		System.out.println(InetAddress.getLocalHost().getHostName());
		return  network.getHardwareAddress();
		
//		System.out.println(mac);
	}
	
	public static void log(ByteBuffer message, SelectionKey key) throws IOException{
		SocketChannel channel = (SocketChannel) key.channel();
		int size = channel.read(message);
		if(size !=-1){
			byte[] data = new byte[size];
	        System.arraycopy(message.array(), 0, data, 0, size);
	        System.out.println("Got: " + new String(data));
		}else {
			System.out.println("closed connection");
		}
	}
	
	 public static class SyncThreadFactory implements ThreadFactory {
	        private final AtomicInteger threadNumber = new AtomicInteger(1);
	        private final String namePrefix;

	        public SyncThreadFactory() {
	            namePrefix = "pool-sync -thread-";
	        }

	        public Thread newThread(Runnable r) {
	            Thread t = new Thread(null, r,
	                                  namePrefix + threadNumber.getAndIncrement(),
	                                  0);
	            if (t.isDaemon())
	                t.setDaemon(false);
	            if (t.getPriority() != Thread.NORM_PRIORITY)
	                t.setPriority(Thread.NORM_PRIORITY);
	            return t;
	        }
	    }
	 
	
	 
}
