package com.event;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BSerial extends ASerial implements Serializable {
	private static final long serialVersionUID = -932957237953861221L;
    
	public BSerial(String name) {
		super(name);
	}



	public static void main(String[] args) {
//		ObjectOutputStream 
		 BSerial e = new BSerial("santi serial");
	      try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("employee.ser");
	         ObjectOutputStream out =  new ObjectOutputStream(fileOut);
	         out.writeObject(e);
	         out.close();
	          fileOut.close();
	  }catch(IOException i)
      {
          i.printStackTrace();
      }
	      e.deserialize();
	}
	
	public void deserialize(){
		 try
	      {
	         FileInputStream fileIn =
	                          new FileInputStream("employee.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         BSerial e = (BSerial) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found.");
	         c.printStackTrace();
	         return;
	      }
	}
}
	
