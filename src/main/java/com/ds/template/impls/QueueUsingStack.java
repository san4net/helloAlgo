package com.ds.template.impls;

import com.core.Stack;
import com.ds.impls.StackImpl;

public class QueueUsingStack<T> {

	private final Stack<T> originalStack ;
	private final Stack<T> backUpStack ;
	
	/**
	 * 
	 * @param size
	 */
	public QueueUsingStack(int size) {
		originalStack = new StackImpl<T>(size);
		backUpStack   = new StackImpl<T>(size);
	}
	
	/**
	 * expensive enque 
	 * 
	 * @param data
	 */
	public  void enqueue(T data){
		while(!originalStack.isEmpty()){
			backUpStack.push(originalStack.pop());
		}
		
	originalStack.push(data);
		//
 while(!backUpStack.isEmpty()){
		 originalStack.push(backUpStack.pop());
 }
		
	}
	
	public T dequeue(){
		return originalStack.pop();
	}
	
	public static void main(String[] args) {
		QueueUsingStack<Integer> queue  = new QueueUsingStack<Integer>(15);
		int i=1;
		while(i<5){
			queue.enqueue(i);
			i++;
		}
	 System.out.println(queue);
	 System.out.println(queue.dequeue());
	 System.out.println(queue.dequeue());
	 
	 System.out.println(queue);
	}
	@Override
	public String toString() {
		return originalStack.toString();
	}
}
