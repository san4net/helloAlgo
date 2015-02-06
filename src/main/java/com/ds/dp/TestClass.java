package com.ds.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TestClass {

	public TestClass() {
		// TODO Auto-generated constructor stub

	}

	public static long sumEachDigit(long num) {
		if (num < 10)
			return num;
		else {
			return num % 10 + sumEachDigit(num / 10);
		}
	}
  
	private static Map<Long, Long> factorialMap = new HashMap<>();
	
	static {
		factorialMap.put(0l, 0l);
		factorialMap.put(1l, 1l);
	}
  
	public static long rSum(long num) {
		int result = 0;
		while (num >= 10) {
			num = sumEachDigit(num);
		}
		return num;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);

		// algo
		// 1. find factorial
		// 2. find rsum of each factorial
		// 3. sum rsum's

		for (int i = 1; i <= N; i++) {
			long sum = 0;
			String inputLine = br.readLine();
			String range[] = inputLine.split(" ");
			long min = Long.parseLong(range[0]);
			long max = Long.parseLong(range[1]);

			for (long j = min; j <= max; j++) {
				long factorial = getFactorial(j);
				sum = sum + rSum(factorial);
			}
			System.out.println(sum);
		}

	}

	public static long getFactorial(long n) {
		if (n <= 1)
			return factorialMap.get(n);
		else
			 if(factorialMap.get(n) == null){
				 long factorial = n * getFactorial(n-1);
				 factorialMap.put(n, factorial);
			 }
		
			return factorialMap.get(n);
	}
}
