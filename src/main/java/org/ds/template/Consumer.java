package org.ds.template;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class Consumer<V> implements Callable<V>{
	Object buffer;
	CountDownLatch lacth;
	public Consumer(Object buffer, CountDownLatch count) {
		super();
		this.buffer = buffer;
		this.lacth = count;
	}

	public void consume() {
		synchronized (buffer) {
			System.out.println("consuming");
			lacth.countDown();
			buffer.notifyAll();
			Thread.yield();
		}
	}

	public V startConsuming() throws InterruptedException{
		while(lacth.getCount()!= 0){
			consume();
			Thread.sleep(1000);
		
		}
		return (V)"consumed";
	}

	@Override
	public V call() throws Exception {
		return startConsuming();
	}
}
