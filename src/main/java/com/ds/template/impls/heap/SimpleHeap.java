package com.ds.template.impls.heap;

public class SimpleHeap {
	int index = -1;
	int capacity = 10;
	int size = 0;
	int[] data = new int[10];

	public void insert(int number) {
		//full check
		data[++index ] = number;
		size++;
		percolateUp(index);
		// percolate down
	}
	
	public int pool() {
		if(size>0) {
			int a = data[0];
			// last element at root
			data[0] = data[index];
			data[index] = 0;
			--index;
			--size;
			percolateDown(0);
			return a;
		}
		
		return -1;
	}
	
	private void percolateDown(int i) {
		int left = getLeft(i);
		int right = getRight(i);
		
		  int temp = i;
		  
		  if(left >=0 && data[left] < data[temp]) {
			  temp= left;
		  }
		  
		  if(right >=0 && data[right] < data[temp]) {
			  temp=right;
		  }
		  
		  if(temp != i) {
			  // swap 
			  swap(temp,i);
			  percolateDown(temp);
		  }
		  
	}

	private int getRight(int i) {
		return i*2+2>index ? -1 : i*2+2;
	}

	private int getLeft(int i) {
		return i*2+1>index ? -1 : i*2+1;
	}

	private void percolateUp(int i) {
		int data = this.data[i];
		int parent = getParent(i);
		
		while(parent >=0 && (data< this.data[parent])) {
			// swap
			swap(i, parent);
			percolateUp(parent);
		}
	}
	

	private void swap(int i, int parent) {
		// TODO Auto-generated method stub
		data[i] = data[i] + data[parent];
		data[parent] = data[i]-data[parent];
		data[i] = data[i]-data[parent];
	}

	private int getParent(int i) {
		return i==0?-1 : (i-1)/2;
	}

	private boolean isfull() {
		return size < capacity;
	}

	public int getSize() {
		return size;
	}

}
