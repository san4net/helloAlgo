package com.tc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Prime {

	int getNumberOfPrimes(int N) {
		int noOfPrime=0;
		if(N>3){
			noOfPrime =2;
			while(N>3){
				boolean isPrime=true;
				for (int i = 2; i <=( N/2); i++) {
					if(N % i == 0){
						isPrime = false; 
						break;
					}
				}

				if (isPrime) {
					noOfPrime++;
				}
				
				N = N-1;
			}
		}
		
		return noOfPrime;

	}
	
	int getNumberOfPrimes2(int n) {
		int noOfPrime=0;
		Integer t[] = new  Integer[n+1];
		for(int i=0;i<n+1;i++){
			t[i]=1;
		}
		
		t[0] = 0;
		t[1] = 0;
		
		for(int a=2;a<Math.sqrt(n);a++){
			
			for(int b=2;a*b<=n;b++){
				t[a*b]=0;
			}
		}
		
		for(int c=0;c<t.length;c++){
			if(t[c]==1){
			 noOfPrime++;	
			}
		}
		
		return noOfPrime;

	}
	
	public static void main(String[] args) {
//		Prime p = new Prime();
//		System.out.println("100 -"+p.getNumberOfPrimes2(1000000));  
//		System.out.println("1000000"+p.getNumberOfPrimes(1000000));
		int a[] = {1,1,44,2,2,3,3,4,4,66,66};
	   System.out.println(getUniqueElement(a));
	}
	
//	import java.util.HashMap;
//	import java.util.Iterator;
//	import java.util.Map.Entry;
	static int getUniqueElement(int[] a) {
		// Map containing number vs occurence
		HashMap<Integer, Integer> NoVersusOcurrence = new HashMap<Integer, Integer>();
		Integer unique = -1;

		for (Integer number : a) {
			if (NoVersusOcurrence.containsKey(number)) {
				NoVersusOcurrence
						.put(number, NoVersusOcurrence.get(number) + 1);
			} else {
				NoVersusOcurrence.put(number, 1);
			}
		}

		// Now our map is ready we need to iterate
		java.util.Iterator<java.util.Map.Entry<Integer, Integer>> iter = NoVersusOcurrence.entrySet()
				.iterator();

		while (iter.hasNext()) {
			java.util.Map.Entry<Integer, Integer> entry = iter.next();
			if (entry.getValue() == 1) {
				unique = entry.getKey();
				break;
			}
		}

		return unique;
	}
}
