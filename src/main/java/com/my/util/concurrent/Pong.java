package com.my.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

import com.my.util.concurrent.SetupPingPong.Poison;
import com.my.util.concurrent.SetupPingPong.Status;

public class Pong<V> implements Callable<V> {

	private AtomicBoolean controller;
	private volatile Poison poison;

	public Pong(AtomicBoolean controller, Poison poison) {
		super();
		this.controller = controller;
		this.poison = poison;
	}

	@Override
	public V call() throws Exception {
		while (poison.getState() == Status.RUN) {
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
