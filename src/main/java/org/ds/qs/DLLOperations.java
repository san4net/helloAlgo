package org.ds.qs;

import java.util.concurrent.atomic.AtomicLong;

public class DLLOperations<T> {

	private Node<T> head;

	private AtomicLong size = new AtomicLong();

	public boolean addNode(T data, boolean atBegining) {

		if (head == null) {
			head = createNode(data);
			size.incrementAndGet();
		} else {
			Node<T> tmp = createNode(data);
			tmp.setNext(head);
			tmp.setPrev(null);
			head.setPrev(tmp);
			head = tmp;
			size.incrementAndGet();
		}
		return true;
	}

	public Node<T> createNode(T data) {
		return new Node<T>(data, null, null);
	}

	public Node<T> delete(int pos) {
		if (pos <= 0 || pos > size.longValue()) {
			throw new ArrayIndexOutOfBoundsException();
		}

		Node<T> nodeToDelete = head;
		int i = 1;
		while (i < pos) {
			nodeToDelete = nodeToDelete.getNext();
			i++;
		}

		if (pos == 1) {
			nodeToDelete = nodeToDelete.getNext();
			nodeToDelete.setPrev(null);
			head.setNext(null);
			head = nodeToDelete;
		} else {
			Node<T> prevNode = nodeToDelete.getPrev();
			Node<T> nextNode = nodeToDelete.getNext();
			prevNode.setNext(nextNode);

			if (nextNode != null)
				nextNode.setPrev(prevNode);
			// now free
			nodeToDelete.setPrev(null);
			nodeToDelete.setNext(null);
		}
		return head;
	}

	public void display() {
		if (head == null) {
			System.out.println("No Element");
		} else {
			Node<T> tmp = head;
			String data = "";
			while (tmp != null) {
				data = data + "->" + "" + tmp.getData();
				tmp = tmp.getNext();
			}

			System.out.println("linked list" + data);
		}
	}

	public static void main(String[] args) throws Exception {

		DLLOperations<Integer> instance = new DLLOperations<Integer>();
		for (int i = 5; i < 10; i++) {
			instance.addNode(i, true);
		}
		instance.display();

		System.out.println("deleting position" + 5);
		instance.head = instance.delete(5);
		instance.display();
		System.out.println("deleting position" + 3);
		instance.head = instance.delete(3);
		instance.display();
	}

	private class Node<T> {
		private T data;
		private Node<T> next;
		private Node<T> prev;

		public Node(T data, Node<T> next, Node<T> prev) {
			super();
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public Node<T> getPrev() {
			return prev;
		}

		public void setPrev(Node<T> prev) {
			this.prev = prev;
		}

	}
}
