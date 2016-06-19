package com.ds.template.node;

public interface DoubleNode<K, V> {
	void setNext(DoubleNode<K, V> next);
	void setPrevious(DoubleNode<K, V> previous);
	public K getKey();
	public V getValue();
	public DoubleNode<K,V> getNext();
	public DoubleNode<K,V> getPrevious();
	public void setKey(K key);
	public void setValue(V value);
	
}
