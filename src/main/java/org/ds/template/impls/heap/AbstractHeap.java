package org.ds.template.impls.heap;

import java.util.Arrays;

import org.ds.template.heap.IHeap;

public abstract class AbstractHeap<T extends Number> implements IHeap<Number> {
	protected T[] elements;
	private int size;
	private int capacity;

	public void resize() {
		capacity *= 2;
		elements = Arrays.copyOf(elements, capacity * 2);
	}
	
	@Override
	public int getLeftChildIndex(int i) {
		return i * 2 + 1;
	}

	@Override
	public int getRightChildIndex(int i) {
		return i * 2 + 2;
	}

	/**
	 * Parent Index 
	 * 
	 */
	@Override
	public int getParentIndex(int i) {
		if (i - 1 < 0)
			return -1;
		return (i - 1) / 2;
	}

	@Override
	public Number root() {
		return elements[0];
	}

	/**
	 * 
	 */
	@Override
	public Number remove(int index) {
		if (index > (size - 1))
			throw new IllegalStateException(" wrong index");
		Number elemetTobeRemoved = elements[index];
		elements[index] = elements[size - 1];
		elements[size - 1] = null;
		// decrease the size
		size--;
		// now we need to call heapify on this
		heapify(index);
		return elemetTobeRemoved;
	}

	public void display() {
		System.out.println("******display******");
		int i = 0;
		while (i < size) {
			System.out.print(elements[i] + " ");
			i++;
		}
	}

	public abstract void heapify(int index);

	/**
	 * For building heap we need to start from last parent index till root
	 *  and heapify all parent indices
	 * 
	 */
	@Override
	public void buildHeap(Number[] data) {
		initialize(data);
		int lastParentIndex = getParentIndex(size - 1);
		for (; lastParentIndex > -1; lastParentIndex--) {
			heapify(lastParentIndex);
		}
	}

	private void initialize(Number[] data) {
		elements = (T[]) data;
		int i = 0;
		for (; i < data.length && data[i] != null; i++) {
		}
		size = i;
		capacity = data.length;
	}
	
	public void incrementSize(){
		size++;
	}
	
	public void decrementSize(){
		size--;
	}
	public boolean isResizingRequired(){
		return size >= capacity;
	}

	public int getSize() {
		return size;
	}

}
