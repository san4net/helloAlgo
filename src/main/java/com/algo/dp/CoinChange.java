package com.algo.dp;

/**
 * 
 * Given a value N, if we want to make change for N cents, and we have infinite supply of each of
 *  S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change? 
 *  The order of coins doesn?t matter.
 * 
 * @author santosh kumar
 *
 */
public class CoinChange {
	
	// using  recursion at every call we either include mth coin or exclude mth
	public static long makeChange(int[] coins, int index, int money) {
		// we have a solution since we have made money to 0
		if (money == 0)
			return 1;
		// money is less then 0 that means this is not a solution
		if (money < 0)
			return 0;
		//
		if (index <= 0 && money > 0)
			return 0;
		// int count( int S[], int m, int n )
		// return count( S, m - 1, n ) + count( S, m, n-S[m-1] )
		return makeChange(coins, index, money - coins[index - 1]) + makeChange(coins, index - 1, money);
	}

	// memorization\
	// keep table[]

	public static long makeChangeMemorization(int[] coins, int money) {
		long[] table = new long[money+1];
		// base case table[0]=0
		table[0]=1;
		// Iterate over all the coins 
		// and then update the table where index is >= value of coin
		// it means 
		for(int i=0; i<coins.length; i++){
			 for(int j= coins[i]; j<=money;j++){
				 table[j] = table[j] + table[j-coins[i]];
			 }
		}
		
		return table[money];
	}

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3 };
		System.out.println(makeChange(arr, arr.length, 5));
		System.out.println(makeChangeMemorization(arr, 5));
	}
}
