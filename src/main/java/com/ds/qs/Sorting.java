package com.ds.qs;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class Sorting {
	static int numbers[] = { 4, 3, 2, 1 };

	public static void main(String[] args) {
		Integer elements[] = { 1, 5, 4, 3, 8, 6 };
		quickSort(elements, 0, elements.length-1);
		System.out.println("quck sort" +Arrays.asList(elements));

		/**
		 * 
		 * bubleSort(elements); testAtomicLong();
		 */
		selectionSort(elements);
		insertionsort(elements);
	}
	
	

	public static void testAtomicLong() {
		System.out.println("----testAtomicLong----");
		AtomicLong num = new AtomicLong();
		num.set(Long.MAX_VALUE);
		System.out.println("After b4 addition:" + num);
		System.out.println();
		num.addAndGet(1);
		System.out.println("After addition:" + num);
	}


	private static void exchange(Integer[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	private static void quickSort(Integer[] A, int p, int r) {
		if (p < r) {
			int q = partition(A, p, r);
			// on index q we are putting pivot and it is correct index 
			quickSort(A, p, q - 1);
			quickSort(A, q + 1, r);
		}
	}
	
	/** pseudo algo from coreman 
	 int X = A[r];
	 i = p-1;
		for j=p to r-1
	 		if(A[j]=< X)
				i = i +1;
				exchange A[i] with A[j]
   	exchange A[i+1] with A[r]
   	return i+1;
	 **/
	
	static int partition(Integer[] A, int p, int r) {
		int pivot = A[r];
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (A[j] <= pivot) {
				i++;
				// exhange i with j
				exchange(A, i, j);
			}
		}
		// now we know that 
		exchange(A, i + 1, r);
		return i + 1;
	}
	
	private static void bubleSort(Integer[] elements) {
		System.out.println("IP: " + Arrays.toString(elements));
		int i = elements.length - 2;
		int l = 1;
		boolean swap = false;
		for (; i >= 0; i--) {
			swap = false;
			for (int j = 0; j <= i; j++) {
				// 4 3 1 6
				if (elements[j] > elements[j + 1]) {
					swap(elements, j, j + 1);
					swap = true;
				}

			}
			if (!swap) {
				break;
			}
			System.out.println("elements after run " + l++ + ":" + Arrays.toString(elements));
		}
	}

	private static void swap(Integer elements[], int index1, int index2) {
		int temp = elements[index1];
		elements[index1] = elements[index2];
		elements[index2] = temp;
	}

	/**
	 * 
	 * @param A
	 */
	private static void insertionsort(Integer[] A) {
		System.out.println("insertion:" + Arrays.toString(A));
		for (int j = 1; j < A.length; j++) {
			int key = A[j];
			// Now insert A[j] into to the sorted array A[0... (j-1)]
			int i = j - 1;
			while (i > -1 && A[i] > key) {
				A[i + 1] = A[i];
				i--;
			}
			// put key in the sorted array at the correct position
			A[i + 1] = key;
		}

		System.out.println("insertion:" + Arrays.toString(A));
	}

	/**
	 * 
	 * @param a1
	 * @param length
	 */
	static void insertionSort(Integer[] a1, int length) {
		int i, j, tmp;
		for (i = 1; i < length; i++) {
			j = i;
			while (j > 0 && a1[j - 1] > a1[j]) {
				tmp = a1[j];
				a1[j] = a1[j - 1];
				a1[j - 1] = tmp;
				j--;
			}
		}
	}

	static void selectionSort(Integer[] A) {
		System.out.println("Elements" + Arrays.toString(A));
		for (int j = 0; j < A.length - 1; j++) {
			//
			int key = A[j];
			int index = j;
			// finding the key element and its index
			// first we will find 1st smallest and swap it with 1st element ,
			// 2nd we will select
			// 2nd smallest and swap it with 2nd element
			for (int i = j + 1; i < A.length; i++) {
				if (key > A[i]) {
					key = A[i];
					index = i;
				}
			}

			// swap it
			A[index] = A[j];
			A[j] = key;

		}
		System.out.println("Elements" + Arrays.toString(A));
	}

}
