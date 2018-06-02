package com.concurrent.nonblocking;

import java.util.concurrent.atomic.AtomicReference;
/**
 * Non blocking stack using Treiber's Algorithm(Treiber, 1986)
 * 
 * @author santosh
 *
 * @param <E>
 */
public class ConcurrentNonBlockingStack<E> {
	// we can use this for non blocking
	AtomicReference<Node<E>> top = new AtomicReference<>();

	private class Node<E> {
		private final E data;
		public Node<E> next;

		Node(E item) {
			this.data = item;
		}
	}

	public void push(E item) {
		Node<E> newHead = new Node(item);
		Node<E> oldHead = null;
		do {
			oldHead = top.get();
			newHead.next = oldHead;
		} while (!top.compareAndSet(oldHead, newHead));
	}

	public E pop() {
		Node<E> oldHead;
		Node<E> newHead;
		do {
			oldHead = top.get();
			if (oldHead == null)
				return null;
			newHead = oldHead.next;
		} while (!top.compareAndSet(oldHead, newHead));

		return oldHead.data;
	}
}
