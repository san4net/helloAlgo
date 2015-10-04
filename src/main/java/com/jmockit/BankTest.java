package com.jmockit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mockit.Expectations;
import mockit.Mocked;

public class BankTest {

	@Mocked
	DBManager db; // mocked by default
	
	@Mocked
	Person p;
	@Mocked 
	Dummy d;

	@Test
	public void testprocessAccount(){
//		Person p = new Person("san");
		Bank b = new Bank();
		
		new Expectations() {
			{
			  db.retrieveAccountHolderName("san");
			  returns("san");
			  
			}
		
		};
		  
		 String acholder = b.processAccount("san");
		 System.out.println(acholder);
		 assertEquals("Name of person is san", acholder,"san");
		 
	}
	
	
}
