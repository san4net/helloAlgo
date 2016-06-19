package com.ds.template.impls;

import com.ds.template.Queue;
import com.ds.template.node.TreeNode;

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
				if ((Long) temp.data() > (Long) nodeNumber) {
					if (temp.left() == null) {
						TreeNode<? extends T> n = new TreeNodeImpl<T>(nodeNumber, null, null);
						temp.setLeft(n);
						temp = head;
					} else {
						temp = temp.left();
						addNode(nodeNumber, true);
					}
				} else {
					if (temp.right() == null) {
						TreeNode<? extends T> n = new TreeNodeImpl<T>(nodeNumber, null, null);
						temp.setRight(n);
						temp = head;
					} else {
						temp = temp.right();
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
			inorder(temp.left());
			System.out.println(temp.data());
			inorder(temp.right());
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
				System.out.print(n.data());
				queue.enqueue(n.left());
				queue.enqueue(n.right());
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
				System.out.println(nodeArray[0].data());
			}

			for (int j = 0; j < nodeArray.length; j++) {
				if (nodeArray[j].left() != null)
					queue.enqueue(nodeArray[j].left());
				if (nodeArray[j].right() != null)
					queue.enqueue(nodeArray[j].right());
			}

		}
	}
}
