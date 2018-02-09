package com.inner;

public class Combination {

	static void combination(char[] input, int index){
		if(index<0){
		  System.out.println(new String(input));	
		  return;
		}
		
		if(input.length ==1 ){
			System.out.println(new String(input));
			return;
		}
		
		// either we include or exclude
		combination(input, index-1);
		// remove index
		char[] removeInput = removeAndReturnArray(input, index);
		combination(removeInput, index-1);
	}
	
	static char[] removeAndReturnArray(char[] in, int index){
		char[] r = new char[in.length-1];
		for(int i =0,j=0; i<in.length;i++){
			if(i==index) continue;
			r[j++]=in[i];
		}
		return r;
	}
	
	public static void main(String[] args) {
		combination("ABCE".toCharArray(), 3);
	}
}
