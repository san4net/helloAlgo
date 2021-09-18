package org.ds.qs;

import org.ds.template.impls.node.SingleNodeImpl;
import org.ds.template.node.SingleNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class LinkedList<T> implements Iterable<T> {
	private AtomicInteger size = new AtomicInteger(0);
	private SingleNode<T> head = null;

	public void display() {
		SingleNode<? extends T> tmp = head;
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
			head = new SingleNodeImpl<T>(data, null);
			increment();
			return;
		}

		if (atBegining) {
			head = new SingleNodeImpl(data, head);
		} else {
			SingleNode<? extends T> t = head;
			while (t.getNext() != null) {
				t = (SingleNode<T>) t.getNext();
			}
			t.setNext(new SingleNodeImpl(data, null));
		}
		increment();
	}

	
	// Iterative reverse
	public void reverseIterative(LinkedList<T> ll) {
		ll.display(); // 1->2->3(null)
		SingleNode<T> t1 = null, t2 = null;

		while (ll.head != null) {
			t1 = ll.head;
			ll.head = (SingleNode<T>) ll.head.getNext();
			t1.setNext(t2);
			t2 = t1;
		}
		ll.head = t1;
		ll.display();
	}
	
	public void reverseIT(){
		SingleNode<T> first=null, second=null;
		while(head != null){
			first = head;
			head = head.getNext();
			first.setNext(second);
			second = first;
		}
		head = first;
	}



	/**
	 *
	 * @param head
	 * @return
	 */
	public SingleNode<T> reverseRecursiveCorrect(SingleNode<T> head) {
		if (head == null) {
			return null;
		}
		if (head.getNext() == null) {
			return head;
		}

		SingleNode<T> second = head.getNext();
		// break the link
		head.setNext(null);
		SingleNode<T> reverse = reverseRecursiveCorrect(second);
		// here we need to do the lining
		second.setNext(head);
		return reverse;
	}

	/**
	 * With head node flowing from last to first;
	 *
	 * @param head
	 * @param prev
	 * @return
	 */
	public static <T> SingleNode<T> reverseRecursive(SingleNode<T> head, SingleNode<T> prev) {
		if (head == null) {
			return prev;
		}
		SingleNode<T> upcoming = head.getNext();
		// reverse the link
		head.setNext(prev);
		return reverseRecursive(upcoming, head);
	}

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
			 * // reverse the first pair
			 *  SingleNode<? extends Number> secondNode = (SingleNode<? extends Number>) head.getNext();
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
		ArrayList<Integer> i = new ArrayList<>();
		ArrayList<? extends Number> iext = i;
		
		LinkedList<Integer> first = new LinkedList();
		first.add(1);
		first.add(3);
		first.add(3);
		first.display();
		first.reverseIT();
		first.display();
		first.head = reverseRecursive(first.head, null);
		first.display();
//
//		LinkedList<Integer> second = new LinkedList();
//		second.add(2);
//		second.add(4);
//
//		first.display();
//		second.display();
//
//		second.head = second.reverseRecursiveCorrect(second.head);
//		second.display();
//		 display(mergeSorted(first.getHead(), second.getHead()));
		// l.reverseInPair((SingleNode<Integer>) l.getHead(), null);
		/*
		 * l.reverse(2); l.reverseRecursive((SingleNode<Integer>) l.getHead());
		 * l.display();
		 */

	}


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
