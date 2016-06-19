package com.serialize;

public class Father extends GrandPa  {
	private String fatherName;
 
	public Father() {
			super();
}
	public String getName() {
		return fatherName;
	}

	public void setFatherName(String name) {
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
