package com.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class PCManager {
	Integer[] inventory = {0};
	
	ExecutorService service = Executors.newFixedThreadPool(2);
	
	/**
	 * 
	 * @param p
	 * @param c
	 * @throws InterruptedException 
	 */
	public void kickStartPC(/*CookTheProducer<String> p, BaiterTheConsumer<String> c*/) throws InterruptedException {
//		String[] inventory = new String[0];
		CookTheProducer<String>  cook = new CookTheProducer<String>(inventory);
		
		BaiterTheConsumer<String>  baiter = new BaiterTheConsumer<String>(inventory);
		
	    Future<String> cookF = service.submit(cook);
	    
		Future<String>baiterF = service.submit(baiter);
		
		try {
			System.out.println(" future cook:" +cookF.get());
			System.out.println(" future baiter:" +baiterF.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		service.shutdown();
		
//		service.awaitTermination(2, TimeUnit.MINUTES);
	}
	
	public static void main(String[] args) {
		try {
			new PCManager().kickStartPC();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
