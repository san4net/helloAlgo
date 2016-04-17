package com.sapient.mscs;

public interface Trip {
void start(Station station, Card card);
void end(Station station, Card card);
Station startStation();
Station endStation();
}
