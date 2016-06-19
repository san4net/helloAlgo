package com.tc;

public class MaxSubArray {
	
	public Integer[] findMaximumSubArray(Integer[] A, int low, int high) {
		if (low == high) {
			Integer[] result = new Integer[3];
			result[0] = low;
			result[1] = high;
			result[2] = A[low]; // sum
			return result;
		} else {
			int mid = (low + high) / 2;
			@SuppressWarnings("unused")
			Integer[] left_Result =  findMaximumSubArray(A, low, mid);
			Integer[] right_Result = findMaximumSubArray(A, mid + 1, high);
			Integer[] cross_Result = getCrossMaxSubArray(A, low, mid, high);

			if (left_Result[2] >= right_Result[2]
					&& left_Result[2] >= cross_Result[2]) {
				return left_Result;
			} else if (right_Result[2] >= left_Result[2]
					&& right_Result[2] >= cross_Result[2]) {
				return right_Result;
			}

			else
				return cross_Result;
		}
	}
 
	public Integer[] getCrossMaxSubArray(Integer[] A, int low, int mid, int high) {
		int leftSum = -9000;
		int sum = 0;
		int i = mid;
		int maxLeIndex = -1;
		while (i >= low) {
			sum = sum + A[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxLeIndex = i;
			}
			i--;
		}

		int rightSum = -9000;
		sum = 0;
		i = mid+1;
		int maxRightIndex = -1;
		while (i <= high) {
			sum = sum + A[i];
			if (sum > rightSum) {
				rightSum = sum;
				maxRightIndex = i;
			}
			i++;
		}

		Integer[] result = new Integer[3];
		result[0] = maxLeIndex;
		result[1] = maxRightIndex;
		result[2] = leftSum + rightSum;
		return result;
	}
	
public static void main(String[] args) {
	Integer testArray[] = {-5,4,6,-8,9,9,9};
	MaxSubArray maxArray = new MaxSubArray();
	Integer a[]  = maxArray.findMaximumSubArray(testArray, 0, testArray.length-1);
	
	System.out.println("sum="+a[2] +"-"+ a[0] +"-"+ a[1]);
	
}
	
}
