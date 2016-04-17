package com.sapient.mscs;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sapient.mscs.impl.SmartCard;
import com.sapient.mscs.impl.StationImpl;

public class SmartCardTest {

	@BeforeClass
	public static void init(){
	  System.out.println("before class");
	}
	
	@Before
	public void setup(){
	  System.out.println("before");
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testSimpleTrip() {
		 Card card = new SmartCard("id1", 7);
		card.swipeIn("A1");
		card.swipeOut("A2");
		System.out.println(card);
		assertEquals(1.5f, card.balance());
	}
	
	@Test
	public void testInsuffiicentFundTrip() {
		Card card = new SmartCard("id2", 4);
		card.swipeIn("A1");
	}
	
	@Test
	public void testIssueInSwipeOut() {
		Card card = new SmartCard("id2", 11);
		card.swipeIn("A1");
		card.swipeOut("A10");
	}
	
	@Test
	public void testTopup() {
		Card card = new SmartCard("id3", 11);
		card.swipeIn("A1");
		card.topUp(40);
		card.swipeOut("A10");
	}
	
	@Test
	public void testRoundTrip() {
		Card card = new SmartCard("id1", 17);
		card.swipeIn("A1");
		card.swipeOut("A2");
		System.out.println(card);
		card.swipeIn("A2");
		card.swipeOut("A1");
		System.out.println(StationImpl.locateStation("A1").inCount());
		assertEquals(2, StationImpl.locateStation("A1").inCount());
	}
	
	@Test(expected=IllegalMonitorStateException.class)
	public void testSpeed() throws InterruptedException{
		int i =0;
		this.wait();
		assertEquals(0, 1);
	}
	
}
