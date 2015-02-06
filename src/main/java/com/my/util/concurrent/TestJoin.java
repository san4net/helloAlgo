package com.my.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class TestJoin {

	private static class FirstThread implements Runnable{

		@Override
		public void run() {
			System.out.println("first starting");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("first ending");
		}
		
	}
	
	private static class SecondThread implements Runnable{

		@Override
		public void run() {
			System.out.println("second starting");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("second ending");
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
	
	}
}
