package org.java;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;

public class ThreadUnCaughthExceptionHandler {

	public ThreadUnCaughthExceptionHandler() {
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
		InnerTask task = new InnerTask();
		Thread t =new Thread(task);
		t.setUncaughtExceptionHandler(task);
		t.start();
		Thread.sleep(1000);
	}

}
