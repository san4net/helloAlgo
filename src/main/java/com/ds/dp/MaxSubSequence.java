package com.ds.dp;

public class MaxSubSequence {
	
 static int maxsubsequence(char[] x1, char[] y1){
	 char[] x = new char[x1.length+1];
	 char[] y = new char[y1.length+1];
	 
	 for(int i=0;i<x1.length;i++){
		 x[i+1] = x1[i];
	 }
	 
	 for(int i=0;i<y1.length;i++){
		 y[i+1] = y1[i];
	 }
	 
	 int[][] lcs = new int[x.length][y.length];
	 // first initialization
	 // for if we take 0 from y and then all from x even then max subsequence =0
	 for(int i=0;i<x.length;i++){
		 lcs[i][0]=0;
	 }
	// for if we take 0 from x and then all from y even then max subsequence=0
	 for(int i=0;i<y.length;i++){
		 lcs[0][i]=0;
	 }
	 
	 for(int i=1;i<x.length;i++){
		 for(int j=1;j<y.length;j++){
			 if(x[i]==y[j]){
			 lcs[i][j] =  lcs[i-1][j-1]+1;	 
			 }
			 else{
				lcs[i][j] =  max(lcs[i-1][j], lcs[i][j-1]); 
			 }
		 }
		 
	 }
   
	 System.out.println("printing matrix");
	 
	 for(int i=0; i<lcs.length;i++){
		 for(int j=0; j<lcs[i].length;j++){
			 System.out.print(lcs[i][j]+" ");
		 }
		 System.out.print("\n");
	 }
	 return 0;
 }
 
 static int max(int i, int j){
	 return i>j?i:j;
 }
 
 public static void main(String[] args) {
//	 maxsubsequence("abc".toCharArray(), "abc".toCharArray());
	 maxsubsequence("adbavceb".toCharArray(), "abaceb".toCharArray());
 }
}
