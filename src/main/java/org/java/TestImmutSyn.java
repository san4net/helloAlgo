package org.java;

public class TestImmutSyn {
	Boolean immutBool = new Boolean(true);
	public void testSync() throws InterruptedException{
		int i=0;
		while(i<2)
		{
			synchronized (immutBool) {
				System.out.println("boo-" + immutBool);
				{
					System.err.println("making bool false");

					immutBool = false;
					synchronized (immutBool) {
						immutBool.notify();
					}
					Thread.sleep(1000);
				}
				i++;
			}
	}
	}
		
public void testImut(){
		Boolean a =  new Boolean(true);
		Boolean b = a;
		System.out.println("a"+a+"\t -b"+b);
		a= false;
		System.out.println("a"+a+"\t -b"+b);
	}

	public static void main(String[] args) throws InterruptedException {
		TestImmutSyn imsy = new TestImmutSyn();
		imsy.testSync();
	}
}
