package com.my.util.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
	
	private static final CyclicBarrier barrier = new CyclicBarrier(3,
			new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()
							+ "all came let go together");
				}
			});

	public CyclicBarrierTest() {
	}

	private static class Task implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "reached");
			try {
				barrier.await();
				System.out.println(Thread.currentThread().getName()
						+ "crossed the barrier");
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws InterruptedException,
			BrokenBarrierException {
		Thread t1 = new Thread(new Task());
		Thread t2 = new Thread(new Task());
		t1.start();
		t2.start();
		barrier.await();
		// Here we are sure that the count is 0 hence all threads are cam out of
		// wait
		barrier.reset();
		Thread t3 = new Thread(new Task());
		Thread t4 = new Thread(new Task());
		t3.start();
		t4.start();
		barrier.await();
	}

}
