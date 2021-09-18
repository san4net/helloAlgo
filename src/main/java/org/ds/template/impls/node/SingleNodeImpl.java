package org.ds.template.impls.node;

import java.util.List;

import org.ds.template.node.SingleNode;

public class SingleNodeImpl<T> implements SingleNode<T> {
   private T data;
   private SingleNode<T> next;

   public SingleNodeImpl(T data, SingleNode<T> next) {
	   this.data = data;
	   this.next = next;
	}

	public static void main(String[] args) {
		SingleNode<? extends Number> intNode = new SingleNodeImpl<>(3, null);
		intNode = new SingleNodeImpl<Number>(4.0, null);
		List<? extends Object> ab=null;
		List<String> abs=null;
		ab = abs;
		
		/*IntegerNode<Integer> intNode = new IntegerNode<Integer>(4, null);
		SingleNode snode = intNode;
		snode.setNext(new SingleNodeImpl<String>("s", null));
		System.out.println("ad");*/
	}
	
	
	@Override
	public T data() {
		return data;
	}

	@Override
	public SingleNode<T> getNext() {
		return next;
	}

	@Override
	public void setNext(SingleNode<T> next) {
		this.next = next;
	}

}
