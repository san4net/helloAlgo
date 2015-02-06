package com.me.ds.template.begining;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class Producer<V> implements Callable<V> {
	Object buffer;
	CountDownLatch latch;

	public Producer(Object buffer, CountDownLatch count) {
		super();
		this.buffer = buffer;
		this.latch = count;
	}

	public void produce() {
		synchronized (buffer) {
			System.out.println("producing");
			latch.countDown();
			buffer.notifyAll();
			Thread.yield();
		}
	}

	public V startProducer() throws InterruptedException {
		while (latch.getCount() != 0) {
			produce();
			Thread.sleep(1000);
		}
		return (V) "producer ended";
	}

	public static void main(String[] args) throws InterruptedException {
		Object buffer = new Object();
		CountDownLatch count = new CountDownLatch(10);
		Producer<String> producer = new Producer(buffer, count);
		Consumer<String> con = new Consumer(buffer, count);
		@SuppressWarnings("rawtypes")
		Thread t1 = new Thread(new RunnableAdapter(con));
		Thread t2 = new Thread(new RunnableAdapter(producer));

		t1.start();
		t2.start();
	}

	@Override
	public V call() throws Exception {
		return startProducer();
	}

}

class RunnableAdapter implements Runnable {
	Callable c;

	public RunnableAdapter(Callable adapt) {
		c = adapt;
	}

	@Override
	public void run() {
		try {
			c.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
