package com.me.ds.template;

import java.util.Arrays;
import java.util.List;

public class Heap {
	Integer[] elements;
	int capacity;
	int size;

	public Heap(Integer[] elements, int capacity) {
		super();
		this.elements = elements;
		this.capacity = capacity;
		this.size = elements.length;
	}

	public int parent(int i) {
		if (i >= capacity)
			return -1;
		return (i - 1) / 2;
	}

	public static int leftChild(int i) {
		int childIndex = i * 2 + 1;
		// if(childIndex>=capacity) return -1;
		return childIndex;
	}

	public static int rightChild(int i) {
		int childIndex = i * 2 + 2;
		// if(childIndex>=capacity) return -1;

		return childIndex;
	}

	// this is percolate down
	public static void maxHeapify(Integer[] array, int index) {
		int leftIndex = leftChild(index);
		int rightIndex = rightChild(index);

		int maxIndex = -1;

		if (leftIndex < array.length && array[leftIndex] > array[index]) {
			maxIndex = leftIndex;
		} else {
			maxIndex = index;
		}

		if (rightIndex < array.length && array[rightIndex] > array[maxIndex]) {
			maxIndex = rightIndex;
		}

		if (index != maxIndex) {
			// swap the elements
			exchange(array, index, maxIndex);
			maxHeapify(array, maxIndex);
		}
	}
// minHeapify

	private static void exchange(Integer[] array, int index, int largest) {
		int temp = array[index];
		array[index] = array[largest];
		array[largest] = temp;

	}

	public static void main(String[] args) {
		Integer[] array = { 31, 1, 21, 5, 10, 12, 18, 3, 2, 8, 7 };
		Heap h = new Heap(array, array.length);
		List<Integer> list = Arrays.asList(array);
		System.out.println(list);
		maxHeapify(array, 1);
		System.out.println(Arrays.asList(array));
        h.insert(45);
        System.out.println(Arrays.asList(h.elements));
        int i =0; 
        int j = i-1/2;
        System.out.println(j);
	}

	private void resizeHeap() {
		capacity *= 2;
		Integer[] newArray = new Integer[capacity * 2];
		elements = Arrays.copyOf(elements, capacity * 2);

	}

	public void buildHeap(int[] input) {
		if (input.length > capacity) {
			resizeHeap();
		}

		for (int i = 0; i < input.length; i++) {
			elements[i] = input[i];
		}
		
		int n = size;
		
		for (int i = (size - 1) / 2; i >= 0; i--) {
			// percolate down
			maxHeapify(elements, i);
		}
	}
	
	public void insert(int data){
		if(size == capacity){
			resizeHeap();
		}
	// Increasing size to hold this new item
		 size ++;
		 
		 int i = size-1;
		 
    // percolate up
 		 while(i>=0 &&  data > elements[(i-1)/2] ){
			 elements[i] = elements[(i-1)/2];
			 if(i == 0) break;
			 i = (i-1)/2;		 
		 }
 		 elements[i]=data;
	}
}
