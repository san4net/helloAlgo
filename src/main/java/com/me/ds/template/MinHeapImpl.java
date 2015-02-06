package com.me.ds.template;

import java.util.Arrays;

public class MinHeapImpl implements IHeap {
	private Integer[] elements;
	private int size;
	private int capacity;

	@Override
	public void buildHeap(Integer[] data) {
		this.elements = data;
		this.size = data.length;
		this.capacity = data.length;
		
		int j = size-1;
		
		for (int i = (j-1)/2 ; i>=0; i--) {
			minHeapify(i);
		}

	}

	public int getLeft(int i) {
		return i * 2 + 1;
	}

	public int getRight(int i) {
		return i * 2 + 2;
	}

	public void minHeapify(int index) {
		if (index < size) {
			int left = getLeft(index);
			int right = getRight(index);

			int minIndex = -1;

			if (left < size && elements[left] < elements[index]) {
				minIndex = left;
			} else {
				minIndex = index;
			}

			if (right < size && elements[right] < elements[minIndex]) {
				minIndex = right;
			}

			if (minIndex != index) {
				// exchange index with min index
				int temp = elements[index];
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
	public void insert(Integer number) {
		if (size == capacity) {
			resizeHeap();
		}
		size++;
		// index of
		int i = size - 1;
		// minHeapify((i-1)/2);
		// put it their an call min heapy of its parent or do it here
       // this is percolate up
		while (i >= 0 && number < elements[(i - 1) / 2]) {
			elements[i] = elements[(i - 1) / 2];
			if(i==0) break;
			i = (i-1)/2;
		}
		elements[i]=number;
	}
	
  public int getKthMininum(int k){
	  // suppose our heap contain only positive 
	  return -1;
  }
	
	public static void main(String[] args) {
		Integer[] ele = {5,3,4,1,2,10,20};
		
		MinHeapImpl minHeap = new MinHeapImpl();
		minHeap.buildHeap(ele);
		System.out.println(Arrays.asList(ele));
		minHeap.insert(-1);
		System.out.println(Arrays.asList(ele));
	   minHeap.display();
	}

}
