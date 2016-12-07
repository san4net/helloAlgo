package com.ds.dp;

/**
 * 
 * @author santosh
 *
 */
public class RodCutting {

	/**
	 * This is bottom up approach
	 * 
	 * @param price
	 * @param n
	 * @return
	 */
	static int maxRodeCutting(int[] price, int n) {
		int mPrice[] = new int[n + 1];
		mPrice[0] = 0;
		for (int i = 0; i < mPrice.length; i++) {
			mPrice[i] = 0;
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = i; j > 0; j--) {
				mPrice[i] = Math.max(mPrice[i], price[j] + mPrice[i - j]);
			}
		}

		return mPrice[n];
	}

	private static int getAmount(int hours) {
		return (2 + 3 + (hours - 1) * 4);
	}

	public static void main(String[] args) {
	}
}
