package com.me.ds.template;

import java.io.Serializable;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GenericTree<T> implements Serializable{
	private static final long serialVersionUID = 8568082440037774768L;
	
	Node<T> head = null;

	/**
	 * This is BST
	 * 
	 * @param data
	 * @param temp
	 */
	public void add(T data, Node<T> temp) {
		if (head == null) {
			head = new TreeNodeImpl<T>(data, null, null);
		} else {
			if ((Long) temp.getData() > (Long) data) {
				if (temp.getLeft() == null) {
					Node<T> l = new TreeNodeImpl<T>(data, null, null);
					temp.setLeft(l);
				} else {
					add(data, temp.getLeft());
				}
			} else {
				if (temp.getRight() == null) {
					Node<T> l = new TreeNodeImpl<T>(data, null, null);
					temp.setRight(l);
				} else {
					add(data, temp.getRight());
				}
			}

		}
	}

	public static void inorderWalk(Node<?> n) {
		if (n != null) {
			inorderWalk(n.getLeft());
			System.out.print(n.getData() + " ");
			inorderWalk(n.getRight());
		}
	}

	public void traversal(Node<T> node, Traversal order) {
		Stack<Node<T>> stack = new Stack<Node<T>>();
		Node<T> current = node;
		push(stack, current, order);

		while (!stack.isEmpty()) {
			current = stack.pop();
			System.out.print(current.getData());
			if (current.getRight() != null) {
				push(stack, current.getRight(), order);
			}
		}

	}

	public void preorderIterative(Node<T> node) {
		Stack<Node<T>> stack = new Stack<Node<T>>();
		Node<T> current = node;
		stack.push(node);

		while (!stack.isEmpty()) {
			Node<T> element = stack.pop();
			System.out.print(element.getData());
			push(stack, element, Traversal.PRE_ORDER);
		}
	}

	public void push(Stack<Node<T>> stack, Node<T> node,
			Traversal traversalOrder) {
		
		if (Traversal.IN_ORDER.name().equals(traversalOrder.name())) {
			while (node != null) {
				stack.push(node);
				node = node.getLeft();
			}
		} else if (Traversal.PRE_ORDER.name().equals(
				traversalOrder.name())) {
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
		}
	}

	public static void main(String[] args) {
		GenericTree<Long> tr = new GenericTree<Long>();
		tr.add(4l, tr.head);
		tr.add(2l, tr.head);
		tr.add(3l, tr.head);
		tr.add(1l, tr.head);
		tr.add(6l, tr.head);
		tr.add(5l, tr.head);
		tr.add(8l, tr.head);
		
//		System.out.println("Max Element" + tr.getMaxElement(tr.head));
		System.out.println(Traversal.IN_ORDER);
		tr.inorderWalk(tr.head);
//		tr.mirror(tr.head);
		System.out.println(Traversal.IN_ORDER);
		tr.inorderWalk(tr.head);
		System.out.println("\n size" + tr.getSize(tr.head));
		tr.getFullAndHalfNode(tr.head);
		System.out.println("full"+ fullNode + "half-"+ halfNode);
		tr.getFullAndHalfNode_WR(tr.head);
		
		tr.printAllPath(tr.head, new Long[12], 0);
		/**
		 * System.out.println(STACK_TRAVERSAL.IN_ORDER);
		 * tr.inorderWalk(tr.head); tr.makeDLL(tr.head);
		 * System.out.println("\nUsing stack in order"); tr.traversal(tr.head,
		 * STACK_TRAVERSAL.IN_ORDER); System.out.println("depth"+
		 * tr.findDepth(tr.head)); System.out.println("LevelOrder traversal");
		 * tr.printLevelOrder(tr.head); System.out.println("printing zigzag");
		 * tr.printZigZag(tr.head); System.out.println("printing preorder");
		 * tr.preorderIterative(tr.head); System.out.println("printing level");
		 * tr.printReverseLevel(tr.head);
		 */
	}

	/**
	 * Here's the formal problem statement: Write a recursive function
	 * treeToList(Node root) that takes an ordered binary tree and rearranges
	 * the internal pointers to make a circular doubly linked list out of the
	 * tree nodes. The "previous" pointers should be stored in the "small" field
	 * and the "next" pointers should be stored in the "large" field. The list
	 * should be arranged so that the nodes are in increasing order. Return the
	 * head pointer to the new list. The operation can be done in O(n) time --
	 * essentially operating on each node once. Basically take figure-1 as input
	 * and rearrange the pointers to make figure-2.
	 */

	private void makeDLL(Node node) {
		setPrevNode(node);
		Node head = setNextNode(node);
		System.out.println(head);
	}

	public void mirror(Node<T> node) {
		if (node != null) {
			mirror(node.getLeft());
			mirror(node.getRight());
			Node left = node.getLeft();
			node.setLeft(node.getRight());
			node.setRight(left);
		}
	}

	static Node prev;

	private void setPrevNode(Node node) {
		if (node != null) {
			setPrevNode(node.getLeft());
			node.setLeft(prev);
			prev = node;
			setPrevNode(node.getRight());
		}
	}

	private Node setNextNode(Node node) {
		// Traverse till right child
		Node prev = null;
		while (node.getRight() != null) {
			node = node.getRight();
		}

		while (node != null) {
			/*
			 * prev = node; node = node.getLeft(); node.setRight(prev);
			 */
			node.setRight(prev);
			prev = node;
			if (node.getLeft() == null) {
				return node;
			} else {
				node = node.getLeft();
			}
		}

		return node;
	}

	public int getSize(Node node) {
		return node == null ? 0 : getSize(node.getLeft())
				+ getSize(node.getRight()) + 1;
	}
	static int fullNode, halfNode;
	
	public void getFullAndHalfNode(Node node) {
		if (node == null) {
			return;
		} else {
			if (node.getLeft() != null && node.getRight() != null) {
				fullNode++;
			} else if ((node.getLeft() == null && node.getRight() != null)
					|| (node.getLeft() != null && node.getRight() == null)) {
				halfNode++;
			}
			getFullAndHalfNode(node.getLeft());
			getFullAndHalfNode(node.getRight());
		}
	}
	
	public void getFullAndHalfNode_WR(Node node){
		Stack<Node<Integer>> s = new Stack<Node<Integer>>();
		int fNode = 0, hNode =0;
		if(node == null) return;
		
		while (true) {
			while (node != null) {
				s.push(node);
				node = node.getLeft();
			}
			if(s.isEmpty()){
			 break;
			}
			Node<Integer> topNode = s.pop();
			
			if (topNode.getLeft() != null && topNode.getRight() != null) {
				fNode++;
			} else if ((topNode.getLeft() == null && topNode.getRight() != null)
					|| (topNode.getLeft() != null && topNode.getRight() == null)) {
				hNode++;
			}
			node = topNode.getRight();
		}
		System.err.println("fullnode "+ fNode+"half node"+ hNode);
	}
	
	public void printAllPath(Node<Long> node,Long[] pathNodes, int index){
		if(node == null) return;
		else{
		  pathNodes[index++] = (Long) node.getData();
		 
		 if(node.getLeft() == null && node.getRight()==null){
			 printPath(pathNodes);
		 }
		 printAllPath(node.getLeft(), pathNodes, index);
		 printAllPath(node.getRight(), pathNodes, index);
		}
	}
	private void printPath(Long[] pathNodes) {
		for(Long n:pathNodes){
			System.out.print(n+"->");
		}
		System.out.println("");
	}

	private void printReverseLevel(Node<T> root) {
		Queue<Node<T>> q = (Queue<Node<T>>) new ConcurrentLinkedQueue<T>();
		q.add(root);

		Node<T> r;

		while ((r = q.poll()) != null) {
			System.out.println(r.getData());
			if (r.getLeft() != null) {
				q.add(r.getLeft());
			}
			if (r.getRight() != null) {
				q.add(r.getRight());
			}
		}

	}

	public void printLevelOrder(Node<T> root) {
		int height = findDepth(root);
		for (int i = 1; i <= height; i++) {
			printGivenLevel(root, i, null);
			System.out.println("");
		}

	}

	public void printZigZag(Node<T> root) {
		int height = findDepth(root);

		for (int i = 1; i <= height; i++) {
			printGivenLevel(root, i, !(i % 2 == 0));
			System.out.println("");
		}

	}

	private void printGivenLevel(Node<T> node, int level, Boolean leftFirst) {
		if (node == null)
			return;

		if (level == 1) {
			System.out.print(node.getData());
		} else {
			if (leftFirst == null || leftFirst) {
				printGivenLevel(node.getLeft(), level - 1, null);
				printGivenLevel(node.getRight(), level - 1, null);
			}

			else if (!leftFirst) {
				printGivenLevel(node.getRight(), level - 1, null);
				printGivenLevel(node.getLeft(), level - 1, null);

			}

		}
	}

	public long getMaxElement(Node<T> node) {
		if (node == null) {
			return 0;
		} else {
			long leftMax = getMaxElement(node.getLeft());
			long rightMax = getMaxElement(node.getRight());
			return Math.max((Long) node.getData(), Math.max(leftMax, rightMax));
		}
	}

	public int findDepth(Node<T> node) {
		if (node == null)
			return 0;
		else {
			int leftD = findDepth(node.getLeft());
			int rightD = findDepth(node.getRight());
			if (leftD > rightD) {
				return leftD + 1;
			} else {
				return rightD + 1;
			}
		}
	}

	private class DLLNode<T> {
		T data;
		DLLNode<T> next;
		DLLNode<T> prev;

		public DLLNode(T data, DLLNode<T> next, DLLNode<T> prev) {
			super();
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

		public DLLNode<T> getNext() {
			return next;
		}

		public void setNext(DLLNode<T> next) {
			this.next = next;
		}

		public DLLNode<T> getPrev() {
			return prev;
		}

		public void setPrev(DLLNode<T> prev) {
			this.prev = prev;
		}
	}
}
