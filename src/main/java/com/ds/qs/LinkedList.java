package com.ds.qs;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import com.ds.template.impls.node.SingleNodeImpl;
import com.ds.template.node.SingleNode;

public class LinkedList<T> implements Iterable<T> {
	private AtomicInteger size = new AtomicInteger(0);
	private SingleNode<T> head = null;

	public void display() {
		SingleNode<T> tmp = head;
		while (tmp != null) {
			System.out.print("->" + tmp.data());
			tmp = (SingleNode<T>) tmp.getNext();
		}
		System.out.println();
	}

	/*
	 * public static void display(SingleNode<? extends Number> node) { while
	 * (node != null) { System.out.print("->" + node.getData()); node =
	 * node.getNext(); } }
	 */
	
	public void add(T data) {
		add(data, false);
	}
	
	private void increment(){
		size.addAndGet(1);
	}
	
	/**
	 * @param data
	 * @param atBegining
	 */
	public void add(T data, boolean atBegining) {
		if (head == null) {
			head = new SingleNodeImpl(data, null);
			increment();
			return;
		}

		if (atBegining) {
			head = new SingleNodeImpl(data, head);
		} else {
			SingleNode<T> t = head;
			while (t.getNext() != null) {
				t = (SingleNode<T>) t.getNext();
			}
			t.setNext(new SingleNodeImpl(data, null));
		}
		increment();
	}

	/*
	 * private void reverse(LinkedList<T> ll) { ll.display(); // 1->2->3(null)
	 * SingleNode<? extends Number> t1 = null, t2 = null;
	 * 
	 * while (ll.head != null) { t1 = ll.head;
	 * 
	 * ll.head = (SingleNode<T>) ll.head.getNext();
	 * 
	 * ll.head = (SingleNode<? extends Number>) ll.head.getNext();
	 * t1.setNext(t2); t2 = t1; } ll.head = t1; ll.display(); }
	 */

	/*
	 * public void reverseRecursive(SingleNode<? extends Number> node) { if
	 * (node.getNext() == null) { head = node; } else {
	 * reverseRecursive((SingleNode<? extends Number>) node.getNext());
	 * ((SingleNode<T>) node.getNext()).setNext(node); node.setNext(null); } }
	 */

	/*	*//**
			 * 
			 * @param head
			 * @param prevHead
			 *//*
			 * private void reverseInPair(SingleNode<? extends Number> head,
			 * SingleNode<? extends Number> prevHead) {
			 * 
			 * if (head.getNext() == null || ((SingleNode)
			 * head.getNext()).getNext() == null) { return; // base case }
			 * 
			 * // reverse the first pair SingleNode<? extends Number> secondNode
			 * = (SingleNode<? extends Number>) head.getNext();
			 * head.setNext(secondNode.getNext()); secondNode.setNext(head);
			 * 
			 * // this is must to restore head and linking reversed node to prev
			 * head // hode if (prevHead == null) { this.head = secondNode; }
			 * else { prevHead.setNext(secondNode); }
			 * 
			 * reverseInPair((SingleNode<? extends Number>) head.getNext(),
			 * (SingleNode<? extends Number>) head); }
			 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// prepareLinkedList();
		LinkedList<Integer> first = new LinkedList();
		first.add(1);
		first.add(3);
		LinkedList<Integer> second = new LinkedList();
		second.add(2);
		second.add(4);

		first.display();
		second.display();
		
		for(Integer i : first){
			System.out.println(i);
		}
		// display(mergeSorted(first.getHead(), second.getHead()));
		// l.reverseInPair((SingleNode<Integer>) l.getHead(), null);
		/*
		 * l.reverse(2); l.reverseRecursive((SingleNode<Integer>) l.getHead());
		 * l.display();
		 */

	}

	/*
	 * public void reverse(int n) { if (n > size.get()) { return; } // get to
	 * the n+1 element in the node int i = 0; SingleNode<? extends Number>
	 * nplusPointer = head;
	 * 
	 * while (i < n) { nplusPointer = (SingleNode<? extends Number>)
	 * nplusPointer.getNext(); i++; }
	 * 
	 * // reverse the first n node SingleNode<? extends Number> prev = null,
	 * curNode = null;
	 * 
	 * i = 0; while (i < n) { curNode = head; head = (SingleNode<? extends
	 * Number>) head.getNext(); curNode.setNext(prev); prev = curNode; i++; } //
	 * no element are reversed head = prev;
	 * 
	 * while (prev.getNext() != null) { prev = (SingleNode<? extends Number>)
	 * prev.getNext(); } prev.setNext(nplusPointer); }
	 */
	/*
	 * public void myReverse() { SingleNode<? extends Number> t1 = null, t2 =
	 * null;
	 * 
	 * while (head != null) { t1 = head; head = (SingleNode<? extends Number>)
	 * head.getNext(); t1.setNext(t2); t2 = t1; } head = t1; }
	 */
	public T getTop() {
		return head.data();
	}

	public SingleNode<T> getHead() {
		return head;
	}

	/*
	 * private void removeDups() { SingleNode<? extends Number> temp1 = head;
	 * HashSet<Integer> bucket = new HashSet<Integer>(); Node prev = null; while
	 * (temp1 != null) {
	 * 
	 * if (!bucket.contains(temp1.getData())) { bucket.add((Integer)
	 * temp1.getData()); prev = temp1; } else { if (prev != null) {
	 * ((SingleNode<? extends Number>) prev).setNext(temp1.getNext()); } } temp1
	 * = (SingleNode<? extends Number>) temp1.getNext(); }
	 * 
	 * // prev.setNext(null);
	 * 
	 * }
	 */

	public void printRev(SingleNode<T> head) {

		if (head.getNext() == null) {
			System.out.println((T)head.data());
		} else {
			printRev((SingleNode<T>) head.getNext());
			System.out.println((T)head.data());
		}
	}

	/**
	 * Given two sorted linked list we need to sort
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static SingleNode mergeSorted(SingleNode first, SingleNode second) {
		if (first == null)
			return second;
		if (second == null)
			return first;

		SingleNode<? extends Number> result = null;

		if (((Integer) first.data()) < (Integer) second.data()) {
			result = first;
			result.setNext(mergeSorted(first.getNext(), second));
			return result;
		}
		if ((Integer) first.data() > (Integer) second.data()) {
			result = second;
			result.setNext(mergeSorted(first, second.getNext()));
			return result;
		}

		return result;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			SingleNode<T> temp = head;

			@Override
			public boolean hasNext() {
				return (temp != null);
			}

			@Override
			public T next() {
				T data = temp.data();
				temp = (SingleNode<T>) temp.getNext();
				return data;
			}
		};
	}
}
