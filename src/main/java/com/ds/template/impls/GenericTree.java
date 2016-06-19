package com.ds.template.impls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.ds.impls.StackImpl;
import com.ds.template.Stack;
import com.ds.template.node.Node;
import com.ds.template.node.TreeNode;

/**
 * Generic Binary Tree
 * 
 * @author santosh kumar
 *
 * @param <T>
 */
public class GenericTree<T> implements Serializable {
	private static final long serialVersionUID = 8568082440037774768L;
	private TreeNode<T> head = null;

	public TreeNode<T> getHead() {
		return head;
	}

	public void add(T data) {
		add(new TreeNodeImpl<T>(data, null, null), head);
	}

	/**
	 * This is BST
	 * 
	 * @param data
	 * @param head
	 */
	private void add(TreeNode<T> newNode, TreeNode<T> head) {
		if (head == null) {
			this.head = newNode;
		} else {
			if (head.compareTo(newNode) > 0) {
				if (head.left() == null) {
					head.setLeft(newNode);
				} else {
					add(newNode, head.left());
				}
			} else {
				if (head.right() == null) {
					head.setRight(newNode);
				} else {
					add(newNode, head.right());
				}
			}

		}
	}

	@Question(description = "inorder traversal")
	public static void inorderWalk(TreeNode<?> n) {
		if (n != null) {
			inorderWalk((TreeNode<?>) n.left());
			System.out.print(n.data() + " ");
			inorderWalk((TreeNode<?>) n.right());
		}
	}

	
	/**
	 * Pseudo code(L-N-R) 
	 * 1. Take stack  
	 * 2. while root is not NULL push root to stack & travese root->left 
	 * 3. while stack in not empty pop  node print it, if node->right is present push to stack and repeat Step2 to Step3 
	 * 
	 */
	
	public static <T> List<T> inorderIterative(TreeNode<T> root) {
		System.out.println("inorder iterative");
		List<T> dataInOrder = new ArrayList<>();
		Stack<TreeNode<T>> stack = new StackImpl();
		
		while (root != null) {
			stack.push(root);
			root = root.left();
		}
		
		while (!stack.isEmpty()) {
			TreeNode<T> node = stack.pop();
			dataInOrder.add(node.data());
			if (node.right() != null) {
				node = node.right();
				while (node != null) {
					stack.push(node);
					node = node.left();
				}
			}
		}
		return dataInOrder;
	}
	
	
	
	/**
	 * psuedo code
	 * 1. initialize stack with root
	 * 2. while stack is not empty pop node print it
	 * 3. push node right and then left
	 * 
	 * 
	 */
	public static <T> void preorderIterative(TreeNode<T> root) {
		System.out.println("preorder iterative");
		if(root == null) return;
		Stack<TreeNode<T>> stack = new StackImpl<TreeNode<T>>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode<T> node = stack.pop();
			System.out.print(node.data());
			if(node.right()!=null){
				stack.push(node.right());
			}
			
			if(node.left() != null){
				stack.push(node.left());
			}
		}
	}

	/**
	 * psuedo code (two stack LR N)
	 * 1. initialize first with root
	 * 2. while first stack is not empty pop and push it to second And 
	 *    push left & right to first
	 * 3. print sencond     
	 *  
	 * 
	 * 2. while stack is not empty pop node print it
	 * 3. push node right and then left
	 * 
	 * 
	 */
	public static <T> List<T> postorderIterative(TreeNode<T> root) {
		System.out.println("postorder iterative");
		List<T> dataPost = new ArrayList<>();
		if(root == null) return dataPost;
		Stack<TreeNode<T>> first = new StackImpl<TreeNode<T>>();
		Stack<TreeNode<T>> second = new StackImpl<TreeNode<T>>();
		first.push(root);

		while (!first.isEmpty()) {
			TreeNode<T> node = first.pop();
			second.push(node);
			
			if(node.left()!=null){
				first.push(node.left());
			}
			
			if(node.right() != null){
				first.push(node.right());
			}
		}
		// print second
		while(!second.isEmpty()){
			dataPost.add(second.pop().data());
		}
		return dataPost;
	}
	
	public static <T> GenericTree<T> buildTree(List<T> data) {
		GenericTree<T> tree = new GenericTree<>();
		for (T t : data) {
			tree.add(t);
		}
		return tree;
	}

	public static void main(String[] args) {
		GenericTree<Integer> tr = new GenericTree<>();
		tr.add(4);
		tr.add(2);
		tr.add(3);
		tr.add(1);
		tr.add(6);
		tr.add(5);
		tr.add(8);
		inorderWalk(tr.head);
		inorderIterative(tr.head);
		preorderIterative(tr.head);
		postorderIterative(tr.head);
		// int depth = tr.findDepth(tr.head);
		// System.out.println("depth" + depth);
		// System.out.println("Max Element" + tr.getMaxElement(tr.head));
		/*System.out.println(Traversal.IN_ORDER);
		tr.traversal(tr.head, Traversal.IN_ORDER);
		tr.mirror(tr.head);
		System.out.println(Traversal.IN_ORDER);
		tr.traversal(tr.head, Traversal.IN_ORDER);*/

		/*
		 * System.out.println(Traversal.PRE_ORDER);
		 * tr.preorderIterative(tr.head);
		 * System.out.println(Traversal.PRE_ORDER); tr.traversal(tr.head,
		 * Traversal.PRE_ORDER);
		 */

//		tr.mirror(tr.head);
		/*
		 * System.out.println(Traversal.IN_ORDER); tr.inorderWalk(tr.head);
		 * System.out.println("\n size" + tr.getSize(tr.head));
		 * tr.getFullAndHalfNode(tr.head); System.out.println("full"+ fullNode +
		 * "half-"+ halfNode); tr.getFullAndHalfNode_WR(tr.head);
		 */
		// System.out.println("all paths");
		// tr.printAllPath(tr.head, new Long[12], 0);

		/**
		 * System.out.println(STACK_TRAVERSAL.IN_ORDER);
		 * tr.inorderWalk(tr.head); tr.makeDLL(tr.head); System.out.println(
		 * "\nUsing stack in order"); tr.traversal(tr.head,
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

	private void makeDLL(TreeNode<T> node) {
		setPrevNode(node);
		Node head = setNextNode(node);
		System.out.println(head);
	}

	public void mirror(TreeNode<T> node) {
		if (node != null) {
			mirror((TreeNode<T>) node.left());
			mirror((TreeNode<T>) node.right());
			TreeNode<T> temp = node.left();
			node.setLeft(node.right());
			node.setRight(temp);
		}
	}

	static TreeNode prev;

	private void setPrevNode(TreeNode<T> node) {
		if (node != null) {
			setPrevNode((TreeNode<T>) node.left());
			node.setLeft(prev);
			prev = node;
			setPrevNode((TreeNode<T>) node.right());
		}
	}

	private Node setNextNode(TreeNode<T> node) {
		// Traverse till right child
		TreeNode<T> prev = null;
		while (node.right() != null) {
			node = (TreeNode<T>) node.right();
		}

		while (node != null) {
			/*
			 * prev = node; node = node.getLeft(); node.setRight(prev);
			 */
			node.setRight(prev);
			prev = node;
			if (node.left() == null) {
				return node;
			} else {
				node = (TreeNode<T>) node.left();
			}
		}

		return node;
	}

	public int getSize(TreeNode<T> node) {
		return node == null ? 0 : getSize((TreeNode<T>) node.left()) + getSize((TreeNode<T>) node.right()) + 1;
	}

	static int fullNode, halfNode;

	public void getFullAndHalfNode(TreeNode<T> node) {
		if (node == null) {
			return;
		} else {
			if (node.left() != null && node.right() != null) {
				fullNode++;
			} else if ((node.left() == null && node.right() != null) || (node.left() != null && node.right() == null)) {
				halfNode++;
			}
			getFullAndHalfNode((TreeNode<T>) node.left());
			getFullAndHalfNode((TreeNode<T>) node.right());
		}
	}

	public void getFullAndHalfNode_WR(TreeNode<T> node) {
		Stack<TreeNode<T>> s = new StackImpl<TreeNode<T>>();
		int fNode = 0, hNode = 0;
		if (node == null)
			return;

		while (true) {
			while (node != null) {
				s.push((TreeNode<T>) node);
				node = (TreeNode<T>) node.left();
			}
			if (s.isEmpty()) {
				break;
			}
			TreeNode<T> topNode = s.pop();

			if (topNode.left() != null && topNode.right() != null) {
				fNode++;
			} else if ((topNode.left() == null && topNode.right() != null)
					|| (topNode.left() != null && topNode.right() == null)) {
				hNode++;
			}
			node = (TreeNode<T>) topNode.right();
		}
		System.err.println("fullnode " + fNode + "half node" + hNode);
	}

	public void printAllPath(TreeNode<T> node, T[] pathNodes, int index) {
		if (node == null)
			return;
		else {
			pathNodes[index++] = (T) node.data();
			if (node.left() == null && node.right() == null) {
				printPath(pathNodes);
			}
			printAllPath((TreeNode<T>) node.left(), pathNodes, index);
			printAllPath((TreeNode<T>) node.right(), pathNodes, index);
		}
	}

	private void printPath(T[] pathNodes) {
		System.out.println(pathNodes);
		for (T n : pathNodes) {
			System.out.print(n + "->");
		}
		System.out.println("");
	}

	private void printReverseLevel(TreeNode<T> root) {
		Queue<TreeNode<T>> q = new ConcurrentLinkedQueue<TreeNode<T>>();
		q.add(root);
		TreeNode<T> r;

		while ((r = q.poll()) != null) {
			System.out.println(r.data());
			if (r.left() != null) {
				q.add((TreeNode<T>) r.left());
			}
			if (r.right() != null) {
				q.add((TreeNode<T>) r.right());
			}
		}

	}

	public void printLevelOrder(TreeNode<T> root) {
		int height = findDepth(root);
		for (int i = 1; i <= height; i++) {
			printGivenLevel(root, i, null);
			System.out.println("");
		}

	}

	public void printZigZag(TreeNode<T> root) {
		int height = findDepth(root);

		for (int i = 1; i <= height; i++) {
			printGivenLevel(root, i, !(i % 2 == 0));
			System.out.println("");
		}

	}

	private void printGivenLevel(TreeNode<T> node, int level, Boolean leftFirst) {
		if (node == null)
			return;

		if (level == 1) {
			System.out.print(node.data());
		} else {
			if (leftFirst == null || leftFirst) {
				printGivenLevel((TreeNode<T>) node.left(), level - 1, null);
				printGivenLevel((TreeNode<T>) node.right(), level - 1, null);
			}

			else if (!leftFirst) {
				printGivenLevel((TreeNode<T>) node.right(), level - 1, null);
				printGivenLevel((TreeNode<T>) node.left(), level - 1, null);

			}

		}
	}

	public long getMaxElement(TreeNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			long leftMax = getMaxElement((TreeNode<T>) node.left());
			long rightMax = getMaxElement((TreeNode<T>) node.right());
			return Math.max((Long) node.data(), Math.max(leftMax, rightMax));
		}
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	public int findDepth(TreeNode<T> node) {
		if (node == null)
			return 0;
		else {
			int leftD = findDepth((TreeNode<T>) node.left());
			int rightD = findDepth((TreeNode<T>) node.right());

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
