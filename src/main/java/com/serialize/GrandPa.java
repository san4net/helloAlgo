package com.serialize;

import java.io.Serializable;

public class GrandPa implements Serializable {
	private String grandPaName;

	public GrandPa() throws Exception{
//		Exception e = new Exception();
		String e = null;
		e.charAt(0);
//		System.out.println("grandpa");
	}
	public String getGrandPaName() {
		return grandPaName;
	}

	public void setGrandPaName(String grandPaName) {
		this.grandPaName = grandPaName;
	}

	@Override
	public String toString() {
		return "GrandPa [grandPaName=" + grandPaName + ", getGrandPaName()="
				+ getGrandPaName() + "]";
	}
	
 
}
