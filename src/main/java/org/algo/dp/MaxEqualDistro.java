package org.algo.dp;

import java.util.stream.Stream;
/**
 Nikita just came up with a new array game. The rules are as follows:
	Initially, there is an array, , containing  integers.
	In each move, Nikita must partition the array into  non-empty contiguous parts such that the sum of the elements in the left partition is equal to the sum of the elements in the right partition. If Nikita can make such a move, she gets  point; otherwise, the game ends.
	After each successful move, Nikita discards either the left partition or the right partition and continues playing by using the remaining partition as array .
	Nikita loves this game and wants your help getting the best score possible. Given , can you find and print the maximum number of points she can score?
 * 
 * @author khus
 *
 */
public class MaxEqualDistro {
	
	private int number[];
	private int sum[] ;
	private void init(int[] num){
		this.number = num;
		this.sum = new int[number.length];
			for (int i = 0; i < number.length; i++) {
				sum[i] = (i == 0 ? 0 : sum[i - 1]) + number[i];
			}
	}
	
	/** 
	 * 1. index of equal index
	 * 2. else return 0;
	 * 3. else traverse either half and return greater +1
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private int getMaxEqualDistro(int start, int end) {
		if (start < end) {
			int sumThisSubArray ;

			if (start == 0) {
				sumThisSubArray = sum[end];
			} else {
				sumThisSubArray = sum[end] - sum[start - 1];
			}
			int index = -1;
			int temp1 = 0;
			for (int i = start; i < end; i++) {
				temp1 = temp1 + number[i];
				if ((temp1 * 2) == sumThisSubArray) {
					//we have equal index
					index = i;
					break;
				}
			}

			if (index != -1) {
				int l = getMaxEqualDistro(start, index);
				int r = getMaxEqualDistro(index + 1, end);
				return l>r ? l+1:r+1;
			}
			return 0;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(addBinary("11", "1"));
	}

	public static String addBinary(String a, String b) {
		// decimal to binary
		char[] aa = a.toCharArray();
		char[] bb = b.toCharArray();
		StringBuilder r = new StringBuilder();
		int c = 0;
		int i=bb.length-1, j=aa.length-1;

		for(; i>-1 && j>-1; i--,j--){
			int t = aa[j]-'0' +bb[i]-'0' + c;
			r = r.append(t%2);
			c = t/2;
		}

		if(i == -1 ){
			while (j>=0){
				int t = aa[j]-'0' + c;
				r = r.append(t%2);
				c = t/2;
				j--;
			}
		}

	    if(j==-1){
			while (i>=0){
				int t = bb[i]-'0'+c;
				r.append(t%2);
				c = t/2;
				i--;
			}
		}

		if(c==1){
			r.append(c);
		}

		return r.reverse().toString();

	}
	
	static int[] parseIntArray(String[] arr) {
	    return Stream.of(arr).mapToInt(Integer::parseInt).toArray();
	}
	
	static void finalPrice(int[] prices) {
		// i need to find discount per index
		int dis[] = new int[prices.length];
		dis[prices.length-1] = 0;
		
		// for each index we need to find
		
		for(int i = 0 ; i< prices.length; i++){
			int indexPrice = prices[i];
			int discount = 0;
			for(int j = i+1;  j< prices.length; j++){
				if(indexPrice>prices[j]){
					discount = prices[j];
					break;
				}
			}
			dis[i] =  discount;
		}
		// cost of purchasing
		long totalCost = 0;
		for(int i =0; i<prices.length;i++){
			totalCost = totalCost + prices[i];
		}
		
		long totalDiscout = 0;
		for(int i =0; i<dis.length;i++){
			totalDiscout = totalDiscout + dis[i];
		}
		
		System.out.println(totalCost-totalDiscout);
		
		for(int i =0; i<dis.length;i++){
			if(dis[i] == 0){
				System.out.print(i +" ");
			}
		}
		
	}
	
}
