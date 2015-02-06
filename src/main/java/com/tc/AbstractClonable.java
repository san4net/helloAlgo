package com.tc;

public abstract class AbstractClonable {
public abstract Object clone();
}

class DeepClone extends AbstractClonable{

	@Override
	public Object clone() {
		DeepClone dc = new DeepClone();
		return dc;
	}
	
}

class ShallowClone extends AbstractClonable{

	@Override
	public Object clone() {
		return this;
	}
	
	@Override
	public boolean equals(Object sc){
		return true;
	}
	
}