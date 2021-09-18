package org.thread;

import java.util.concurrent.Callable;

public class BaiterTheConsumer<V> implements Callable<V> {
   Integer[] itemsToServe ;
		   
	public BaiterTheConsumer(Integer[] TakeAwayPlace) {
		itemsToServe = TakeAwayPlace;
	}
	int count =0;
	@Override
	public V call() throws Exception {
		while(count<5){
		consume();
		count++;
		}
	  return (V) "baiterdone"	;
	}
   
	private V consume() {
		int count=0;
		synchronized (itemsToServe) {
				if (0 ==(itemsToServe[0])) {
					System.out.println("no item avail waiter waiting");
					try {
						itemsToServe.notify();
						itemsToServe.wait();
						System.out.println("consumer notified ");
						if (!( 0 == itemsToServe[0])) {

							System.out.println("consuming item"+ itemsToServe[0]);
							itemsToServe[0] = 0;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("consuming " + itemsToServe[0]);
					itemsToServe[0] = 0;
					itemsToServe.notifyAll();
				}
		}
		return (V) "";
	}
}
