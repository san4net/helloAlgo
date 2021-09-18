package org.algo.dp;

public class KadaneAlgo {
	/**
	 * <link> https://en.wikipedia.org/wiki/Maximum_subarray_problem </link>
	 * {@link MaxEqualDistro}
	 * Kadane's algorithm begins with a simple inductive question: if we know the maximum subarray sum ending at position i
	 * .
	 * 
	 */
	public int maxContigious(int[] data) {
		int max_ending_here = data[0];
		int max_so_far = data[0];
		
		for(int i=1; i<data.length;i++) {
			max_ending_here = Math.max(max_ending_here+data[i], data[i]);
			max_so_far = Math.max(max_ending_here, max_so_far);
		}
		
		return 0;
		
	}

}
