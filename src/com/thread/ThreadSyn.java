package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSyn {
	private MyObject flag;
	private final AtomicInteger count = new AtomicInteger(0);

	public ThreadSyn(MyObject flag) {
		super();
		this.flag = flag;
	}

	public static void main(String[] args) {
		ThreadSyn instance = new ThreadSyn(new ThreadSyn.MyObject(
				"one".intern()));

		Runnable taskOne = instance.new Task("one".intern());
		Runnable taskTwo = instance.new Task("two".intern());
		Runnable taskThree = instance.new Task("three".intern());

		ExecutorService service = Executors.newFixedThreadPool(3);
		service.submit(taskOne);
		service.submit(taskTwo);
		service.submit(taskThree);

	}

	static class MyObject {
		String indicator = "one";

		public MyObject(String indicator) {
			this.indicator = indicator;
		}

		public String getIndicator() {
			return indicator;
		}

		public void setIndicator(String indicator) {
			this.indicator = indicator;
		}
	}

	class Task implements Runnable {
		String id;

		public Task(String id) {
			super();
			this.id = id;
		}

		@Override
		public void run() {
			while (count.intValue() < 9) {
				synchronized (flag) {
					while (!flag.getIndicator().equals(id)) {
						try {
							System.out.println(id
									+ " || going to wait state counter:"
									+ count.intValue());
							flag.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("its me==" + id);
					count.incrementAndGet();
					if (id.equals("one")) {
						flag.setIndicator("two");
					} else if (id.equals("two")) {
						flag.setIndicator("three");
					} else if (id.equals("three")) {
						flag.setIndicator("one");
					}
					flag.notifyAll();
				}

			}
		}

	}

}
