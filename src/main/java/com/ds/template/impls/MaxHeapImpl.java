package com.ds.template.impls;

import java.util.Arrays;

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
		
		int i = size-1; // we can sacrifice index 0 and start from 1 
		
		for(int j = getParentIndex(i); j>-1 ; j--){
			heapify(j);
		}
		
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
	public static void main(String[] args) {
		Integer a[] = {1,2,3,4,5};
		MaxHeapImpl<Integer> instance = new MaxHeapImpl<>();
		instance.buildHeap(a);
		System.out.println(Arrays.asList(a));
		System.out.println((0-1)/2);
		instance.insert(6);
		
	}
	
	private void display (){
		
	}
	private void resizeHeap() {
		capacity *= 2;
		elements = Arrays.copyOf(elements, capacity * 2);
	}

	@Override
	public void insert(Comparable data) {
	// check the size
			if(size == capacity){
				resizeHeap();
			}
			// now size will increase
			size++;
			//index of new data
			int index = size -1;
			
			// now travers to fine the correct index
			while(index >=0 && elements[getParentIndex(index)].compareTo(data) < 0){
				// if child is greater we need to copy parent to child and update the index with parent index
				elements[index] = elements[getParentIndex(index)];
				index = getParentIndex(index);		
			}
			
			elements[index] = (T) data;
		}
	
}
