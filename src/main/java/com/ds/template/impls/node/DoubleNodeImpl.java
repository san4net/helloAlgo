package com.ds.template.impls.node;

import com.ds.template.node.DoubleNode;

public class DoubleNodeImpl<K,V> implements DoubleNode<K,V> {
	private K key;
	private V value;
	private DoubleNode<K,V> next;
	private DoubleNode<K,V> prev;
	
	
	public DoubleNodeImpl(K key, V value, DoubleNode<K, V> next, DoubleNode<K, V> prev) {
		super();
		this.key = key;
		this.value = value;
		this.next = next;
		this.prev = prev;
	}


	@Override
	public void setNext(DoubleNode<K,V> next) {
	this.next = next;	
	}


	@Override
	public void setPrevious(DoubleNode previous) {
		this.prev = previous;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "key="+key+"value="+value;
	}


	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return key;
	}


	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return value;
	}


	@Override
	public DoubleNode<K, V> getNext() {
		// TODO Auto-generated method stub
		return next;
	}


	@Override
	public DoubleNode<K, V> getPrevious() {
		// TODO Auto-generated method stub
		return prev;
	}


	@Override
	public void setKey(K key) {
		this.key = key;
	}

	@Override
	public void setValue(V value) {
		this.value = value;
	}
	public static void main(String[] args) {
		 
		try{
			int[] a = {1,2};
			int b = a[3];
		}catch(Exception e){
			
		}finally{
			System.out.println("here i am");
		}
	}
 
	
}
