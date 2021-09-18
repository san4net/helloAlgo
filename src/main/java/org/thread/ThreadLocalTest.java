package org.thread;

public class ThreadLocalTest {

	public static void main(String[] args) {
		
		for(int i=0; i<4;i++){
			Thread t = new Thread(new NameTask());
			t.start();
		}
	
	}
}

class NameTask implements Runnable{

	@Override
	public void run() {
		System.out.println(PerThreadValues.get());
	}
	
}

class PerThreadValues {
	private static int count = 1;
	private static final ThreadLocal<String> stringNameHolder = new ThreadLocal<String>() {
		/*
		 * initialValue() is called
		 */
		protected String initialValue() {
			System.out.println("called for" + Thread.currentThread());
			return "string" + count++;
		};
	};

	public static String get() {
		return stringNameHolder.get();
	}
}
