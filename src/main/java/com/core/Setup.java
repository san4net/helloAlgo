package com.core;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ds.template.Stack;

public class Setup {
	ExecutorService service = Executors.newFixedThreadPool(2);
	private volatile boolean run = true;
	private void kickStart() {

		final Stack<Integer> s = new com.ds.impls.StackImpl<>(2);

		Callable<String> pusher = new Callable<String>() {
			@Override
			public String call() throws Exception {
				while (run) {
					int r = new Random().nextInt(10);
					s.push(r);
				}
				System.out.println("exiting" + Thread.currentThread());
				return "push";
			}
		};

		Callable<String> popper = new Callable<String>() {
			@Override
			public String call() throws Exception {
				while (run) {
					s.pop();
				}
				System.out.println("exiting" + Thread.currentThread());
				return "popping";
			}
		};

		service.submit(pusher);
		service.submit(popper);
		System.err.println("exiting kick");
	}
	
	private void stop(){
		run = false;
//		service.shutdown();
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		 Setup s = new Setup();
		 System.out.println(Thread.currentThread());
		 s.kickStart();
//		 Thread.sleep(1000);
		 s.stop();
		 System.out.println("Main exiting");
	}
	
}
