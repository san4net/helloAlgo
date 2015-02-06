package com.my.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class Pong<V> implements Callable<V> {

//	private  Boolean controller ;
	private AtomicBoolean controller;
	
	/*public Pong(Boolean controller) {
		super();
		this.controller = controller;
	}*/
	
	public Pong(AtomicBoolean controller) {
		super();
		this.controller = controller;
	}

	@Override
	public V call() throws Exception {
		while(true){
		synchronized (controller) {
		while(true){
				while(controller.get()==true){
					controller.notify();
					controller.wait();
				}
				System.out.println("pong");
				controller.set(true);
//				controller.notify();
			}
		
			

					/*while(controller == true){
						controller.notify();
						controller.wait();
					}
					System.out.println("pong");
					controller = true;
					controller.notify(); */
				}
			
		}
	}

}
