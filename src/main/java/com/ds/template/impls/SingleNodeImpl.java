package com.ds.template.impls;

import com.ds.template.Node;
import com.ds.template.SingleNode;

public class SingleNodeImpl<T> implements SingleNode<T> {
   private T data;
   private Node<T> next;

   public SingleNodeImpl(T data, Node<T> next) {
	   this.data = data;
	   this.next = next;
	}

	@Override
	public T getData() {
	 return data;
	}

	@Override
	public Node<T> getNext() {
		return next ;
	}

	@Override
	public void setNext(Node<T> node) {
		this.next = node;
	}

}