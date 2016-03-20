package com.ds.template.impls;

import com.core.Queue;
import com.ds.template.TreeNode;

public class TreePart3<T> {

	private TreeNode<T> head;
	private static TreeNode temp;
	private boolean firstAddittion = true;

	public void addNode(T nodeNumber, boolean binary) {
		if (head == null) {
			head = new TreeNodeImpl<T>(nodeNumber, null, null);
		} else {
			if (firstAddittion) {
				temp = head;
				firstAddittion = false;
			}

			if (binary) {
				if ((Long) temp.getData() > (Long) nodeNumber) {
					if (temp.getLeft() == null) {
						TreeNode<? extends T> n = new TreeNodeImpl<T>(nodeNumber, null, null);
						temp.setLeft(n);
						temp = head;
					} else {
						temp = temp.getLeft();
						addNode(nodeNumber, true);
					}
				} else {
					if (temp.getRight() == null) {
						TreeNode<? extends T> n = new TreeNodeImpl<T>(nodeNumber, null, null);
						temp.setRight(n);
						temp = head;
					} else {
						temp = temp.getRight();
						addNode(nodeNumber, true);
					}
				}
			} else {

			}
		}
	}

	private void display() {
		inorder(head);
	}

	private void inorder(TreeNode temp) {
		if (temp != null) {
			inorder(temp.getLeft());
			System.out.println(temp.getData());
			inorder(temp.getRight());
		}
	}

	public boolean isFirstAddittion() {
		return firstAddittion;
	}

	public void setFirstAddittion(boolean firstAddittion) {
		this.firstAddittion = firstAddittion;
	}

	public static void main(String[] args) {
		TreePart3<Long> tree = new TreePart3<Long>();
		tree.addNode(4l, true);
		tree.addNode(2l, true);
		tree.addNode(1l, true);
		tree.addNode(3l, true);
		tree.addNode(5l, true);
		tree.display();
		tree.displayLevelView();
		tree.displayLeftView();
	}

	private void displayLevelView() {
		TreeNode<T> temp = head;
		Queue<TreeNode<T>> queue = new QueueImpl<TreeNode<T>>();
		queue.enqueue(temp);

		while (queue.size() != 0) {
			TreeNode<T>[] nodeArray = new TreeNodeImpl[queue.size()];
			int i = 0;
			while (queue.size() != 0) {
				try {
					nodeArray[i++] = queue.dequeue();
				} catch (InterruptedException e) {
				}
			}

			for (TreeNode<T> n : nodeArray) {
				System.out.print(n.getData());
				queue.enqueue(n.getLeft());
				queue.enqueue(n.getRight());
			}
			System.out.println("\n");
		}
	}

	private void displayLeftView() {
		TreeNode<T> temp = head;
		Queue<TreeNode<T>> queue = new QueueImpl<TreeNode<T>>(10, "");
		queue.enqueue(temp);

		while (queue.size() != 0) {
			TreeNode<T>[] nodeArray = new TreeNodeImpl[queue.size()];

			int i = 0;
			while (queue.size() != 0) {
				try {
					nodeArray[i++] = queue.dequeue();
				} catch (InterruptedException e) {
				}
			}

			if (nodeArray.length != 0) {
				System.out.println(nodeArray[0].getData());
			}

			for (int j = 0; j < nodeArray.length; j++) {
				if (nodeArray[j].getLeft() != null)
					queue.enqueue(nodeArray[j].getLeft());
				if (nodeArray[j].getRight() != null)
					queue.enqueue(nodeArray[j].getRight());
			}

		}
	}
}