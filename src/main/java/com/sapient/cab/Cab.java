package com.sapient.cab;

public class Cab {

	private String CabNo;
	private int currentLocation;
	private int destinationLoaction = -1; // -1 mean there are free
	private Booking booking;

	public Cab(String cabNo, int currentLocation, int destinationLoaction) {
		super();
		CabNo = cabNo;
		this.currentLocation = currentLocation;
		this.destinationLoaction = destinationLoaction;
	}

	public String getCabNo() {
		return CabNo;
	}

	public void setCabNo(String cabNo) {
		CabNo = cabNo;
	}

	public int getCurrentLocation() {
		return currentLocation;
	}

	public int getDestinationLoaction() {
		return destinationLoaction;
	}

	public void setDestinationLoaction(int destinationLoaction) {
		this.destinationLoaction = destinationLoaction;
	}

	@Override
	public String toString() {
		return "Cab [CabNo=" + CabNo + ", currentLocation=" + currentLocation
				+ ", destinationLoaction=" + destinationLoaction + " booking "
				+ booking + "]";
	}

}
