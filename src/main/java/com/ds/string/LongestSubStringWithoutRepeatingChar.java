package com.ds.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * <pre>
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Input: "abcdamn"
Output: 3 
Explanation: The answer is "bcdamn", with the length of 6.
 * </pre>
 * 
 * @author santoshkumar
 *
 */
public class LongestSubStringWithoutRepeatingChar {

	private static int longestSubstring(String input) {
		int start = 0;
		int max = 0;
		Map<Character, Integer> map = new HashMap();

		for (int i = 0; i < input.length(); i++) {
			if (map.get(input.charAt(i)) != null) {
				if(map.get(input.charAt(i)) +1>start) {
					start=map.get(input.charAt(i)) +1;
				}
			}

			map.put(input.charAt(i), i);

			max = i - start + 1 > max ? i - start + 1 : max;

		}
		return max;

	}
	
	public static void main(String[] args) {
		System.out.println(longestSubstring("abcabcd"));
		System.out.println(longestSubstring("abcdamn"));
	}

}
