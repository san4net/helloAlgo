package com.algo.dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * Alice is a kindergarden teacher. She wants to give some candies to the
 * children in her class. All the children sit in a line ( their positions are
 * fixed), and each of them has a rating score according to his or her
 * performance in the class. Alice wants to give at least 1 candy to each child.
 * If two children sit next to each other, then the one with the higher rating
 * must get more candies. Alice wants to save money, so she needs to minimize
 * the total number of candies given to the children.
 * 
 * Input Format
 * 
 * The first line of the input is an integer N, the number of children in
 * Alice's class. Each of the following N lines contains an integer that
 * indicates the rating of each child. 3 Rank: 1 2 2 candies 1 2 1 so 4
 * 
 * @author santosh
 *
 */

public class Candies {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("e:\\input_candies2.txt"));
		Integer n = Integer.valueOf(br.readLine().trim());

		while (n > 0) {
			int each = Integer.valueOf(br.readLine().trim());
			String[] rank = new String[each];
			int i = 0;
			while (each > 0) {
				rank[i++] = br.readLine().trim();
				each--;
			}
			printCandies(rank);
			n--;
		}

	}

	private static void printCandies(String[] rank) {
		int r[] = Arrays.asList(rank).stream().mapToInt(Integer::parseInt).toArray();
		int c[] = distributeCandies(r.length, r);
		long sum = 0;
		for (int i = 0; i < c.length; i++) {
			sum = sum + c[i];
		}
		System.out.println(sum);
	}

	private static int[] distributeCandies(int n, int[] r) {
		int candies[] = initiaAndPopulateLocalMin(n, r);
		int index = -1;
		for (int i = 0; i < n; i++) {
			if (candies[i] != 0) {
				index = i;
				break;
			}
		}
		// traverse from index to left and rigth
		for (int i = index - 1; i > -1; i--) {
			candies(i, r, candies);
		}
		for (int i = index + 1; i < r.length; i++) {
			candies(i, r, candies);
		}

		return candies;
	}

	private static void populate(int index, int[] r, int[] c) {
		if (c[index] != 0)
			return;
		else {
			//
			if (isLocalMax(index, r)) {
				if (index != 0 && index != r.length - 1) {
					populate(index - 1, r, c);
					populate(index + 1, r, c);
					// here we need to do something
					c[index] = c[index - 1] > c[index + 1] ? c[index - 1] + 1 : c[index + 1] + 1;
				} else if (index == 0) {
					populate(index + 1, r, c);
					c[index] = c[index + 1] + 1;
				} else if (index == r.length - 1 && c[index - 1] != 0) {
					populate(index - 1, r, c);
					c[index] = c[index - 11] + 1;
				}
			} else {
				if (isLocalMin(index, r)) {
					c[index] = 1;
				} else {
					// not local min not local max
					// this index is not local max so
					if (r[index] > r[index - 1]) {
						populate(index - 1, r, c);
						c[index] = c[index - 1] + 1;

					} else {
						populate(index + 1, r, c);
						c[index] = c[index + 1] + 1;
					}

				}
			}
		}
	}

	static void candies(int index, int[] r, int[] c) {
		if (c[index] != 0) {
			return;
		}
		// this case it should be greter than both
		if (isLocalMax(index, r)) {
			if (index != 0 && index != r.length - 1) {
				populate(index - 1, r, c);
				populate(index + 1, r, c);
				// here we need to do something
				c[index] = c[index - 1] > c[index + 1] ? c[index - 1] + 1 : c[index + 1] + 1;
			} else if (index == 0) {
				populate(index + 1, r, c);
				c[index] = c[index + 1] + 1;
			} else if (index == r.length - 1 && c[index - 1] != 0) {
				populate(index - 1, r, c);
				c[index] = c[index - 1] + 1;
			}
		} else {
			if (isLocalMin(index, r)) {
				c[index] = 1;
			} else {
				// not local min not local max
				// this index is not local max so
				if (r[index] > r[index - 1]) {
					populate(index - 1, r, c);
					c[index] = c[index - 1] + 1;
				} else {
					populate(index + 1, r, c);
					c[index] = c[index + 1] + 1;
				}

			}
		}
	}

	private static int[] initiaAndPopulateLocalMin(int n, int[] r) {
		int c[] = new int[n];
		for (int i = 0; i < n; i++) {
			if (isLocalMin(i, r)) {
				c[i] = 1;
			}
		}
		return c;
	}

	public static boolean isLocalMin(int index, int[] r) {
		if (index == 0) {
			return (r[index + 1] >= r[index]);
		}
		if (index == r.length - 1) {
			return (r[index - 1] >= r[index]);
		}

		return r[index - 1] >= r[index] && r[index] <= r[index + 1];
	}

	private static boolean isLocalMax(int index, int[] r) {
		if (index == 0) {
			return r[index] > r[index + 1];
		}
		if (index == r.length - 1) {
			return r[index] > r[index - 1];
		}
		return r[index] > r[index + 1] && r[index] > r[index - 1];
	}
}
