package com.sapient.cab;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class handle {@link Booking}  and {@link Cab} {@link BookingManager} Need a list of cabs to confirm 
 * booking i.e assigning a booking a to a cab
 * 
 * @author santosh kumar
 *
 */
public class BookingManager {
	private final int speed = 30;
	// Cab with destination -1 are assumed to be free to take up booking
	private final int FREE = - 1;
	private List<Cab> cabs = new ArrayList<Cab>();
	
	public BookingManager(List<Cab> cabs) {
		super();
		this.cabs = cabs;
	}
   
	/**
	 * 
	 * @param booking
	 * @return
	 */
	public synchronized Cab assignCab(Booking booking) {
		// list of avaialble cab apply filter
		// 1. time filter as we need to reach atleast 15 minute before
		Cab cab = findNearestCab(applyTimeFilter(getFreeCab(), booking), booking);
		booking.setCab(cab);
		postBookingTask(cab, booking);
		return cab;
	}
 
	private void postBookingTask(Cab cab, Booking booking){
		cab.setDestinationLoaction(booking.getDropAreaCode());
	}

	private List<Cab> applyTimeFilter(List<Cab> cabList, Booking booking) {
		Date pickupDate = booking.getPickupTime();
		Calendar bookingPickupTime = Calendar.getInstance();
		bookingPickupTime.setTime(pickupDate);
		bookingPickupTime.add(Calendar.MINUTE, -15);

		List<Cab> eligibleCab = new ArrayList<>();
		for (Cab cab : cabList) {
			if (timeCabWillReach(cab, booking).before(bookingPickupTime.getTime())) {
				eligibleCab.add(cab);
			}
		}
		return eligibleCab;
	}
	
	private List<Cab> getFreeCab(){
		// all cab having destination as -1 
		List<Cab> availiableCab = new ArrayList<>();
		
		for(Cab cab: cabs){
			if(cab.getDestinationLoaction() == FREE){
				availiableCab.add(cab);
			}
		}
		
		return availiableCab;
	}

	// Nearest cab will be having maximum profit

	private Cab findNearestCab(List<Cab> cabs, Booking booking) {
		// initializing with max to find the cab nearest 
		int delta = Integer.MAX_VALUE;
		booking.getPickupAreaCode();
		Cab selectedCab = null;

		for (Cab cab : cabs) {
			if (Math.abs(booking.getPickupAreaCode() - cab.getCurrentLocation()) < delta) {
				delta = Math.abs(booking.getPickupAreaCode()
						- cab.getCurrentLocation());
				selectedCab = cab;
			}
		}

		return selectedCab;
	}

	private static Date timeCabWillReach(Cab cab, Booking booking) {
		int exactDistance = Math.abs(booking.getPickupAreaCode()
				- cab.getCurrentLocation()) * 2;
		// cab speed is 30 kmph
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, (exactDistance * 60 / 30));
		return cal.getTime();
	}

	
	public static void main(String[] args) {
      List<Cab> cabs = new ArrayList<>();
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
      
      BookingManager manager = new BookingManager(cabs);
      
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.HOUR, 1);
      Cab cab = manager.assignCab(Factory.createBooking("B121", 100280, 100059, cal.getTime()));
      
      System.out.println("choosen cab " + cab);
	}
}
