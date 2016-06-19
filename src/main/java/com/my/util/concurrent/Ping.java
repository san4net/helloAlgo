package com.my.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

import com.my.util.concurrent.SetupPingPong.Poison;
import com.my.util.concurrent.SetupPingPong.Status;

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
