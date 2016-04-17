package com.sapient.mscs.impl;

import com.sapient.mscs.Card;
import com.sapient.mscs.Station;
import com.sapient.mscs.Trip;

public class TripImpl implements Trip {
	private Card card;
	private Station start;
	private Station end;
	
	public TripImpl(Station start) {
		super();
		this.start = start;
	}
	
	//
	@Override
	public void start(Station station, Card card) {
	this.start = station;
	this.card = card;

	}

	@Override
	public void end(Station station, Card card) {
	this.end = station;
	this.card = card;
	}
	

	@Override
	public String toString() {
		return "TripImpl [source_Station=" + start + ", destination_station=" + end + "]";
	}
	
	static Trip startNewTrip(Station station){
		return (Trip) new TripImpl(station);
	}

	@Override
	public Station startStation() {
		return start;
	}

	@Override
	public Station endStation() {
		return end;
	}
	
}
