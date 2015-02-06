package com.annot;

import java.util.List;

public class Singleton implements Cloneable {
	static int i = 0;
	private static Singleton INSTANCE = new Singleton();

	private Singleton() {
		if (INSTANCE != null) {
			throw new IllegalStateException(
					"Inside JavaSingleton(): JavaSingleton "
							+ "instance already created.");
		}
		System.out
				.println("Inside JavaSingleton(): Singleton instance is being created.");
		i++;
	}

	public static final Singleton getInstance() {
		if (INSTANCE == null) {
			synchronized (Singleton.class) {
				if (INSTANCE == null) {
					INSTANCE = new Singleton();
				}
			}
		}
		return INSTANCE;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Singleton ins = Singleton.getInstance();
		Singleton two = Singleton.class.newInstance();
		
		System.out.println("reflection");
		List<? super Number> number = null;
		List<Integer> integer = null;
		
	}
}
