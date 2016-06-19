package com.my.util.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class SetupPingPong {

	private  Poison poison =  new Poison(Status.RUN);

	public void triggerRun() {
		AtomicBoolean controller = new AtomicBoolean(true);
		Ping<String> ping = new Ping<String>(controller, poison);
		Pong<String> pong = new Pong<String>(controller, poison);
		ThreadPoolExecutor tp = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		try {
			tp.submit(ping);
			tp.submit(pong);
			Thread.sleep(100);
			poison.setState(Status.STOP);
			System.err.println("********" + poison.getState());
			System.err.println("********");
			tp.shutdown();
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	public static void main(String[] args) {
		new SetupPingPong().triggerRun();
	}

	public static class Poison {
		private volatile Status state;

		public Poison(Status state) {
			super();
			this.state = state;
		}

		public Status getState() {
			return state;
		}

		public void setState(Status state) {
			this.state = state;
		}

	}

	public enum Status {
		RUN, STOP
	}

}
