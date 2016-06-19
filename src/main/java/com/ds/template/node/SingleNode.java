package com.ds.template.node;

public interface SingleNode<T> extends Node<T> {
	SingleNode<? extends T> getNext();
	void setNext(SingleNode<T> node);
}
