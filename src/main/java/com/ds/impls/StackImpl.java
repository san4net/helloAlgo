package com.ds.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ds.template.Stack;

public class StackImpl<T> implements Stack<T> {
	private final List<T> elementArray;
	private final int maxSize;
	private volatile int tos = -1;
	private final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();

	public StackImpl(int maxSize) {
		super();
		this.maxSize = maxSize;
		elementArray = new ArrayList<T>(maxSize);
	}

	public StackImpl() {
		this(50);
	}

	@Override
	public void push(T element) {
		lock.lock();
		try {
			while (isFull()) {
				try {
					if(!notFull.await(30, TimeUnit.SECONDS)){
					System.out.println("stack full");
					}
				} catch (InterruptedException e) {
				}
			}
			insert(element);
			notEmpty.signalAll();

		} finally {
			lock.unlock();
		}
	}

	private void insert(T element) {
//		System.out.println("inserting " + element);
		elementArray.add(++tos, element);
	}

	@SuppressWarnings("unused")
	private T remove() {
		T tmp = elementArray.remove(tos--);
//		System.out.println("popping-" + tmp);
		return tmp;
	}

	public T pop() {
		T data = null;
		try {
			lock.lockInterruptibly();
			while (isEmpty()) {
				try {
					if (!notEmpty.await(30, TimeUnit.SECONDS)) {
						System.out.println("stack empty");
						return null;
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			data = remove();
			notFull.signalAll();
		} catch (InterruptedException e) {

		} finally {
			lock.unlock();
		}
		return data;
	}
	
	public boolean isEmpty(){
		return (tos == -1);
	}
	
	public boolean isFull(){
		boolean full = false;
		 try {
			lock.lock();
			 full = (tos+1 == maxSize);
		 } catch (Exception e) {
			e.printStackTrace();
		 }finally{
			lock.unlock();
		}
		return full;
	}
	
	
	@Override
	public String toString() {
		try {
			int index = tos;
			StringBuilder msg = new StringBuilder();
			while(index>=0){
				 msg.append("[index:"+index +"value:"+elementArray.get(index)+"]");
				 index--;
			}
			
			return msg.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public int size() {
		return tos+1;
	}

	@Override
	public void clear() {
	   removeAllElements();
	}

	@Override
	public T peek() {
		return null;
	}

	private void removeAllElements(){
	        while(!isEmpty()){
	        	remove();
	        }
	}
	
}
