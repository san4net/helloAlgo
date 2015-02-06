package com.my.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class SetupPingPong {
//	ThreadPoolExecutor tp = (ThreadPoolExecutor) Executors
//			.newFixedThreadPool(2);
	

	SetupPingPong() {

	}



	public static void main(String[] args) {

		SetupPingPong pingpong = new SetupPingPong();
		AtomicBoolean controller =  new AtomicBoolean(true);
//		Boolean controller = new Boolean(true);
//		Object contrlString = new String("ping");
		Pong<String> pong = new Pong<String>(controller);
		Ping<String> ping = new Ping<String>(controller);
		ThreadPoolExecutor tp = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(2);
		try {
			tp.submit(ping);
		} /*catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		finally{
			
		}
		tp.submit(pong);
		
		// Callable pingcall = new SetupPingPong.Ping<"Ping">(controller);

	}
}
