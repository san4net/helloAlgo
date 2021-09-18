package com.algo.dp;

/**
 * https://leetcode.com/articles/trapping-rain-water
 * 
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * 
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In
 * this case, 6 units of rain water (blue section) are being trapped. Thanks
 * Marcos for contributing this image!
 * 
 * 
 * @author sk50921
 *
 */
public class TrappingRainWater {

	public static int maxWaterTapDp(int[] height) {
		int[] maxL = new int[height.length];
		int[] maxR = new int[height.length];

		maxL[0] = height[0];
		// we will find max left for each index
		for (int i = 1; i < height.length; i++) {
			maxL[i] = Math.max(height[i], maxL[i - 1]);
		}

		maxR[height.length - 1] = height[height.length - 1];
		// we will find max left for each index
		for (int i = height.length - 2; i > -1; i--) {
			maxR[i] = Math.max(height[i], maxR[i + 1]);
		}

		int max = 0;

		for (int i = height.length - 2; i > -1; i--) {
			max += Math.min(maxL[i], maxR[i]) - height[i];
		}

		return max;
	}

	public static int maxWaterTapBruteForce(int[] height) {
		// iterate from 1 to end
		int ans = 0;
		for (int i = 0; i < height.length; i++) {
			int maxl = 0, maxr = 0;
			// finding max left
			for (int j = i; j >= 0; j--) {
				maxl = Math.max(maxl, height[j]);
			}

			for (int j = i; j < height.length; j++) {
				maxr = Math.max(maxr, height[j]);
			}

			ans = ans + Math.min(maxl, maxr) - height[i];
		}

		return ans;
	}

}
