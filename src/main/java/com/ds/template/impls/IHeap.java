package com.ds.template.impls;

public interface IHeap<T extends Comparable > {
	public int getLeftChildIndex(int index);
	
	public int getRightChildIndex(int index);
	
	public int getParentIndex(int index);
	
	public void buildHeap(T[] data);
	
}
