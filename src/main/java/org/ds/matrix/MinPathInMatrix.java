package org.ds.matrix;
/**
 * 
 * var a = [
	        [1,4,5],
	        [2,7,6],
	        [4,2,7]
      	   ]
    we need to go from [0,0] to [3.3] and
    <li> only valid move is right
    <li> And down from any given position
    we need to find min path cost
 * 
 * @author santoshkumar
 *
 */
public class MinPathInMatrix {
	
	/*
	 * We traverse whole matrix
	 * 1. any index u can come from two way
	 * 2. from top i.e for current index [x][y] we check for [x][y-1] {given y-1>0}
	 * 3. from right i.e for current index [x][y] we check for [x-1][y] {given x-1>0}
	 * 
	 * 
	 */
	public static int findMinCost(int[][] matrix) {
		
		// we need to update min cost at every iteration 
		
		for(int row=0; row<matrix.length; row++) {
			
			for(int column=0; column<matrix.length; column++) {
				
				if(row==0 && column==0) {
					continue;
				}
				//from given x and y axis we can go to two different place
				//1. right i.e x++
				else if(column-1>=0 && row-1 >=0) {
					matrix[row][column] = matrix[row][column] +  Math.min(matrix[row][column-1], matrix[row-1][column]);
				}
				
				else if(column-1 < 0) {
					// means we need to consider only coming from up
					matrix[row][column] = matrix[row][column] + matrix[row-1][column];
				}
				
				else if(row-1<0) {
					// means we need to consider only coming from right
					matrix[row][column] =matrix[row][column] + matrix[row][column-1];
				}
			}
		}
		
		return matrix[matrix.length-1][matrix.length-1];
	}

	public static void main(String[] args) {
		int[][] a = {
	        {1,4,5},
	        {2,7,6},
	        {4,2,7}	
		};
		
		System.out.println(findMinCost(a));
	}

	
	
}
