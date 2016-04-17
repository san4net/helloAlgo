package com.sapient.mscs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
 
	public static boolean isWeekend(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return (dayOfWeek == 1 || dayOfWeek == 7);
	}
	
	public static void main(String[] args) throws ParseException {
		Date d = new SimpleDateFormat("dd/M/yyyy").parse("04/3/2016");
		System.out.println(d);
		System.out.println(isWeekend(d));	
//		System.out.println(stationCount(StationImpl.locateStation("A2"),StationImpl.locateStation("A1")));
	}
	
	public static int stationCount(Trip trip){
		int count =  trip.startStation().index()-trip.endStation().index();
		return count <0 ? -(count):count;
	}
	
	public static double tripCost(Trip trip){
		int stationCount = stationCount(trip);
		return stationCount * rate(new Date());
	}
	
	public static double rate(Date date){
		return Utils.isWeekend(new Date()) ? 5.5 : 7.0;
	}
}
