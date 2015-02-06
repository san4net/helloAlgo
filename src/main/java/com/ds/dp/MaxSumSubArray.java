package com.ds.dp;

public class MaxSumSubArray {

	public MaxSumSubArray() {
		// TODO Auto-generated constructor stub
	}
	public Integer[] crossResult(Integer[] input, int low, int mid, int high){
		int sum =0;
		int leftSum=0;
		int maxLeftIndex =0;
		for (int i = mid; i >= low; i--) {
			sum = sum + input[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxLeftIndex = i;
			}
		}
		
		int rightSum=0;
		int maxRightIndex =0;
		for (int i = high; i > mid; i--) {
			sum = sum + input[i];
			if (sum > rightSum) {
				rightSum = sum;
				maxRightIndex = i;
			}
		}
		
		Integer[] result = new Integer[3];
		result[0] = maxLeftIndex;
		result[1] = maxRightIndex;
		result[2] = leftSum + rightSum;
		return result;
		
	}
	
	public Integer[] findMaximumSubArray(Integer[] A, int low, int high) {
		if(low == high){
			Integer[] result = new Integer[0];
			result[0]= low;
			result[1]= low;
			result[2]= A[low];
			return result;
		}
		else
		{
			Integer[] result = new Integer[0];
			int mid = (low + high )/2;
			Integer[] left_Result = findMaximumSubArray(A, low, mid);
			Integer[] right_Result = findMaximumSubArray(A, mid+1, high);
			Integer[] cross_result = crossResult(A, low, mid, high);
			
			if(left_Result[2] >= right_Result[2] && left_Result[2] >= cross_result[2]){
				return left_Result;
			}else if(right_Result[2]>= left_Result[2] && right_Result[2] >= cross_result[2]){
				return right_Result;
			}else{
				return cross_result;
			}
			
		}
	}

	private class Result{
		int sum; 
		int left;
		int right;
		public Result(int sum, int left, int right) {
			super();
			this.sum = sum;
			this.left = left;
			this.right = right;
		}
		
		
	}
}
