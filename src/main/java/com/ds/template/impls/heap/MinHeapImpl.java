package com.ds.template.impls.heap;

import java.util.Arrays;

import com.ds.template.impls.AbstractHeap;

public class MinHeapImpl<T extends Number> extends AbstractHeap<Number> {
	@Override
	public void heapify(int index) {
			int left = getLeftChildIndex(index);
			int right = getRightChildIndex(index);
			int minIndex = -1;

			if (left < getSize() &&
					elements[left].intValue() < elements[index].intValue() ) {
				minIndex = left;
			} else {
				minIndex = index;
			}

			if (right < getSize() &&
					elements[right].intValue() < elements[minIndex].intValue()) {
				minIndex = right;
			}

			if (minIndex != index) {
				// exchange index with min index
				T temp = (T) elements[index];
				elements[index] = elements[minIndex];
				elements[minIndex] = temp;
				heapify(minIndex);
			}
	}

	public void display(){
		System.out.println(""+Arrays.asList(elements));
	}
	
	/**
	 * Time complexity is O(logn)
	 * @param number
	 */
	public void insert(Number data) {
		if (isResizingRequired()) {
			resize();
		}
		incrementSize();
		// index of new element to be inserted
		int i = getSize() - 1;
		// minHeapify((i-1)/2);
		// put it their an call min heap of its parent or do it here
       // this is percolate up
		while (i > 0 && data.intValue() < (elements[getParentIndex(i)].intValue()) ) {
			elements[i] = elements[getParentIndex(i)];
			i = getParentIndex(i);
		}
		elements[i] = (T) data;
	}
	
  public int getKthMininum(int k){
	  // suppose our heap contain only positive 
	  return -1;
  }
	
	public static void main(String[] args) {
		 
		Integer[] element = {5,3,4,1,2,10,20};
		System.out.println("element before" +Arrays.asList(element));
		MinHeapImpl<Integer> minHeap = new MinHeapImpl();
		minHeap.buildHeap(element);
		System.out.println(Arrays.asList(element));
		minHeap.insert(-1);
		System.out.println(Arrays.asList(element));
		minHeap.display();
	}


}
