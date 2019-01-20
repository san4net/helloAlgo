package com.math;

public class Combination {
	/**nCr
	 * Combination of n things taken r at a time
	 * 
	 * @param input
	 * @param index
	 * @param r
	 */
	static void combination(char[] input, int index, int r) {
		if (index < 0) {
			if (input.length == r) {
				System.out.println(new String(input));
			}
			return;
		}

		if (input.length == r) {
			System.out.println(new String(input));
			return;
		}

		// Either we include or exclude
		// include here
		combination(input, index - 1,r);
		// remove index / exclude
		char[] removeInput = removeAndReturnArray(input, index);
		if(removeInput != null && removeInput.length>=r)
		 combination(removeInput, index - 1,r);
	}

	/**
	 * Finding all combination of given array i.e ncr where r=1 to n
	 * 
	 * @param input
	 * @param index
	 */
	static void combination(char[] input, int index) {
		
		if (index < 0) {
			System.out.println(new String(input));
			return;
		}

		/*if (input.length == 1) {
			System.out.println(new String(input));
			System.out.println("here");
			return;
		}*/

		// Either we include or exclude
		// include
		combination(input, index - 1);
		// remove index / exclude
		char[] removeInput = removeAndReturnArray(input, index);
		if(removeInput!=null)
		combination(removeInput, index - 1);
	}

	static char[] removeAndReturnArray(char[] in, int index) {
		if(in.length==1) return null;
		char[] r = new char[in.length - 1];
		for (int i = 0, j = 0; i < in.length; i++) {
			if (i == index)
				continue;
			r[j++] = in[i];
		}
		return r;
	}

	public static void main(String[] args) {
//		combination("ABCDE".toCharArray(), 4);
		combination("ABCDE".toCharArray(), 4,4);
	}
}
