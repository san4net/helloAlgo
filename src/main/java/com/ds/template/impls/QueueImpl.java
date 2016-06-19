package com.ds.template.impls;

import java.util.concurrent.atomic.AtomicLong;

public class QueueImpl<T> implements com.ds.template.Queue<T> {

	private Node<T> head, tail;
	private String name;

	private AtomicLong size = new AtomicLong(0);

	private int limit;

	public QueueImpl() {
		this(15, "default");
	}
	
	public QueueImpl(int limit, String name) {
		super();
		head = tail = null;
		this.limit = limit;
		this.name = name;
	}

	private void incrementSize(int i) {
		size.getAndAdd(i);
	}
	
    public boolean isEmpty(){
    	return (size.intValue()==0);
    }
    
	@Override
	public synchronized boolean enqueue(T data) {
      if( data == null ) return false;
      
		while (size.intValue() == limit) {
			try {

				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

		if (head == null) {
			head = new Node<T>(data, null);
			tail = head;
			incrementSize(1);
		} else {
			Node<T> temp = new Node<T>(data, null);
			tail.next = temp;
			tail = temp;
			incrementSize(1);
		}
//		System.out.println("enqueing: size:" + size() + "queue" + name);
		this.notify();
		return true;
	}

	@Override
	public synchronized T dequeue() throws InterruptedException {

		while (head == null) {
			wait();
			return null;
		}
		T data = head.data;
		head = head.next;
//		System.out.println("dequeing: size: " + size() + "queue" + name);
		incrementSize(-1);
		this.notify();
		return data;
	}

	@Override
	public int size() {
		return size.intValue();
	}  

	private class Node<T> {
		T data;
		Node<T> next;

		public Node(T data, Node<T> next) {
			super();
			this.data = data;
			this.next = next;
		}

		@Override
		public String toString() {
			return "[data:" + data + "]" + next == null ? "" : next.toString();
		}
	}

    @Override
    public boolean isFull() {
        return false;
    }

}
