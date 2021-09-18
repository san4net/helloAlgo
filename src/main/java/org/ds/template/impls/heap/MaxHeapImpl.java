
package org.ds.template.impls.heap;

public class MaxHeapImpl<T extends Number> extends AbstractHeap<Number> {
	
	public MaxHeapImpl() {
		
	}
	
	/** to build we need to start from non leaf node till 0th index
	 * 
	 */
	
	public void heapify(int index){
		int leftChild = getLeftChildIndex(index);
		int rightChild = getRightChildIndex(index);
		int maxIndex = -1;
	
		if(leftChild < getSize() && elements[leftChild].intValue() > (elements[index].intValue()))
			maxIndex = leftChild;
		else {
			maxIndex = index;
		}
		
		if(rightChild < getSize() && elements[rightChild].intValue() > elements[maxIndex].intValue()){
			maxIndex = rightChild;
		}
		
		if(maxIndex != index){
		//swap the element at index with element at max index
		  T data = (T) elements[index];
		  elements[index] = elements[maxIndex];
		  elements[maxIndex] = data;
		  heapify(maxIndex);
		}
	}
	
		/**
		 * 
		 */
	public static void main(String[] args) {
		Integer a[] = {1,2,3};
		MaxHeapImpl<Integer> instance = new MaxHeapImpl<>();
		instance.buildHeap(a);
		instance.display();
		instance.insert(6);
		instance.display();
		instance.heapSort();
		instance.display();
		System.out.println("removing root element");
		instance.buildHeap(a);
		instance.display();
		instance.remove(0);
		instance.display();
	}
	
	@Override
	public void insert(Number data) {
		// check the size
			if(isResizingRequired()){
				resize();
			}
			// now size will increase
			incrementSize();
			//index of new data
			int index = getSize() -1;
			// Now traverse to find the correct index
			while(index > 0 && elements[getParentIndex(index)].intValue() < data.intValue()){
				// if child is greater we need to copy parent to child and update the index with parent index
				elements[index] = elements[getParentIndex(index)];
				index = getParentIndex(index);		
			}
			elements[index] = (T) data;
		}

	/**
	 * To make things simple we can start with 1 leaving index 0 
	 */
	
	/**
	 * to sort we can use heap property
	 * 1. build a heap .. here maxheap .  now we know the element at the top is max. 
	 * 2. swap this element with last index .. so max is at last(this we will keep repeating for sorting)
	 * 3. decrease the size of heap  by 1 . 
	 * 4. heapify the element at top index. and repeat 2 to 4
	 * 
	 */
	public void heapSort() {
		// we have have
		buildHeap(elements);
		for (int i = getSize() - 1; i > 0; i--) {
			// swap 1 to size here it will be i
			swap(0, i);
			// we need to decrease size
			decrementSize();
			heapify(0);
		}
	}
	
	private void swap(int index1, int index2){
		T temp = (T) elements[index1];
		elements[index1] = elements[index2];
		elements[index2] = temp;
	}

	@Override
	public Number root() {
		// TODO Auto-generated method stub
		return elements[0];
	}
	
}

