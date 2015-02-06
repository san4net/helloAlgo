package com.ds.impls;

import com.core.*;

/**
 * |==============================================================================|
 * |Given a array where element at index i is parent of							  |
 * |2*i+1 && 2*i+2. [This is heap property] | |If index contain -1 that means no  |
 * |child. Build a BST from given array and show inorder traversal 				  |
 * |==============================================================================|
 * 
 * @author santosh kumar
 * 
 * @param <T>
 */
public class HeapToBST<T> {
	private Node<T> head;

	public static HeapToBST<Integer> buildBSTGivenHeap(Integer[] arrayNodes) {
		// Initialize a tree
		HeapToBST<Integer> tree = new HeapToBST<>();
		// Take stack to keep track of parents node index
		Stack<Integer> stack = new StackImpl<Integer>(10);

		for (int i = 0; i < arrayNodes.length; i++) {
			stack.clear();
			if (i == 0) {
				tree.add(arrayNodes[i], i, null);

			} else {
				Integer j = i;
				while (j > 0) {
					j = (j - 1) / 2;
					stack.push(j);
				}

				tree.add(arrayNodes[i], i, stack);
			}
		}

		return tree;
	}

	public static void main(String[] args) {
		Integer[] arrayNodes = { 4, 5, 8, 99, 10, 11, 15 };
		HeapToBST.buildBSTGivenHeap(arrayNodes);

		}

	public void inorder() {
		Node<T> temp = head;
		inorder(temp);
	}

	private void inorder(Node<T> node) {
		if (node != null) {
			inorder(node.getLeft());
			System.out.println(node.getData());
			inorder(node.getRight());
		}
	}

	/**
	 * 
	 * 
	 * @param data
	 * @param index
	 * @param parents
	 */
	public void add(T data, T index, Stack<T> parents) {

		// if -1 then no need to do anything
		if (((Integer) data).intValue() == -1) {
			return;
		}
		if ((Integer) index == 0) {
			head = new Node<>(data, null, null, index);
			return;
		}

		Node<T> temp = head;

		while (!parents.isEmpty()) {
			Integer parentIndex = (Integer) parents.pop();

			if (temp.getStartingIndex() != null
					&& temp.getStartingIndex() == parentIndex) {
				temp = temp.getLeft();
			} else if (temp.getEndingIndex() != null
					&& temp.getEndingIndex() == parentIndex) {
				temp = temp.getRight();
			}
		}

		if (temp.getLeft() == null) {
			temp.setLeft(new Node<>(data, null, null, index));
			temp.setStartingIndex(index);
		} else {
			temp.setRight(new Node<>(data, null, null, index));
			temp.setEndingIndex(index);
		}

	}
	
	/**
	 * Node of the tree
	 * 
	 * @author santosh kumar
	 *
	 * @param <T>
	 */
	class Node<T> {
		T data;
		Node<T> left;
		Node<T> right;
		T index;
		T startingIndex;
		T endingIndex;

		public Node(T data, Node<T> left, Node<T> right, T index) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
			this.index = index;
		}

		public T getData() {
			return data;
		}

		public Node<T> getLeft() {
			return left;
		}

		public void setLeft(Node<T> left) {
			this.left = left;
		}

		public Node<T> getRight() {
			return right;
		}

		public void setRight(Node<T> right) {
			this.right = right;
		}

		public T getIndex() {
			return index;
		}

		public void setIndex(T index) {
			this.index = index;
		}

		public T getStartingIndex() {
			return startingIndex;
		}

		public void setStartingIndex(T startingIndex) {
			this.startingIndex = startingIndex;
		}

		public T getEndingIndex() {
			return endingIndex;
		}

		public void setEndingIndex(T endingIndex) {
			this.endingIndex = endingIndex;
		}

	}
}
