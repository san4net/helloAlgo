package com.ds.template;

public interface Queue<T> {

	boolean enqueue(T data);

	T dequeue() throws InterruptedException;

	int size();

	boolean isEmpty();

	boolean isFull();
}
