package com.annot;


@MyVersion(provideDoc = "3")
public class TestAnnotation {
	
  int  name;
	
//  @MyVersion(provideDoc = "1")

	private void printName(String name){
		System.out.println(name);
		TestAnnotation.class.getAnnotations();
	}
	
	public static void main(String[] args) {
		TestAnnotation ta = new TestAnnotation();
		ta.printName("dd");
	}
	
}
