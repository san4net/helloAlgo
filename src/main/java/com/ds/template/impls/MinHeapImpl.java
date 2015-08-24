package com.ds.template.impls;

import java.util.Arrays;

public class MinHeapImpl<T extends Comparable> extends AbstractHeap<Comparable> {
	private T[] elements;
	private int size;
	private int capacity;
	
	/* Leaf elements satisfy heap property so we need to start form the first non-leaf node and travere till root. 
	 * 
	 * (non-Javadoc)
	 * @see com.ds.template.impls.IHeap#buildHeap(java.lang.Integer[])
	 */
   
	@Override
	public void buildHeap(Comparable[] data ) {
		this.elements = (T[]) data;
		this.size = data.length;
		this.capacity = data.length;
		
		int j = size-1;
		
		for (int i = (j-1)/2 ; i>=0; i--) {
			minHeapify(i);
		}

	}
	
	
	public void minHeapify(int index) {
		if (index < size) {
			int left = getLeftChildIndex(index);
			int right = getRightChildIndex(index);

			int minIndex = -1;

			if (left < size && elements[left].compareTo(elements[index]) < 0 ) {
				minIndex = left;
			} else {
				minIndex = index;
			}

			if (right < size && elements[right].compareTo(elements[minIndex]) < 0) {
				minIndex = right;
			}

			if (minIndex != index) {
				// exchange index with min index
				T temp = elements[index];
				elements[index] = elements[minIndex];
				elements[minIndex] = temp;
				minHeapify(minIndex);
			}
		}
	}

	private void resizeHeap() {
		capacity *= 2;
		elements = Arrays.copyOf(elements, capacity * 2);
	}
	
	public void display(){
		System.out.println(""+Arrays.asList(elements));
	}
	/**
	 * Time complexity is O(logn)
	 * @param number
	 */
	public void insert(T data) {
		if (size == capacity) {
			resizeHeap();
		}
		
		size++;
		// index of
		int i = size - 1;
		// minHeapify((i-1)/2);
		// put it their an call min heapy of its parent or do it here
       // this is percolate up
		while (i >= 0 && data.compareTo(elements[(i - 1) / 2]) < 0) {
			elements[i] = elements[(i - 1) / 2];
			if(i==0) break;
			i = (i-1)/2;
		}
		elements[i]=data;
	}
	
  public int getKthMininum(int k){
	  // suppose our heap contain only positive 
	  return -1;
  }
	
	public static void main(String[] args) {
		 
		Integer[] element = {5,3,4,1,2,10,20};
		System.out.println("element befor" +Arrays.asList(element));
		MinHeapImpl<Integer> minHeap = new MinHeapImpl();
		minHeap.buildHeap(element);
		System.out.println(Arrays.asList(element));
		minHeap.insert(-1);
		System.out.println(Arrays.asList(element));
		minHeap.display();
	}

}
