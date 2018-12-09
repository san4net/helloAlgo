package com.solid.observer;

public class WeatherObserver implements Observer {
	
	private int temperature;
	private int humidity;
	private Subject subject;
	
	public WeatherObserver(Subject subject) {
		this.subject = subject;
		subject.addObserver(this);
	}
	
	@Override
	public void update(int temperature, int humidity) {
		this.temperature = temperature;
		this.humidity = humidity;
		show();

	}
	
	private void show() {
		System.out.println("temp:"+temperature +" humidity:"+humidity);
	}

}
