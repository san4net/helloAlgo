package com.ds.template.node;

public interface Node<T> extends Comparable<Node<T>> {
	 T data();
	 
	 @Override
	default int compareTo(Node<T> o) {
		return 0;
	}
}
