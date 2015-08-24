package com.ds.template.impls;

public abstract class AbstractHeap<T extends Comparable> implements IHeap< Comparable> {

	@Override
	public int getLeftChildIndex(int i) {
		return i * 2 + 1;
	}
	
	@Override
	public int getRightChildIndex(int i) {
		return i * 2 + 2;
	}
	
	@Override
	public int getParentIndex(int i){
		return (i-1)/2;
	}
	
}
