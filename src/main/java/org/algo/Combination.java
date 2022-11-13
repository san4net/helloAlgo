package org.algo;

public class Combination {

	/**
	 * Finding all combination of given array i.e ncr where r=1 to n
	 *
	 *
	 * @param chars
	 * @param idx
	 * @param r
	 * @param l is index of temp <=r
	 * @param temp is word with r
	 */
	private static  void combination(char[] chars, int idx, int r, int l, char[]temp){
		if(l==r){
			System.out.println(new String(temp));
			return;
		}
		if(idx>=chars.length){
			return;
		}
		//include
		temp[l]=chars[idx];
		combination(chars, idx+1, r, l+1, temp);
		//this is back track since we are using char array can do this by simply using index l
		//exclude index idx
		combination(chars, idx+1, r, l, temp);
	}



	public static void main(String[] args) {
		combination("ABCDE".toCharArray(), 0,2, 0, new char[2]);
	}
}
