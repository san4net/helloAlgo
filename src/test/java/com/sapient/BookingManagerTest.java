package com.sapient;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import org.sapient.cab.BookingManager;
import org.sapient.cab.Cab;
import org.sapient.cab.Factory;

public class BookingManagerTest {
	static List<Cab> cabs = new ArrayList<>();

	static{
	      cabs.add(Factory.createCab("DL0121", 100001, -1));
	      cabs.add(Factory.createCab("DL0122", 100002, -1));
	      cabs.add(Factory.createCab("DL0123", 100003, -1));
	      cabs.add(Factory.createCab("DL0124", 100004, -1));
	      cabs.add(Factory.createCab("DL0125", 100005, -1));
	      cabs.add(Factory.createCab("DL0126", 100006, -1));
	      cabs.add(Factory.createCab("DL0127", 100007, -1));
	      cabs.add(Factory.createCab("DL0128", 100008, -1));
	      cabs.add(Factory.createCab("DL0129", 100009, -1));
	      cabs.add(Factory.createCab("DL0130", 100010, -1));
	      cabs.add(Factory.createCab("DL0131", 100011, -1));
	      cabs.add(Factory.createCab("DL0132", 100012, -1));
	      cabs.add(Factory.createCab("DL0133", 100013, -1));
	      cabs.add(Factory.createCab("DL0134", 100014, -1));
	      
	 }

	@Test
	public void validCase(){
	      BookingManager manager = new BookingManager(cabs);
	      Calendar cal = Calendar.getInstance();
	      cal.add(Calendar.HOUR, 1);
	      Cab cab = manager.assignCab(Factory.createBooking("B121", 100010, 100059, cal.getTime()));
	      System.out.println("choosen cab " + cab);
	      String cabNo = cab.getCabNo();
		  assertEquals(cabNo, "DL0130");
		  
		  cal = Calendar.getInstance();
	      cal.add(Calendar.HOUR, 3);
	      cab = manager.assignCab(Factory.createBooking("B122", 100040, 100059, cal.getTime()));
	      System.out.println("choosen cab " + cab);
	      cabNo = cab.getCabNo();
		  assertEquals(cabNo, "DL0134");
	}
	
	/**
	 * Here distance is so long that reaching not posssible
	 */
	
	@Test
	public void cornerCase(){
		BookingManager manager = new BookingManager(cabs);
	      Calendar cal = Calendar.getInstance();
	      cal.add(Calendar.HOUR, 1);
	      Cab cab = manager.assignCab(Factory.createBooking("B122", 101210, 100059, cal.getTime()));
	      System.out.println("choosen cab " + cab);
		  assertEquals(cab, null);
		  
		  // as we are supposed to reach atleast 15 minute before
	      Cab cab1 = manager.assignCab(Factory.createBooking("B123", 100010, 100059, Calendar.getInstance().getTime()));
	      assertEquals(cab1, null);
	}
  
}
