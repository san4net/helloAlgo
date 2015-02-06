package com.me.ds.template;

public interface SingleNode<T> extends Node<T> {
	Node<T> getNext();
	
	void setNext(Node<T> node);

}
