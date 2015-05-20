package com.ds.template.impls;

import java.util.Arrays;

public class Heap {
	Integer[] elements;
	int size;  // no of elements in the heap
	int capacity ; // capacity of heap

	public Heap(int[] elements, int capacity) {
		super();
		this.elements = new Integer[capacity];
		Arrays.copyOf(elements,capacity );
		this.size = elements.length;
	}

	public int getParentIndex(int i) {
		if (i<0  || i >= size)
			return -1;
		return (i - 1) / 2;
	}

	public static int getLeftChildIndex(int i) {
		
		int childIndex = i * 2 + 1;
		// if(childIndex>=capacity) return -1;
		return childIndex;
	}

	public static int getRightChildIndex(int i) {
		int childIndex = i * 2 + 2;
		// if(childIndex>=capacity) return -1;

		return childIndex;
	}

	// this is percolate down this is utilty
	
	public static void maxHeapify(Integer[] array, int index) {
		int leftIndex = getLeftChildIndex(index);
		int rightIndex = getRightChildIndex(index);

		int maxIndex = -1;
		
		// if max heapify
		if (leftIndex < array.length  && array[leftIndex] > array[index]) {
			maxIndex = leftIndex;
		} else {
			maxIndex = index;
		}

		if (rightIndex < array.length  && array[rightIndex] > array[maxIndex]) {
			maxIndex = rightIndex;
		}

		if (index != maxIndex) {
			// swap the elements
			exchange(array, index, maxIndex);
			maxHeapify(array, maxIndex);
		}
	}
   
	// this is percolate down this is utilty
	
	public void maxHeapify(int index) {
		int leftIndex = getLeftChildIndex(index);
		int rightIndex = getRightChildIndex(index);

		int maxIndex = -1;
		
		// if max heapify
		if (leftIndex < size  && elements[leftIndex] > elements[index]) {
			maxIndex = leftIndex;
		} else {
			maxIndex = index;
		}

		if (rightIndex < size  && elements[rightIndex] > elements[maxIndex]) {
			maxIndex = rightIndex;
		}

		if (index != maxIndex) {
			// swap the elements
			exchange(elements, index, maxIndex);
			maxHeapify(maxIndex);
		}
	}
	
	public static void minHeapify(Integer[] array, int index){
		int leftChildIndex = getLeftChildIndex(index);
		int rightChildIndex = getRightChildIndex(index);
		int minIndex = -1;
		
		if(leftChildIndex >=0 && array[leftChildIndex] <array[index]){
			minIndex = leftChildIndex;
		}else {
			minIndex = index;
		}
		
		if(rightChildIndex >=0 && array[rightChildIndex] <array[minIndex]){
			minIndex = rightChildIndex;
		}
		
		if(index != minIndex){
			// swap and call percolate down minheapi
			exchange(array, index,minIndex);
			minHeapify(array, minIndex);
		}
	}

	private static void exchange(Integer[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;

	}

	public static void main(String[] args) {
		int[] array = { 5, 7, 8, 11, 56 };
		Heap h = new Heap(array, array.length);
//		List<Integer> list = Arrays.asList(array);
		int[] a = {1,2,3,4,5};
		h.buildHeap(array);
		System.out.println(h);
	/*	for (int i = 0; i < array.length; i++) {
			maxHeapify(array, i);
		}*/

		System.out.println("heapify " + Arrays.asList(array));
//		h.insert(45);
	}

	private void resizeHeap() {
		// capacity *= 2;
		// Integer[] newArray = new Integer[capacity];
		elements = Arrays.copyOf(elements, size * 2);

	}

	public void buildHeap(int[] input) {
		if (input.length > size) {
			resizeHeap();
		}

		for (int i = 0; i < input.length; i++) {
			elements[i] = input[i];
		}

		int n = size-1;

		for (int i = getParentIndex(n); i >= 0; i--) {
			// percolate down
			maxHeapify( i);
		}
	}

	/**
	 * This insert is for max heap
	 * 
	 * @param data
	 */
	public void insert(int data) {
		if (size == capacity) {
			resizeHeap();
		}
		
		// Increasing size to hold this new item
		size++;

		int i = size - 1;

		// percolate up
		while (i >= 0 && data > elements[getParentIndex(i)]) {
			//	elements[i] = elements[(i - 1) / 2];
			// exchange
			exchange(elements, i, getParentIndex(i));
			if (i == 0)
				break;
			i = getParentIndex(i);
		}

		elements[i] = data;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Arrays.toString(elements);
	}
}
