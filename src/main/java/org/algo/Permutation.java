package org.algo;

import java.util.Arrays;

public class Permutation {
  public void doPermute(char[] input, int start, int end){
	  if(start == end){
		  System.out.println(Arrays.toString(input));
	  }
	  else{

		  for(int i=start;i<=end;i++){
			  swap(input, start, i);
			  doPermute(input,start+1, end);
			  // back track
			  swap(input, start, i);
		  }
	  }
  }

private void swap(char[] input, int start, int i) {
	char a = input[start];
	input[start] = input[i];
	input[i] = a;
}

public static void main(String[] args) {
	char[] input = {'a','b','c','d'};
	Permutation p = new Permutation();
	p.doPermute(input, 0, 3);
}
  
}
