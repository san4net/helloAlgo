package org.algo.dp;

import java.util.ArrayList;
import java.util.List;

public class BinaryKnapsack {
	/*
	 * Given coins with same benefits and we can have W weight
	 * 
	 */

	public void knapSack(int conference, int[] lectures) {
		int[][] lecturesM = new int[lectures.length + 1][conference + 1];
		int[] destLecuture = new int[lectures.length + 1];

		System.arraycopy(lectures, 0, destLecuture, 1, lectures.length);

		destLecuture[0] = 0;

		int row = lectures.length; // one extra
		// if conferene limit is 0 we cann' take any lecture
		for (int i = 0; i <= row; i++) {
			lecturesM[i][0] = 0;
		}
		// if lecture duration is 0 we can't take any confere
		for (int i = 0; i <= conference; i++) {
			lecturesM[0][i] = 0;
		}

		// for each lecture
		for (int a = 1; a < lecturesM.length; a++) {
			// for each incremental conferences
			for (int b = 1; b <= conference; b++) {

				if (destLecuture[a] > b) {
					lecturesM[a][b] = lecturesM[a - 1][b];
				} else {
					lecturesM[a][b] = Math.max(destLecuture[a] + lecturesM[a - 1][b - destLecuture[a]],
							lecturesM[a - 1][b]);
				}

			}

		}
		// lets print it
		for (int i = 0; i < lecturesM.length; i++) {
			int[] a = lecturesM[i];
			for (int j = 0; j < a.length; j++) {
				System.out.print(lecturesM[i][j] + ":");
			}
			System.out.println();
		}

		// finding what are included
		// simply traverse last column of the matrix in case it is same as
		// previous
		// then it is not included
		// int[][] lecturesM = new int[lectures.length+1][conference+1];
		List<Integer> included = new ArrayList<>();
		int weightUnderConsideration = conference;
		for (int j = lecturesM.length - 1; j > 0; j--) {
			if (lecturesM[j][weightUnderConsideration] != lecturesM[j - 1][weightUnderConsideration]) {
				// then it is not included
				included.add(destLecuture[j]);
				weightUnderConsideration = weightUnderConsideration - destLecuture[j];
			}
		}
		System.out.println(included);
	}

	public static void main(String[] args) {
		new BinaryKnapsack().knapSack(4, new int[] { 1, 2, 3, 5 });
	}
}
