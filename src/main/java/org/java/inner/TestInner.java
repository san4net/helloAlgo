package org.java.inner;

public class TestInner {

	
	public static class StaticInner{
		static int  staticInnerCount =1;
		static{
			System.out.println("static inner : static block");
		}
		
		public static void display(){
		System.out.println(staticInnerCount);
		}
	}
	
	public  class Inner{
	}
	
	public static void main(String[] args) {
//		TestInner.StaticInner;
		
	}
}
