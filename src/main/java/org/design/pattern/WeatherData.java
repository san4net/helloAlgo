package org.design.pattern;

import java.util.Observable;

public class WeatherData extends Observable {
private float temperature;
private float humidity;
private float pressure;

public WeatherData(){
}

public void measurementChanged(){	
	setChanged();
	notifyObservers();
}

public void setMeasurement(float t, float h, float p){
temperature = t;
humidity =h;
pressure = p;
measurementChanged();
}
}
