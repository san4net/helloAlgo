package com.ds.template.impls;

import com.ds.template.heap.IHeap;

public abstract class AbstractHeap<T extends Number> implements IHeap<Number> {
	protected T[] elements;
	protected int size;
	protected int capacity;
	
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
		if(i-1< 0) return -1;
		return (i-1)/2;
	}
	
	@Override
	public Number root(){
		return elements[0];
	}
	
	/**
	 * 
	 */
	@Override
	public Number remove(int index) {
		if(index > (size-1) ) throw new IllegalStateException(" wrong index");
		Number elemetTobeRemoved = elements[index];
		elements[index] = elements[size-1];
		elements[size-1] = null;
		// decrease the size
		size--;
		// now we need to call heapify on this
		heapify(index);
		return elemetTobeRemoved;
	}
	
	public void display(){
		System.out.println("******display******");
		int i =0;
		while(i<size){
			System.out.print(elements[i] + " ");
			i++;
		}
	}

}
