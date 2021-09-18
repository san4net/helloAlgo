package org.ds.template.node;

public interface SingleNode<T> extends Node<T> {
	SingleNode<T> getNext();
	void setNext(SingleNode<T> node);
}
