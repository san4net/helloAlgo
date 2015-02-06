package com.serialize;

import java.io.Serializable;

public class Father extends GrandPa  {
	private String fatherName;
 
	public Father() throws Exception{
			super();
}
	public String getName() {
		return fatherName;
	}

	public void setName(String name) {
		this.fatherName = name;
	}
	
	public static void main(String[] args) {
		try {
			Father f = new Father();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("sala ");
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Father [fatherName=" + fatherName 
				+ super.toString() + "]";
	}
	
	

}
