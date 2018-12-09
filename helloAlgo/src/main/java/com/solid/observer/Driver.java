package com.solid.observer;

public class Driver {

	public static void main(String[] args) {
		Subject subject = new WeatherStation();
		Observer weatherObserver = new WeatherObserver(subject);
		subject.setTemperature(12);
	}
}
