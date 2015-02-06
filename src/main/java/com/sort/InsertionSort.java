package com.sort;

import java.util.Arrays;

public class InsertionSort {

/** 
 *  -  1st element is always sorted 
 *  -  start with second element  assume it is as key and we have to find its 
 *    index in the sorted array a[1... i-1]
 *    - to do this we need to traverse till 
 *   - now pick others one by one let index be i let call that elements as key = a[i]
 *   
 *   - find the correct index for key in the A[1.. i-1] 
 * 
 * 
 */
	public static void  insertionSort(Integer[] A){
		System.out.println("Before"+Arrays.toString(A));
		for(int j =1 ; j<A.length ; j++){
			int key = A[j];
			int i =j-1;
			 while(i>-1 && A[i]>key){
				 A[i+1] = A[i];
				 i--;
			 }
			 A[i+1] = key;
		}
		System.out.println(Arrays.toString(A));
	}
	
	public static void insert(Integer[] A, int index){
		int key = A[index];
		int i =index-1;
		 while(i>-1 && A[i]>key){
			 A[i+1] = A[i];
			 i--;
		 }
		 A[i+1] = key;
	}
	
	/**
	 * 
	 * @param A
	 * @param length
	 */
	public static void insertionByRecursion(Integer[] A, int length){
		if(length >= 1){
			insertionByRecursion(A, length-1);
			insert(A, length);
		}
		System.out.println(Arrays.toString(A));
	}
	
	public static void main(String[] args) {
		Integer a [] = {5,4,3,2,1};
//		insertionByRecursion(a, a.length-1);
		InsertionSort i  = new InsertionSort();
		i.testInsertion(a);
	}

public void insertionTest(Integer[] inputArray){
	System.out.println("input:" + Arrays.toString(inputArray));
	//
	for(int i = 1; i<inputArray.length; i++){
		
		int key = inputArray[i];
		int j = i-1;
		while(j>=0 && inputArray[j] > key){
			inputArray[j+1] = inputArray[j];
			j--;
		}
		inputArray[j+1]=key;
		
	}
	// time complexity   1.  c1 *n c2*n   c3 * summation of j=1 to n , 
	System.out.println("input:" + Arrays.toString(inputArray));
}

public void testInsertion(Integer[] A){
/**psedo code
 * 1. first element is sorted 
 * 2. start with second, and treat them as key
 * 3. find the position in the sorted array A[1..j-1]
 * 4. put 
 * 	
 */
	System.out.println("input:" + Arrays.toString(A));
	for(int i=1;i<A.length;i++){
		int key = A[i];
		int j = i-1;
		 while(j>=0&&key<A[j]){
			 A[j+1] = A[j];
			 j--;
		 }
		 A[j+1]=key;
		 
	}
	System.out.println("input:" + Arrays.toString(A));
}
}