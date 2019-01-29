package com.solid.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherStationSubject implements Subject {
	private int temperature;
	private int humidity;
	List<Observer> observerList;
	
	
	
	public WeatherStationSubject() {
		super();
		this.observerList = new ArrayList<>();
	}

	@Override
	public void addObserver(Observer observer) {
		observerList.add(observer);

	}

	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		observerList.remove(observer);

	}

	@Override
	public void notifyObserver() {
		observerList.forEach((o)->{o.update(temperature, humidity);});
	}

	@Override
	public void setTemperature(int temperature) {
		this.temperature = temperature;
		notifyObserver();
	}

	@Override
	public void setHumidity(int humidity) {
		this.humidity = humidity;
		notifyObserver();
	}
	

}
