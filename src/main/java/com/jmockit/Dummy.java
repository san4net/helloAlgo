package com.jmockit;

public class Dummy {
//	private static A a;
static Dummy instance = new Dummy();
	
/*	static{
		System.out.println("static in dummy");
		a = new A("");
	}
	*/
	public Dummy(){
		System.out.println("public cons");
	}
	
	public  static  Dummy getInstance(){
		System.out.println("sss");
		return instance;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Dummy){
		 return super.equals(obj);
		}
		return false;
	}

}
