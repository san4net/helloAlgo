package com.my.util.concurrent;

import java.util.ArrayList;
import java.util.List;

public class Test {
	enum TestEnum{
		JAN(1), FEB(2);
		private TestEnum(int monthNum) {
			this.n = monthNum;
		}
		int n =0;
		public int getN() {
			return n;
		}
		public void setN(int n) {
			this.n = n;
		}
		
	}
	
	
	public void test(TestEnum t){
	String firstString ="first";	String secondString ="second";
	
	System.out.println("First String="+firstString);
	System.out.println("Second String="+secondString);
	
	int firstLength = firstString.length();
	firstString = firstString+secondString;
	secondString = firstString.substring(0,firstLength);
	firstString = firstString.substring(firstLength,firstString.length());
	System.out.println("After Swap");
	System.out.println("First String="+firstString);
	System.out.println("Second String="+secondString);
	
	}
	
	public static void main(String[] args) {
		new Test().test(TestEnum.FEB);
	}

}
