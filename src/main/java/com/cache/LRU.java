package com.cache;

import java.util.HashMap;
import java.util.Map;

import com.ds.template.DoubleNode;
/**
 * 
 * @
 * 
 * @author santosh
 *
 * @param <K>
 * @param <V>
 */
public class LRU<K, V> {
	private DoubleNode<K, V> head = null;
	private DoubleNode<K, V> tail = null;
	private int capacity;
	private Map<K, DoubleNode<K, V>> map = new HashMap<>();

	public LRU(int capacity) {
		super();
		this.capacity = capacity;
	}

	public void remove(DoubleNode<K, V> node) {
		if (node.getPrevious() != null) {
			node.getPrevious().setNext(node.getNext());
		} else {
			// this is head node
			head = node.getNext();
		}

		if (node.getNext() != null) {
			node.getNext().setPrevious(node.getPrevious());
		} else {
			// this is tail node
			tail = node.getPrevious();
		}
		//
		node.setNext(null);
		node.setPrevious(null);

	}

	private void setHead(DoubleNode<K, V> node) {
		if (head == null) {
			head = node;
			tail = node;
		} else {
			node.setNext(head);
			head.setPrevious(node);
			node.setPrevious(head.getPrevious());
			head = node;
		}
	}

	private void removeAndBringToFron(DoubleNode<K, V> node) {
		remove(node);
		setHead(node);
	}

	public void set(K key, V value) {
		if (map.containsKey(key)) {
			DoubleNode<K, V> old = map.get(key);
			// remove it and bring to head
			old.setValue(value);
			remove(old);
		} else {
			// create new enty
			DoubleNode<K, V> created = new DoubleNodeImpl<>(key, value, null, null);
			if (map.size() == capacity) {
				// remove tail
				map.remove(tail.getKey());
				//
				remove(tail);
				setHead(created);
			} else {
				setHead(created);
			}
			map.put(key, created);
		}
	}

	public V get(K key) {
		if (map.containsKey(key)) {
			DoubleNode<K, V> node = map.get(key);
			// re
			remove(node);
			return node.getValue();
		}
		return null;
	}

	public static void main(String[] args) {
		LRU<Integer, Integer> lru = new LRU<>(5);
		for (int i = 1; i < 10; i++) {
			if (lru.get(i) == null) {
				lru.set(i, i);
			}
		}
		lru.display();
	}

	public void display() {
		DoubleNode<K, V> temp = head;
		while (temp != null) {
			System.out.println(temp.getValue());
			temp = temp.getNext();
		}
	}
}
