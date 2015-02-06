package com.jmockit;

import static org.junit.Assert.assertEquals;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class PersonTest {
   @Mocked 
	Person p;
   @Mocked
    Dummy d;
   
 /*  @Mocked
   A a;*/
   
   @Test
   public void testGetName() {
	   
	 /*  new Expectations() {
		   {
			   p.getName(d);
			   returns(new Dummy());
		   }
		   {
			   p.getName();
			   returns("santa");

		   }
	};*/
	   
	/*new MockUp<Dummy>() {
		@Mock
		public void $init(){
			
		}
	};*/
	
	new NonStrictExpectations() {
		{
			   p.getName(d);
			   returns(new Dummy());
		   }
	

	};
	
	Dummy d1 = new Dummy();
	Dummy d2 = p.getName(d1);
	assertEquals("Equal",d2 , d1);
	
	
   }
}
