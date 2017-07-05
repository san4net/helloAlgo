package com.annot;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Field;

public class Singleton extends SingletonParent implements Cloneable, Serializable {
	static int i = 0;
	private static Singleton INSTANCE = new Singleton();
	private int id = 2;

	private Singleton() {
		if (INSTANCE != null) {
			throw new IllegalStateException("Inside JavaSingleton(): JavaSingleton " + "instance already created.");
		}

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
	protected Singleton clone() throws CloneNotSupportedException {
		return this;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchFieldException, SecurityException, CloneNotSupportedException, IOException {
//		testReflection( Singleton.getInstance());
		testSerialization( Singleton.getInstance());
	}

	public static void testReflection(Singleton ins)
			throws InstantiationException, IllegalAccessException, CloneNotSupportedException {
		System.out.println("reflection");
		Singleton two = Singleton.class.newInstance();
		System.out.println(ins == two);
		System.out.println("reflection");
		Singleton three = (Singleton) two.clone();
		System.out.println(two == three);
	}

	public static void testSerialization(Singleton ins) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
		ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
		oos.writeObject(ins);
		oos.close();
		ins.id = 5;
		System.out.println(ins);

		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(is);
		Singleton deserialized = (Singleton) ois.readObject();
		System.out.println(deserialized);

	}

	@Override
	public String toString() {
		return "Singleton [id=" + id + "]";
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();
		INSTANCE = this;
	}

	private Object readResolve() throws ObjectStreamException {
		return INSTANCE;
	}
}
