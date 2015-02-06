package com.core;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.corba.se.impl.orbutil.StackImpl;

public class Setup {
ExecutorService service = Executors.newFixedThreadPool(2);
 
private void kickStart(){
	
	final Stack<Integer> s = new com.ds.impls.StackImpl<>(2);
    
	Callable<String> pusher = new Callable<String>() {
		
		@Override
		public String call() throws Exception {
			int r = new Random().nextInt(10);
			s.push(r);
			return "push";
		}
	};
	
	Callable<String> popper = new Callable<String>() {
		
		@Override
		public String call() throws Exception {
			s.pop();
			
			return "popping";
		}
	};
	
	service.submit(pusher);
	service.submit(popper);
}

public static void main(String[] args) {
	new Setup().kickStart();
}
}
