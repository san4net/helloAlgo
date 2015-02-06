package com.ds.qs;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class Utility {
	static Integer numbers[] = {25,17,31,13,2,44,-7,456,234,22222,33,0,0};
	public static void main(String[] args) {
	   Integer elements[] = {5,4,3,2,1};
	   /**
	    * 
	   bubleSort(elements);
	   testAtomicLong();
	  */
//	  selectionSort(elements);
//	  insertionsort(elements);
	  LinkedList ll = new LinkedList();
	  ll.add(1,false);
	  ll.add(2,false);
	  ll.add(3,false);
	  ll.display();
	  
	}
	
public static void testAtomicLong(){
	System.out.println("----testAtomicLong----");
	AtomicLong num = new AtomicLong();
	num.set(Long.MAX_VALUE);
	System.out.println("After b4 addition:"+num);
	System.out.println();
	num.addAndGet(1);
	System.out.println("After addition:"+num);
}

public static int getNumber(){
	String a ="an";
	System.out.println("anti".contains(a));
	return 0;
}
/**
 * 
 * @param low
 * @param high
 */

private static void quicksort(int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    int pivot = numbers[low + (high-low)/2];

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (numbers[i] < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (numbers[j] > pivot) {
        j--;
      }

      // If we have found a values in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        exchange(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j)
      quicksort(low, j);
    if (i < high)
      quicksort(i, high);
  }

private static void exchange(int i, int j) {
    int temp = numbers[i];
    numbers[i] = numbers[j];
    numbers[j] = temp;
  }


private static void quicksort(Integer[] elements, int low, int high){
	int pivot = elements[low + (high-low)/2];
	int i =low;
	int j = high;
	 
		while (i <= j) {
			while (elements[i] < pivot) {
				i++;
			}
			while (elements[j] > pivot) {
				j--;
			}
			if (i <= j) {
				// exhange
				int t = elements[i];
				elements[i] = elements[j];
				elements[j] = t;
				i++;
				j--;
			}

		}
		
		if (low < j) {
			quicksort(elements, low, j);
		}
		if (i < high) {
			quicksort(elements, i, high);
		}
	
}

private static void bubleSort(Integer[] elements){
	System.out.println("IP: "+ Arrays.toString(elements));
	int i = elements.length-2;
	int l=1;
	boolean swap = false;
	for (;i>=0;i--){
		swap =false;
		for(int j =0;j<=i;j++){
			//4 3 1 6      
		   if(elements[j]>elements[j+1]){
			   swap(elements, j, j+1);
			   swap = true;
		   }
		   
		
		}
		  if(!swap){
			  break;
		  }
		System.out.println("elements after run "+ l++ +":"+Arrays.toString(elements));
	}
}

private static void swap(Integer elements[], int index1, int index2){
	int temp = elements[index1];
	elements[index1] = elements[index2];
	elements[index2] = temp;
}
/**
 * 
 * @param A
 */
	private static void insertionsort(Integer[] A) {
		System.out.println("insertion:"+ Arrays.toString(A));
		for(int j =1; j<A.length;j++ ){
			int key = A[j];
			//Now insert A[j] into to the sorted array A[0... (j-1)]
			int i =j-1;
			while(i >-1 && A[i]>key){
				A[i+1]= A[i];
				i--;
			}
			// put key in the sorted array at the correct position
			A[i+1]=key;
		}
		
		System.out.println("insertion:"+ Arrays.toString(A));
	}
	/**
	 * 
	 * @param a1
	 * @param length
	 */
	static void  insertionSort(Integer[] a1, int length) {
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
	

	static void selectionSort(Integer[] A){
		System.out.println("Elements"+Arrays.toString(A));
		for(int j=0; j<A.length-1; j++){
			//
			int key = A[j];
			int index =j;
			// finding the key element and its index 
			// first we will find 1st smallest and swap it with 1st element , 2nd we will select 
			// 2nd smallest and swap it with 2nd element 
			for(int i =j+1; i<A.length; i++){
				if(key>A[i]){
					key = A[i];
					index = i;
				}
			}
			
           // swap it 
			A[index] = A[j];
			A[j] = key;
			
		}
	 System.out.println("Elements"+Arrays.toString(A));
	}
}

