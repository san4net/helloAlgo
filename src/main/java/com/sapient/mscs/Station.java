package com.sapient.mscs;

public interface Station {
	void swipeIn();
	void swipeOut();
	long inCount();
	long outCout();
	int index();
}
