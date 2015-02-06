package com.test.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
public static void main(String[] args) {
	try {
		 Registry registry = LocateRegistry.getRegistry("192.168.0.101");
		 Hello stub = (Hello)registry.lookup("Hello");
		 
		 String response = stub.sayHello();
		    System.out.println("response: " + response);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
}
