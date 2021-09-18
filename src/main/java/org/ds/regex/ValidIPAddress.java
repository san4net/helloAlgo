package org.ds.regex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class ValidIPAddress {
	
	public static String regex = 
			 "^([01]?\\d\\d?| 2[0-4]\\d | 25[0-5]\\d)\\."
			+ "([01]?\\d\\d?| 2[0-4]\\d | 25[0-5]\\d)\\."
			+ "([01]?\\d\\d?| 2[0-4]\\d | 25[0-5]\\d)\\."
			+ "([01]?\\d\\d?| 2[0-4]\\d | 25[0-5]\\d)$";
	
	public static boolean validIP(String ip) {
		return Pattern.compile(regex).matcher(ip).matches();
	}
	
	public static void main(String[] args) {
		System.out.println(validIP("172.16.254.1"));
		System.out.println( countTriplets(new int[] {2,3}));
		
		int edges[][] = new int[][] {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}}; 
//		boolean[] hasApple = {false,false,true,false,true,true,false};
		System.out.println(minTime(7, edges, Arrays.asList(false,false,true,false,true,true,false)));
				
	}
	
	public static int countTriplets(int[] arr) {
		if(arr.length<=1) return 0;
		
		int count = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				for (int k = j; k < arr.length; k++) {
					if (xor(i, j - 1, arr) == xor(j, k, arr)) {
						count++;
					}
				}

			}
		}

		return count;
	}
	
	public static int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        
		Map<Integer, List<Integer>> map = new HashMap<>();
		buildTree(edges, map);
		Set<Integer> visited = new HashSet<>();
		return helper(0, map, hasApple, visited);
	}
	
	private static int helper(int node, Map<Integer, List<Integer>> map, List<Boolean> hasApple, Set<Integer> visited) {
		visited.add(node);
		int r=0;
		
		for(int e: map.getOrDefault(node, new LinkedList<>())) {
			  
			 if(visited.contains(e)) {
				 continue;
			 }
			 r = r+ helper(e, map, hasApple, visited);
		}
		
		if((r>0 || hasApple.get(node)) && (node!=0) )
			 r=r+2;
		return r;
	}

	private static void buildTree(int[][] edges, Map<Integer,List<Integer>> m) {
		
		for(int[] t: edges) {
			m.putIfAbsent(t[0], new LinkedList<>());
			m.get(t[0]).add(t[1]);
		}
	}

	private static int xor(int s, int e, int[] arr) {
		int r = arr[s];
		for (int i=s+1; i <= e; i++) {
			r=r^arr[i];
		}
		return r;
	}
}
