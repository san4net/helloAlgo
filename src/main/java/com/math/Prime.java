
package com.math;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class Prime{

  static int end = (int) Math.sqrt(10001d);
	static int start = 2;
	static int[] primes;
	
	/** 
	 * Logic
	   To generate prime from range x to y.(2 to 10000)
	  start with number say x for example'2' 
	  1. keep remove all number divisible by x (don't remove x)
	  2. Get next available number x and repeat step 1
   
     Refer to https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
	 * 
	 */
	public static void generatePrimes() {
    int[] f = IntStream.range(2, 10001).toArray();
		while (start <= end) {
			  f = IntStream.of(f).filter((int i) -> {
					if (i == start)
						return true;
					return (i % start != 0);
				}).toArray();
			start++;
		}
	 primes = f;
	 display(f);
	}
  
	public static void display(int[] arr) {
	  for(int a: arr) {
		  System.out.print(a+":");
	  }
	}
  }
