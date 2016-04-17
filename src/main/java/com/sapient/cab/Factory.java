package com.sapient.cab;

import java.util.Date;

public class Factory {

	public static Cab createCab(String cabNo, int currentLocation, int destinationLoaction){
		return new Cab(cabNo, currentLocation, destinationLoaction);
	}
	
	public static Booking createBooking(String bookingId, int pickupAreaCode, int dropAreaCode, Date pickupTime){
		return new Booking(bookingId, pickupAreaCode, dropAreaCode, pickupTime);
	}
	
}
