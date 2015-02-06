package com.my.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class Ping<V> implements Callable<V> {

	private  AtomicBoolean controller ;
//	private  Boolean controller ;
	
	
	/*public Ping(Boolean controller) {
		super();
		this.controller = controller;
	}*/

	public Ping(AtomicBoolean controller) {
	super();
	this.controller = controller;
}


	@Override
	public V call() throws Exception {
		while (true) {
			
		synchronized (controller) {
				while (controller.get() == false) {
					controller.notify();
					controller.wait();
				}
				System.out.println("PING");
				controller.set(false);
//				controller.notify();
			}
		
		 /*System.out.println("running ping");
			synchronized (controller) {
				while (controller == false) {
					controller.notify();
					controller.wait();
				}
				System.out.println("ping");
				controller = (false);
				controller.notify();
			}*/
			
		}
	}

}
