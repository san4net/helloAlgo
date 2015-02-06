package com.ds.qs;

import java.util.Arrays;

public class Problems {

	/*
	 * Given an array [a1b2c3d4] convert to [abcd1234] with 0(1) space and O(n)
	 * time
	 * 
	 * - kumarasvn on October 02, 2
	 */
	/**
	 * pseudocode
	 * 
	 *  - B[A.length/2] traverse the array to get all the numbers int j=1 for
	 *  -  Now we have all number in B make all
	 * alphabets j== for i = 1 to A.length A[i-j]=A[i] i = i+2;
	 * 
	 * i=0 for j = A.lenght/2to A.length A[j++] = B[i++]
	 * */
	public static void problem1(String[] A) {

		

		String B[] = new String[A.length / 2];

		for (int i = 1, j = 0; i < A.length; i = i + 2) {
			B[j++] = A[i];
		}
		
		System.out.println("B" + Arrays.toString(B));
		// Now re arrange
		int i = 0, j = 0;
		while (i < A.length) {
			A[i - j] = A[i];
			j++;
			i = i + 2;
		}
		// fill in
		i = A.length / 2;
		j = 0;
		while (i < A.length) {
			A[i++] = B[j++];
		}
		System.out.println("A:" + Arrays.toString(A));
	}

	public static void main(String[] args) {
		String A[] = { "a", "1", "b", "2", "c", "3", "d", "4", "a", "1", "b",
				"2", "c", "3", "d", "4" };
		problem1(A);
	}
}
