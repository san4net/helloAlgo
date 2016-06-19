package com.ds.template.impls;

public class ConvertP {

	public static void convertP(StringBuilder input) {
		char[] c = input.toString().toCharArray();
		int mid = c.length / 2;
		int left = mid - 1;
		int right = mid + 1;

		while (left >= 0 && right < c.length) {
			if (c[left] == c[right]) {
				left--;
				if (left < 0) {

				}
				right++;
				if (right == c.length) {
					// copy everthing from left to 0 to right of
					for (int i = left; i >= 0; i--) {
						input.append(c[i]);
					}
					break;
				}
			} else {
				mid = mid + 1;
				// corner case
				left = mid - 1;
				right = mid + 1;
				if (mid == c.length - 1 || right == c.length - 1) {
					for (int i = left; i >= 0; i--) {
						input.append(c[i]);
					}
					break;
				}
			}
		}

	}

	public static void main(String[] args) {
		// StringBuilder param =new StringBuilder("abc");
		// convertP(param);
		// System.out.println(param);
		System.out.println(findPattern(0, 0, pattern.length(), text.length()));
	}

	public static String pattern = "abc*bcd";
	public static String text = "abcbcd";
	// test("ge?ks*", "geeksforgeeks");

	public static boolean findPattern(int patternIndex, int stringIndex, int patternSize, int stringSize) {
		if (patternIndex == patternSize - 1 && stringIndex == stringSize - 1) {
			return true;
		}
		if (patternIndex == patternSize) {
			return false;
		}
		if (stringIndex == stringSize) {
			return false;
		}

		if (pattern.charAt(patternIndex) == '*' && patternIndex + 1 != patternSize && stringIndex + 1 == stringSize) {
			return false;
		}

		if (pattern.charAt(patternIndex) == '*' && patternIndex + 1 == patternSize && stringIndex + 1 == stringSize) {
			return true;
		}
		if (pattern.charAt(patternIndex) == '?' || pattern.charAt(patternIndex) == text.charAt(stringIndex)) {
			return findPattern(patternIndex + 1, stringIndex + 1, patternSize, stringSize);
		}

		if (pattern.charAt(patternIndex) == '*') {
			// we have to case either 1. consider current character of string 2.
			// leave current char in string.
			return (findPattern(patternIndex + 1, stringIndex, patternSize, stringSize)
					|| findPattern(patternIndex, stringIndex + 1, patternSize, stringSize));

		}
		return false;
	}
}
