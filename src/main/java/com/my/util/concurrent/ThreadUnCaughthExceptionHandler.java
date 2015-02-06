package com.my.util.concurrent;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;

public class ThreadUnCaughthExceptionHandler {

	public ThreadUnCaughthExceptionHandler() {
		// TODO Auto-generated constructor stub
	}
	
	private static class InnerTask implements Runnable, UncaughtExceptionHandler{
		
		@Override
		public void run() {
			List<String> list = null;
			list.add("null pointer");
		}

		@Override
		public void uncaughtException(Thread t, Throwable e) {
		System.out.println("exception uncaught by thread["+ t.currentThread().getName()+"]"+ e.getMessage());
		}
	}
 
	public static void main(String[] args) throws InterruptedException {
		Thread t =new Thread(new InnerTask());
		t.start();
		Thread.sleep(1000);
	}

}
