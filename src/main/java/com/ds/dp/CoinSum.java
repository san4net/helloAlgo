package com.ds.dp;

import java.util.Arrays;

/**
 * Question: 
 * Given a list of N coins, their values (V1, V2, ... , VN), and the
 * total sum S. Find the minimum number of coins the sum of which is S (we can
 * use as many coins of one type as we want), or report that it's not possible
 * to select coins in such a way that they sum up to S.
 * 
 * @author kumar
 *
 */
public class CoinSum {

	public CoinSum() {
		
	}
	
  public static void main(String[] args) {
	Integer[] coins = {1,3,5};
	Integer[] min = new Integer[15];
	// pseudocode
	// 1. mark that sum is not computed
	// 2. sum[0] = 0 
	// 3.  while computing a sum i  for all coin j whose vj <= i we compute if sum[i-vj] + 1 < sum[i]
	// then we will update 
	// It is simple - for each coin j, Vj≤i, look at the minimum number of
		// coins found for the i-Vjsum (we have already found it previously).
		// Let this number be m. If m+1 is less than the minimum number of coins
		// already found for current sum i, then we write the new result for it.
	
	min[0] = 0;
	for(int i =1 ; i < min.length; i++){
		min[i] = 1000;
	}
	
	for(int i = 1; i< 15 ; i++){
		
		for( int j = 0 ; j<coins.length ; j++ ){
			if(coins[j] <= i && min[i-coins[j]]+1 < min[i]){
				min[i] = min[i-coins[j]]+1;
			}
		}
		//
	}
	// result 
	System.out.println(Arrays.asList(min));
}
}
