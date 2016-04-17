package com.sapient.cab;

import java.util.Date;

public class Booking {
	private String bookingId;
	private int pickupAreaCode;
	private int dropAreaCode;
	private Date pickupTime;
	private Cab cab;

	public Booking(String bookingId, int pickupAreaCode, int dropAreaCode,
			Date pickupTime) {
		super();
		this.bookingId = bookingId;
		this.pickupAreaCode = pickupAreaCode;
		this.dropAreaCode = dropAreaCode;
		this.pickupTime = pickupTime;
	}

	public String getBookingId() {
		return bookingId;
	}

	public int getPickupAreaCode() {
		return pickupAreaCode;
	}

	public int getDropAreaCode() {
		return dropAreaCode;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	
	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", pickupAreaCode="
				+ pickupAreaCode + ", dropAreaCode=" + dropAreaCode
				+ ", pickupTime=" + pickupTime + "]";
	}

}
