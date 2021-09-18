package com.algo.dp;

public class MaxPathInTriangle {
	  /**
	   * Recursive solution not good as time complexity is exponential 2 power n -1 
	   * 
	   * <pre>
	   	 3
		7 4
	   2 4 6  
	  8 5 9 3
	   </pre> 
	   * 
	   * @param triangle
	   * @param maxLevel
	   * @param level
	   * @param sx
	   * @param sy
	   * @return
	   */
	
	public static int maxPath(int[][] triangle, int maxLevel, int level, int sx, int sy) {
		if (level == maxLevel) {
			return triangle[sx][sy];
		}

		int localMax = Math.max(maxPath(triangle, maxLevel, level+1, sx+1, sy ),
								maxPath(triangle, maxLevel, level+1, sx + 1, sy + 1));

		return localMax + triangle[sx][sy];
	}
	
	/**
	 * using DP
	 *   
	     3
		7 4
	   2 4 6 
	   
	   	  3
		11 10  // summing max of below
		
		14     // this is answer

	 * @param triangle
	 * @param maxLevel
	 * @return
	 */
	public static int maxPath(int[][] triangle, int maxLevel) {
		for(int i = maxLevel-1; i>=0; i--){
			 for(int j=0; j<=i ; j++){
				 triangle[i][j] = triangle[i][j] + Math.max(triangle[i+1][j], triangle[i+1][j+1]);
			 }
			 
		}
		
		return triangle[0][0];
	}
	public static void main(String[] args) {
		int t[][] = {
				{55},
				{94,48},
				{95 ,30 ,96},
				{77 ,71 ,26, 67},
//				{77 ,71 ,26, 67,22,},
//				{77 ,71 ,26, 67,25,99}
				};
	
	System.out.println(maxPath(t, 3, 0, 0, 0));
	System.out.println(maxPath(t, 3));

	}
}
