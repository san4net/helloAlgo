package com.sapient.mscs.impl;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.sapient.mscs.Station;

public class StationImpl implements Station {
	private static HashMap<String, StationImpl> locator = new HashMap<>();
	
	static{
		locator.put("A1", new StationImpl("A1","stationA1",1));
		locator.put("A2", new StationImpl("A2","stationA2",2));
		locator.put("A3", new StationImpl("A3","stationA3",3));
		locator.put("A4", new StationImpl("A4","stationA4",4));
		locator.put("A5", new StationImpl("A5","stationA5",5));
		locator.put("A6", new StationImpl("A6","stationA6",6));
		locator.put("A7", new StationImpl("A7","stationA7",7));
		locator.put("A8", new StationImpl("A8","stationA8",8));
		locator.put("A9", new StationImpl("A9","stationA9",9));
		locator.put("A10", new StationImpl("A10","stationA10",10));
	}
	
	public static Station locateStation(String location){
		return (Station) locator.get(location);
	}
	
//	private String Id;
	private String name;
	private int index;
	private StationImpl(String id, String name, int index) {
		super();
		this.name = name;
		this.index = index;
	}
	
	AtomicLong inCount = new AtomicLong(0);
	AtomicLong outCount = new AtomicLong(0);
	
	@Override
	public void swipeIn() {
		inCount.incrementAndGet();
	}
	
	@Override
	public void swipeOut() {
		outCount.incrementAndGet();
	}

	@Override
	public long inCount() {
		return inCount.get();
	}

	@Override
	public long outCout() {
		return outCount.get();
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public String toString() {
		return "StationImpl [name=" + name + "]";
	}
	
	public  enum Month{
		Jan("jan"){
			String num(){
				return month;
				
			}
		};
		String month;
		Month(String j){
			this.month = j;
		}
		abstract String num();
	}
}
