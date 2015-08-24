package com.ds.template.impls;

public class MaxHeapImpl<T extends Comparable> extends AbstractHeap<Comparable> {
	private T[] elements;
	private int size;
	private int capacity;
	
	public MaxHeapImpl() {
		// TODO Auto-generated constructor stub
		
	}
	
	/** to build we need to start from non leaf node till 0th index
	 * 
	 */
	@Override
	public void buildHeap(Comparable[] data) {
		// TODO Auto-generated method stub
		elements = (T[]) data;
		size = data.length;
		capacity = data.length;
		
	}

	private void heapify(int index){
		int leftChild = getLeftChildIndex(index);
		int rightChild = getRightChildIndex(index);
		int maxIndex = -1;
		if(leftChild < size && (elements[leftChild].compareTo(elements[index]) > 0))
			maxIndex = leftChild;
		else {
			maxIndex = index;
		}
		
		if(rightChild < size && (elements[rightChild].compareTo(elements[maxIndex]))>0){
			maxIndex = rightChild;
		}
		
		if(maxIndex != index){
			//swap the element at index with element at min indes
		  T data = elements[index];
		  elements[index] = elements[maxIndex];
		  elements[maxIndex] = data;
		  heapify(maxIndex);
		}
	}
		/**
		 * 
		 */
}
