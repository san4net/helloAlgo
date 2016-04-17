package com.sapient.mscs.impl;

import java.util.ArrayList;
import java.util.List;

import com.sapient.mscs.Card;
import com.sapient.mscs.InSufficientFundException;
import com.sapient.mscs.Trip;
import com.sapient.mscs.Utils;


public class SmartCard implements Card {
	private String id ;
	private double balance;
	private Trip activeTrip;
	private List<Trip> trips = new ArrayList<>();

	public SmartCard(String id, double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}

	@Override
	public String uuid() {
		return id;
	}

	// 1.validation
	// 2. trip delegation
	// 3. station delegation
	@Override
	public void swipeIn(String location) {
		//
		if (!validate())
			throw new InSufficientFundException("insufficient fund current balance:" + balance);
		System.out.println("trip started @ ["+location+"]");
		StationImpl.locateStation(location).swipeIn();
		activeTrip = TripImpl.startNewTrip(StationImpl.locateStation(location));
		
	}

	// 1.validation
	// 2. trip delegation
	// 3. station delegation
	@Override
	public void swipeOut(String location) {
		// TODO Auto-generated method stub
		StationImpl.locateStation(location).swipeOut();
		activeTrip.end(StationImpl.locateStation(location), this);
		if(!validateOut()){
			throw new InSufficientFundException("insufficient fund current balance:" + balance + " trip cost is:" + Utils.tripCost(activeTrip));
		}
		System.out.println("trip ended @["+location + "] current cost"+Utils.tripCost(activeTrip) +" balance ["+balance+"]") ;
		
		deduct();
		// add to list
		trips.add(activeTrip);
		activeTrip = null;
	}

	@Override
	public synchronized void topUp(double amount) {
		balance = balance + amount;;
		System.out.println("toup "+amount +" balance after"+balance);
	}

	@Override
	public double balance() {
		return balance;
	}

	public boolean validate() {
		return Double.compare(balance, 5.5f) < 0 ? false : true;
	}
	
	public boolean validateOut(){
		double tripCost = Utils.tripCost(activeTrip);
		if(tripCost > balance) return false;
		return true;
	}
	
	private void deduct(){
		balance = balance - Utils.tripCost(activeTrip);
	}

	@Override
	public String toString() {
		return "SmartCard [id="+ id + ", balance=" + balance + ", trips=" + trips + "]";
//		return "SmartCard [id="+ id + ", balance=" + balance +  "]";
	}
	
}
