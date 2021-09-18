package org.tc;

public class Arrays {
	static int maxSum(int[] ar) {
	    Integer[] ar2 = new Integer[ar.length];
	    int i=0;
	    for (Integer integer : ar) {
	        ar2[i] = integer.intValue();
	        i++;
	    }
	    Integer[] result = findMaximumSubArray(ar2, 0, ar2.length-1);
	    return result[2];
	    }

	public static Integer[] findMaximumSubArray(Integer[] A, int low, int high) {
			if (low == high) {
				Integer[] result = new Integer[3];
				result[0] = low;
				result[1] = high;
				result[2] = A[low]; // sum
				return result;
			} else {
				int mid = (low + high) / 2;
			
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

	public static Integer[] getCrossMaxSubArray(Integer[] A, int low, int mid, int high) {

			int leftSum = -9000;
			int sum = 0;
			int i = mid;
			int maxLeft = -1;
			while (i >= low) {
				sum = sum + A[i];
				if (sum > leftSum) {
					leftSum = sum;
					maxLeft = i;
				}
				i--;
			}

			int rightSum = -9000;
			sum = 0;
			i = mid+1;
			int maxRight = -1;
			while (i <= high) {
				sum = sum + A[i];
				if (sum > rightSum) {
					rightSum = sum;
					maxRight = i;
				}
				i++;
			}

			Integer[] result = new Integer[3];
			result[0] = maxLeft;
			result[1] = maxRight;
			result[2] = leftSum + rightSum;
			return result;
		}
}
