package com.me.ds.template;

public class Gcd {

	public Gcd() {
		// TODO Auto-generated constructor stub
	}
	public static int getGcdRecursive(int a, int b){
		if(b==0) return a;
		 return getGcdRecursive(b,  a%b);
	}
	
	public static int getGcd(int a, int b) {

		while (b > 0) {
			int c = b;
			b = a % b;
			a = c;
		}
		return a;
	}
	
	public static void main(String[] args) {
		System.out.println(getGcdRecursive(6, 4));
		System.out.println(getGcd(6, 4));
	}

}
