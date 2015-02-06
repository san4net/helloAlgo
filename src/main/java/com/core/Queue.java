package com.core;

public interface Queue<T> {

	boolean enqueue(T data);
	
	T dequeue() throws InterruptedException;
	
	int size();
}
