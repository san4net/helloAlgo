package com.annot;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.List;

public class Singleton implements Cloneable {
	static int i = 0;
	private static Singleton INSTANCE = new Singleton();

	private Singleton() {
	/*	if (INSTANCE != null) {
			throw new IllegalStateException(
					"Inside JavaSingleton(): JavaSingleton "
							+ "instance already created.");
		}*/
		System.out.format("Inside {%s}: Singleton instance is being created.", this.getClass().getSimpleName());
		System.out.println();
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

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Singleton ins = Singleton.getInstance();
		Field field = ins.getClass().getDeclaredField("INSTANCE");
		field.setAccessible(false);
		Singleton two = Singleton.class.newInstance();
		
		System.out.println("reflection");
		List<? super Number> number = null;
		List<Integer> integer = null;
		
	}
}
