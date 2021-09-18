package org.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Question: 
 * Given a list of N coins, their values (V1, V2, ... , VN), and the
 * total sum S. Find the minimum number of coins the sum of which is S (we can
 * use as many coins of one type as we want), or report that it's not possible
 * to select coins in such a way that they sum up to S.
 * 
 * @author santosh kumar
 *
 */

public class CoinSum {
	static final Logger logger = LogManager.getLogger(CoinSum.class.getName());
	/**
	 *  
	 * @param coins
	 * @param S
	 * @return
	 */
	static int getCount(int[] coins, int S) {
		    int[] minCoins = new int[S+1];
		    minCoins[0]=0;
		    for(int k =1 ; k < minCoins.length; k++){
				minCoins[k] = Integer.MAX_VALUE;
			}
		    
		    for(int i = 1; i< minCoins.length ; i++){
				
				for( int j = 0 ; j<coins.length ; j++ ){
					if(coins[j] <= i && minCoins[i-coins[j]]+1 < minCoins[i]){
						minCoins[i] = minCoins[i-coins[j]]+1;
					}
				}
				//
			}
		    
		    return minCoins[S];
	    }
	 
	 private static int minRemoval(int[] d){
		 System.out.println("sorted"+Arrays.asList(d));
		 int i = getMFromEnd(d);
		 int j = getMFromEnd(d);
		 int k = both(d);
		 if(i<=j && i<=k){
			 return i;
		 }
		 if(j<=i&& j<=k){
			 return j;
		 }
		 return k;
	 }
	 
	 private static int getMFromStart(int[] d){
		 int skip = 1; 
		 int i=0;
		 for( ; i<d.length; i++){
			 if(d[d.length-1]-2*d[i+1]<=0){
				 break;
			 }
		 }
		 
		 return i+1;
	 }
	 
	 private static int getMFromEnd(int[] d){
		 int skip = 1; 
		 int i=d.length-1;
		 for( ; i>=0; i--){
			 if(d[i-1]-2*d[0]<=0){
				 break;
			 }
		 }
		 
		 return d.length - i;
	 }
	 
	 private static int both(int[] d){
		 int mid = d.length%2==0 ? (d.length-1)/2 : d.length/2;
		 int end = d.length-1;
		 int i = 0;
		 for(; i<=mid; i++){
			 if(d[end-(i+1)]- 2*d[i+1]<=0){
				 break;
			 }
		 }
		 return 2*(i+1);
	 }
	 
	 /**minCoins
 * 
 * @return
 */
	private static int getMinimumCount(){
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
		
		return min[0];
	}
	
  public static void main(String[] args) {
		 String hello = "Hello", lo = "lo";
		  System.out.println((hello == "Hello") + " ");
		  System.out.println((hello == ("Hel"+"lo")));
		  System.out.println(lo=="lo");
		  System.out.println((hello == ("Hel"+lo)) );
		  System.out.println(hello == ("Hel"+lo).intern());
		  System.out.println(hello == "Hello" );
	  int [] a = {1,3,4,5};
//	  System.out.println(minRemoval(a));
	  System.out.println(getCount(a, 11));
//	  System.out.println(getMin(6));}
  }
  
  private static List<Integer> generateFib(int N){
	  int a=1 ,b=1;
	  int c = 0;
	  List<Integer> fibos = new ArrayList<>();
		while (c <= N) {
			c = a + b;
			fibos.add(c);
			a = b;
			b = c;
		}
	  return fibos;
  }
  
	private static int getMin(int N) {
		List<Integer> fibos = generateFib(N);
		int[] min = new int[N + 1];
		min[0] = 0;
		min[1] = 1;
		min[2] = 1;
		for (int i = 2; i <= N; i++) {
			min[i] = 9999;
			for (int f : fibos) {
				int temp = f;
				int step = 0;
				if (temp <= i) {
					step = min[i - temp] + 1;
					min[i] = Math.min(min[i], step);
				}
			}
		}

		return min[N];
	}
}
