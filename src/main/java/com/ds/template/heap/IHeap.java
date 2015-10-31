package com.ds.template.heap;

public interface IHeap<T extends Number > {
	public int getLeftChildIndex(int index);
	
	public int getRightChildIndex(int index);
	
	public int getParentIndex(int index);
	
	public void buildHeap(T[] data);
	
	public void insert(T data);
	
	public Number root();
	
	public Number remove(int index);
	
	public void heapify(int index);
	
	public void display();
	
	public void resize();
	
	public boolean isResizingRequired();
}
