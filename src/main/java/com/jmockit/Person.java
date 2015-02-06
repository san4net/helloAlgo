package com.jmockit;

public class Person {
	private String name;
	   
	  
	 Person(){
	  this.name ="";
	 }
	  
	 public Dummy getName(Dummy a) {
	  return Dummy.getInstance();
	 }
	  
	 public void setName(String name) {
	  this.name = name;
	 }

}
