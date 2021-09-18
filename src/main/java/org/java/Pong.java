package org.java;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class Pong<V> implements Callable<V> {

	private AtomicBoolean controller;
	private volatile SetupPingPong.Poison poison;

	public Pong(AtomicBoolean controller, SetupPingPong.Poison poison) {
		super();
		this.controller = controller;
		this.poison = poison;
	}

	@Override
	public V call() throws Exception {
		while (poison.getState() == SetupPingPong.Status.RUN) {
			synchronized (controller) {
				while (controller.get() == true) {
					controller.wait();
				}
				System.out.println("pong");
				controller.set(true);
				controller.notify();
			}
		}
		System.out.println("exiting"+getClass().getSimpleName());
		return null;
	}

}
