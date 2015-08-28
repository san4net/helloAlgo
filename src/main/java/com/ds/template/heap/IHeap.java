package com.ds.template.heap;

public interface IHeap<T extends Comparable > {
	public int getLeftChildIndex(int index);
	
	public int getRightChildIndex(int index);
	
	public int getParentIndex(int index);
	
	public void buildHeap(T[] data);
	
	public void insert(T data);
	
	public Comparable root();
	
	public Comparable remove(int index);
	
	public void heapify(int index);
}
