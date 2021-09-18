package org.inheritance;

public class D extends B implements C {

	@Override
	public void inC() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avoidMultipleInheritance() {
		// TODO Auto-generated method stub
		System.out.println("D");
	}
	
	public static void main(String[] args) {
		B d = new D();
		d.avoidMultipleInheritance();
	}
}
