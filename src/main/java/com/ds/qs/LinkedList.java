package com.ds.qs;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

import com.ds.template.Node;
import com.ds.template.SingleNode;
import com.ds.template.impls.SingleNodeImpl;

public class LinkedList<T> {
	private AtomicInteger size = new AtomicInteger(0);
	private SingleNode<T> head = null;

	// public static LinkedList instance = new LinkedList();

	/**
	 * private static void prepareLinkedList() { LinkedList ll = new
	 * LinkedList(); ll.add(1); ll.add(1); ll.add(1); //ll.add(2); ll.add(1);
	 * ll.add(3); //ll.add(5); display(ll); //ll.removeDups(); //display(ll);
	 * //reverse(ll); }
	 */

	public void display() {
	    SingleNode<T> tmp = head;
		while (tmp != null) {
			System.out.print("->" + tmp.getData());
			tmp = (SingleNode<T>) tmp.getNext();
		}
		System.out.println();
	}

	public void add(T data) {
		add(data, false);
	}

	/**
	 * @param data
	 * @param atBegining
	 */
	public void add(T data, boolean atBegining) {
		if (head == null) {
			head = new SingleNodeImpl(data, null);
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
		size.addAndGet(1);
	}


	private  void reverse(LinkedList<T> ll) {
		ll.display();
		// 1->2->3(null)
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

	 public  void reverseRecursive(SingleNode<T> node){
		 if(node.getNext() == null){
			 head = node;
		 } else{
			 reverseRecursive((SingleNode<T>) node.getNext());
			 ((SingleNode<T>) node.getNext()).setNext(node);
			 node.setNext(null);
		 }
	 }
	 
	 /**
	  * 
	  * @param head
	  * @param prevHead
	  */
	 private void reverseInPair(SingleNode<T> head, SingleNode<T> prevHead){
		 
		 if( head.getNext() == null || ((SingleNode)head.getNext()).getNext() == null){
			 return;	  // base case
		 }
		 
		 // reverse the first pair
		 SingleNode<T> secondNode = (SingleNode<T>) head.getNext();
		 head.setNext(secondNode.getNext());
		 secondNode.setNext(head);
		 
		 if(prevHead == null){
			 this.head = secondNode;
		 }else {
			 prevHead.setNext(secondNode);
		 }
		 
		 reverseInPair((SingleNode<T>) head.getNext(), (SingleNode<T>)head);
	 }
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// prepareLinkedList();
		LinkedList<Integer> l = new LinkedList();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		l.display();
		l.reverseInPair((SingleNode<Integer>) l.getHead(), null);
		l.display();
		/*l.reverse(2);
		l.reverseRecursive((SingleNode<Integer>) l.getHead());
		l.display();*/

	}

	public void reverse(int n) {
		if (n > size.get()) {
			return;
		}
		// get to the n+1 element in the node
		int i = 0;
		SingleNode<T> nplusPointer = head;

		while (i < n) {
			nplusPointer = (SingleNode<T>) nplusPointer.getNext();
			i++;
		}
		
		// reverse the first n node
		SingleNode<T> prev = null, curNode = null;

		i = 0;
		while (i < n) {
			curNode = head;
			head = (SingleNode<T>) head.getNext();
			curNode.setNext(prev);
			prev = curNode;
			i++;
		}
		// no element are reversed
		head = prev;

		while (prev.getNext() != null) {
			prev = (SingleNode<T>) prev.getNext();
		}
		prev.setNext(nplusPointer);
	}

	public void myReverse() {
	    SingleNode<T> t1 = null, t2 = null;

		while (head != null) {
			t1 = head;
			head = (SingleNode<T>) head.getNext();
			t1.setNext(t2);
			t2 = t1;
		}
		head = t1;
	}

	public T getTop() {
		return (T) head.getData();
	}

	public Node<T> getHead() {
		return head;
	}

	private void removeDups() {
	    SingleNode<T> temp1 = head;
		HashSet<Integer> bucket = new HashSet<Integer>();
		Node prev = null;
		while (temp1 != null) {

			if (!bucket.contains(temp1.getData())) {
				bucket.add((Integer) temp1.getData());
				prev = temp1;
			} else {
				if (prev != null) {
					((SingleNode<T>) prev).setNext(temp1.getNext());
				}
			}
			temp1 = (SingleNode<T>) temp1.getNext();
		}

		// prev.setNext(null);

	}

	public void printRev(SingleNode<T> head) {

		if (head.getNext() == null) {
			System.out.println(head.getData());
		} else {
			printRev((SingleNode<T>) head.getNext());
			System.out.println(head.getData());
		}
	}
}

//
/*
 * while (current != null) { if (!data.contains(current.data)) {
 * data.add(current.data); prev = current; current = current.next; } else { if
 * (prev != null) { prev.next = current.next; current = current.next; } } } }
 */

