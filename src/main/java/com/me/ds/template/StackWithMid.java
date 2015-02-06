package com.me.ds.template;
/**
 * 
 * 	
Design a stack with operations on middle element
June 8, 2013

How to implement a stack which will support following operations in O(1) time complexity?
1) push() which adds an element to the top of stack.
2) pop() which removes an element from top of stack.
3) findMiddle() which will return middle element of the stack.
4) deleteMiddle() which will delete the middle element.
Push and pop are standard stack operations
 * 
 * @author santosh kumar
 * @param <T>
 */
public class StackWithMid<T> {
	private Node<T> head;
	private Node<T> mid;
	private volatile int count;
	
	
	public synchronized void push(T data){
		
	}

}

class LinkedNode<T>{
	T data;
	Node<T> next;
	Node<T> prev;
	
	public LinkedNode(T data, Node<T> next, Node<T> prev) {
		super();
		this.data = data;
		this.next = next;
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "Node [data=" + data +"]"+ next == null ?"":super.toString();
	}
	
	
}
