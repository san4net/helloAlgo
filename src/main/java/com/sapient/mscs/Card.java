package com.sapient.mscs;

public interface Card {
String uuid();
void swipeIn(String location);
void swipeOut(String location);
void topUp(double amount);
double balance();
}
