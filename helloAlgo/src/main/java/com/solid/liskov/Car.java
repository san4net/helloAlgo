package com.solid.liskov;

public class Car implements Vehicle {

	@Override
	public boolean speed(boolean increase, int delta) {
		String msg = increase?"increase by:" : "decrese by:";
		System.out.println(msg+delta);
		return false;
	}

	@Override
	public void addFuel(int liters) {
		// TODO Auto-generated method stub

	}

}
