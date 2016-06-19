package com.serialize;

import java.io.Serializable;

public class Son extends Father implements Serializable {

private String sonName;
	
  public Son()  {
	  //System.out.println("son");
}
	public String getSonName() {
		return sonName;
	}

	public void setSonName(String sonName) {
		this.sonName = sonName;
	}

	@Override
	public String toString() {
		return "Son [sonName=" + sonName +  super.toString()
				+ "]";
	}
	
	public void testOver(Integer a){
		System.out.println("son");
	}

	public void testOver(Double d){
		System.out.println("father");
	}
		/*Son s = null;
		try {
			s = new Son();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.setSonName("beta");
		s.setFatherName("baap");
		s.setGrandPaName("dada");
		FileOutputStream f = new FileOutputStream("abc");
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(s);
		System.out.println("son=" + s);
		FileInputStream fileIn = new FileInputStream("abc");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Son s1 = (Son) in.readObject();
		System.out.println("son=" + s1);
		in.close();
		fileIn.close();
		
		FileInputStream f1 = new FileInputStream("abc");
		ObjectInputStream incomong = new ObjectInputStream(f1);
		Object o = incomong.readObject();
	*/	 
	public static void main(String[] args) {
		String a = "abc";
	}
	/**@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(sonName);
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		this.sonName = (String) in.readObject();
	}**/
	
}
