package com.ds.template.impls;

/* IMPORTANT: class must not be public. */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * The first line contains T, the number of test cases.
Each test case consist of 2 lines. First line of each test case
 Would contain N- total no. of buildings in the city. 
Second line contains N integers, the height of N buildings.
 * @author kumar
 *
 */

class TestClass {
	int testcase;
	static int[] inputArray;
	
    public static void main(String args[] ) throws Exception {
    	long water =0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
		for (int i = 0; i < N; i++) {
			String noOfBuilding = br.readLine();
			inputArray = new int[Integer.parseInt(noOfBuilding)];
			String heights = br.readLine();
			String[] heightsString = heights.split(" ");

			for (int j = 0; j < inputArray.length; j++) {
				inputArray[j] = Integer.parseInt(heightsString[j]);
			}

			int s = 0, e = inputArray.length - 1;
            water = getWater(s,e);
            
			while (s < e) {
				water = water + getWater(s++, e);
			}

			s = 0;
			while (s < e) {
				water = water + getWater(s, e--);
			}
			
			e=inputArray.length-1;
			while(s != e ){
				water = water + getWater(s++, e--);
			}
			System.out.println();
		}
        

        System.out.println(water);
    }
    
    public static int getWater(int s, int e){
    	int count=0;
    	for(int i = s+1; i < e; i++){
    		if(inputArray[s] > inputArray[i]  && inputArray[e] >inputArray[i]){
    			count++;
    		}else {
    			count =0;
    			break;
    		}
    	}
    	return count;
    }
}

