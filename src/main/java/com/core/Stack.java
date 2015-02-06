package com.core;

public interface Stack<T> {
	void push(T element);

	T pop();

	int size();

	boolean isEmpty();

	String toString();
	
	void clear();
}
