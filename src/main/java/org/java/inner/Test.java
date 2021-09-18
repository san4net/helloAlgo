package org.java.inner;

import java.util.HashMap;


public class Test extends Thread {

	public static void main(String[] args) throws InterruptedException {
	
	   HashMap map = new HashMap();
	  ClassLoader loader =  map.getClass().getClassLoader();
	  System.out.println(loader);
	  
	   
	}

	
}
