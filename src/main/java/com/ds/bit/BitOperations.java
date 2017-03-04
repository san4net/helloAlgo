package com.ds.bit;

import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

public class BitOperations {
	public static int findNearestPowerofTwo(int n) {
		// if it is power of two then its AND with number 1 less will be zero
		if ((n & n - 1) == 0)
			return n;
		int a = 1;
		while (a < n) {
			a = a << 1;
		}
		return a >> 1;
	}

	public static int maxXor() {
		int l = 5, h = 6;
		int max = 0;
		for (int b = l; b < h; b++) {
			for (int a = b; a <= h; a++) {
				int t = b ^ a;
				System.out.println(t);
				if (t > max) {
					max = t;
				}
			}
		}
		System.out.println(max);
		return max;
	}

	public static void main(String[] args) throws InterruptedException {
    int a = 4; 
     System.out.println(a>>>1);
     System.out.println(a>>1);
     System.out.println(a);
	}
}
