package com.sort;

import java.util.Arrays;

public class Merge {

	// MergeSort
	public void MergeSort(Integer[] A, int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			int mid = (startIndex + endIndex) / 2;
			MergeSort(A, startIndex, mid);
			MergeSort(A, mid + 1, endIndex);
			// Merge(A, startIndex, mid, endIndex);
			Merg(A, startIndex, mid, endIndex);
		}

	}

	/**
	 * 
	 * Merge pseudo code 
	 *   
	 *    Merge(Array A, index p , index m, index q)
	 *    n1 = m-p+1
	 *    n2 = q-m
	 *    Array L[1..to n1] and R[n2]
	 *    // copy all element fromm p to m to L
	 *    for i=1 to n1
	 *    L[i]= A[p+i-1]
	 *     // copy all element from +1 to q to R
	 *     for j=1 to n2
	 *     R[j]=A[m+j] 
	 * 
	 *   i =1, j=1 
	 *   for k = p to q
	 *        if(L[i]< R[j])
	 *          A[k++]=L[i++]
	 *          else
	 *          A[k++] =R[j++]
	 *          
	 * @param A
	 * @param startIndex
	 * @param mid
	 * @param endIndex
	 */
 private void Merg(Integer[] A, int p, int m, int q){
	 int n1 = m-p+1;
	 int n2 = q-m;
	 Integer L[] = new Integer[n1+1];
	 Integer R[] = new Integer[n2+1];
	 
	 int i=0;
	 for(i= 0 ; i< n1 ; i++){
		 L[i] = A[p+i];
	 }
	 
	 L[i]=-1;
	 
	 int j =0;
	 for(j =0; j< n2; j++){
	 R[j] = A[m+j+1];
	 }
	 R[j] = -1;
	 
	 i=0;j=0;
	 
	 for(int k = p; k<=q; k++){
		  if(R[j] == -1 || L[i]<R[j]){
			 A[k]=L[i++];
			 if(L[i]==-1){
				 
			 }
		  }else if(L[i]==-1 || L[i]>R[j]){
			 A[k] = R[j++];
		  }
	 }
 }
	
/**
 * 
 * @param A
 * @param startIndex
 * @param mid
 * @param endIndex
 */
@SuppressWarnings("unused")
private void Merge(Integer[] A, int startIndex, int mid, int endIndex) {
		int leftArray =  mid-startIndex + 1;
		int rightArray = endIndex - mid;

		Integer[] Left = new Integer[leftArray];

		for (int i = 0; i < leftArray ; i++) {
			Left[i] = A[startIndex + i];
		}

		Integer[] Right = new Integer[rightArray];
		for (int j = 0; j < rightArray; j++) {
			Right[j] = A[mid + j+1];
		}

		int i = 0, j = 0;
		
		while (startIndex <= endIndex) {
			if ( (j==Right.length)||(i< Left.length  && Left[i] < Right[j])) {
				A[startIndex++] = Left[i];
				i++;
				if(i == leftArray){
				 // copy remaining from rightList
				 while(startIndex <= endIndex)
					  A[startIndex++] = Right[j++];
				   break;
					  
				}
			} else {
				A[startIndex++] = Right[j];
				j++;
				if(j== rightArray){
				 // copy remaiing from left list 
				  while(startIndex <= endIndex)
					  A[startIndex++] = Left[i++];
				  break;
					  }
			}
		}

	}
	
	public static void main(String[] args) {
		Integer elements[] = {55, 44, 21};
		Merge instance = new Merge();
		instance.MergeSort(elements, 0, elements.length-1);
	    System.out.println("Arry"+Arrays.toString(elements));
	}
}
