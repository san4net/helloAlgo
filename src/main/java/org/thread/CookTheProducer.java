package org.thread;

import java.util.concurrent.Callable;

public class CookTheProducer<V> implements Callable<V> {
   Integer[] items;
   int count =0;
	@Override
	public V call() throws Exception {
		while(count<5){
			produce();
			count++;
		}
		return (V) "cookDone";
	}
	
	
	public CookTheProducer(Integer[] items) {
	this.items = items;
	}
	
	private static int menuCount = 1;
	
	private V produce(){
			synchronized (items) {
				if (!(0 ==(items[0]))) {
					try {
						System.out.println("no place for item : producer waiting ");
						items.notifyAll();
						items.wait();
						System.out.println("producer notified");
						if (0 ==(items[0])) {
							items[0] =  menuCount++;
							System.out.println("producing menu: " + items[0]);
							items.notifyAll();
//							return (V) items[0];
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					items[0] =  menuCount++;
					System.out.println("producing menu: " + items[0]);
					items.notifyAll();
//					return (V) items[0];
				}
			}
		return (V) "";
	}

}
