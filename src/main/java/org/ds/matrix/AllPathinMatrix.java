package org.ds.matrix;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/*
 * Given maze and we need to go from top 
 *  maze[R][C] = 
       {{0,  0, 0, 0},
        {0, -1, 0, 0},
        {-1, 0, 0, 0},
        {0,  0, 0, 0}};
   */
public class AllPathinMatrix {
		

	public static int findAllPossiblePath(int[][] mat) {

		if (mat[0][0] == -1)
			return 0;

		// first we can update possible path on first row

		for (int i = 0; i < mat.length; i++) {
			if (mat[0][i] == 0) {
				mat[0][i] = 1;
			} else {
				break;
			}
		}

		// first column
		for (int i = 0; i < mat.length; i++) {
			if (mat[i][0] == 0) {
				mat[i][0] = 1;
			} else {
				break;
			}
		}

		// now we can traverse from from 1 ,

		for (int r = 1; r < mat.length; r++) {
			for (int c = 1; c < mat.length; c++) {
				//if blockage is found
				 if (mat[r][c]== -1) {
					continue;
				 }
				
				 if(mat[r-1][c]>0) {
					 mat[r][c]= mat[r][c] + mat[r-1][c];
				 }
				 if(mat[r][c-1]>0) {
					 mat[r][c]= mat[r][c] + mat[r][c-1];
				 }
				 
			}
		}
		
		return mat[mat.length-1][mat.length-1];

	}
	
	public static void main(String[] args) {
		int[][] maze ={{0,  0, 0, 0},
		        	   {0, -1, 0, 0},
		        	   {-1, 0, 0, 0},
		        	   {0,  0, 0, 0}};
		
		System.out.println(findAllPossiblePath(maze));
		
		System.out.println(lengthOfLongestSubstring("abcabcd"));
		
	}
	
	public static int lengthOfLongestSubstring(String s) {
        // given string we need 
        int start=0,max=0;
        Map<Character, Integer> indexMap = new HashMap();
       
       for(int i=0;i<s.length();i++){
           
           if(indexMap.get(s.charAt(i))!=null){
               //
               if(indexMap.get(s.charAt(i))+1>start){
                   start = indexMap.get(s.charAt(i))+1;
               }
           }
           
            indexMap.put(s.charAt(i), i);
           
          max = i-start+1>max?i-start+1:max;
           
       }
       return max;
       
      
   }
}
