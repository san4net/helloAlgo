package org.solid.observer;

public interface Subject {
	
	void addObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObserver();
	void setTemperature(int i);
	void setHumidity(int i);
	

}
