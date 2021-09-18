package org.java;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

import org.java.SetupPingPong.Poison;
import org.java.SetupPingPong.Status;

public class Ping<V> implements Callable<V> {
	private AtomicBoolean controller;
	private  Poison poison;

	public Ping(AtomicBoolean controller, Poison poison) {
		super();
		this.controller = controller;
		this.poison = poison;
	}

	@Override
	public V call() throws Exception {
		while (poison.getState() == Status.RUN) {
			synchronized (controller) {
				while (controller.get() == false) {
					controller.wait(1000);
				}
				System.out.println("ping");
				
				controller.set(false);
				controller.notify();
			}

		}
		System.out.println("exiting"+getClass().getSimpleName());
		return null;
	}

}
