package com.serialize;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Son extends Father implements Serializable {

private String sonName;
	
  public Son() throws Exception {
	  System.out.println("son");
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
	
	public static void main(String[] args) 
			 {/*
		Son s = new Son();
		s.setSonName("beta");
		s.setName("baap");
		s.setGrandPaName("dada");
		FileOutputStream f = new FileOutputStream("abc");
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(s);
        System.out.println("son="+s);
		FileInputStream fileIn = new FileInputStream("abc");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Son s1 = (Son) in.readObject();
		System.out.println("son="+s1);
		in.close();
		fileIn.close();

	*/}
	/*@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeObject(sonName);
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		this.sonName = (String) in.readObject();
	}*/
	
}
