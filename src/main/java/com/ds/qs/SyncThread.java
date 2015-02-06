package com.ds.qs;

/**
 * Problem statement:
 * Given four resources A1, A2, B1 , B2. Such that Thread T1 and Thread T2 operates on A1, A2 and B1, B2
 *  respectively. How will you ensure the order of execution is A1-B1-A2-B2 ?
 *
 */
public class SyncThread {

	final Object o = new Object();
	volatile int count;
	volatile boolean isT1started = false;
	final String ResourceA1="A1", ResourceA2 ="A2", ResourceB1="B1", ResourceB2="B2";
	
	// Thread T1
	Runnable T1 = new Runnable() {
		@Override
		public void run() {
		while(count<5){
			synchronized (o) {
				try {
					isT1started = true;
					System.out.println(ResourceA1);
					o.notify();
					o.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			synchronized (o) {
			try {
				System.out.println(ResourceA2);
				o.notify();
				o.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		}
	};
	
	// Thread T2
	Runnable T2 = new Runnable() {
		@Override
		public void run() {
		while(count<5){
			try {
				synchronized (o) {
					System.out.println(ResourceB1);
					o.notify();
					o.wait();
				}

				synchronized (o) {
					System.out.println(ResourceB2);
					System.out.println("============count completed:"+  (++count));
					o.notify();
					o.wait();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
	};
	
	void startThread(){
		int i=1;
		Thread t1 = new Thread(T1);
		Thread t2 = new Thread(T2);
		t1.start();
		// makes sure that Thread t1 is started before t2
		while(!isT1started){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t2.start();
		
		
	}
	
	public static void main(String[] args) {
	new SyncThread().startThread();
	}
}
