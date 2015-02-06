package com.pattern.observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TheHindu implements Subject {
	private List<Observer> subscriber = new ArrayList<Observer>();
	private Date date;
    private String paperName;    
	public TheHindu(String paperName) {
	this.paperName = paperName;
	}
	@Override
	public void notifyObservers() {
		System.out.println("paper notifying susbscriber");
		for (Observer eachSub : subscriber) {
			eachSub.update(date);
		}
	}

	@Override
	public void registerObserver(Observer o) {
		subscriber.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		int i = subscriber.indexOf(o);
		subscriber.remove(i);
	}

	public void paperPublished(Date date) {
		this.date = date;
		notifyObservers();
	}
	@Override
	public String toString() {
		return super.toString();
	}
	
}
