package com.ds.template.impls;

import com.ds.template.node.Node;
import com.ds.template.node.TreeNode;

/**
 * Given a Binary Tree (BT), convert it to a Doubly Linked List(DLL). The left and right pointers in nodes are to be used as previous and next pointers respectively in converted DLL. The order of nodes in DLL must be same as Inorder of the given Binary Tree. The first node of Inorder traversal (left most node in BT) must be head node of the DLL.

TreeToList

A solution to this problem is discussed in this post.
In this post, another simple and efficient solution is discussed. The solution discussed here has two simple steps.

1) Fix Left Pointers: In this step, we change left pointers to point to previous nodes in DLL. The idea is simple, we do inorder traversal of tree. In inorder traversal, we keep track of previous visited node and change left pointer to the previous node. See fixPrevPtr() in below implementation.

2) Fix Right Pointers: The above is intuitive and simple. How to change right pointers to point to next node in DLL? The idea is to use left pointers fixed in step 1. We start from the rightmost node in Binary Tree (BT). The rightmost node is the last node in DLL. Since left pointers are changed to point to previous node in DLL, we can linearly traverse the complete DLL using these pointers. The traversal would be from last to first node. While traversing the DLL, we keep track of the previously visited node and change the right pointer to the previous node. See fixNextPtr() in below implementatio
 */
public class ConverBTToDLL {
	public static TreeNode prev;
	
	private void makeDLL(TreeNode node) {
		setPrevNode(node);
		Node head = setNextNode(node);
		System.out.println(head);
	}

	private void setPrevNode(TreeNode node) {
		if (node != null) {
			setPrevNode((TreeNode) node.left());
			node.setLeft(prev);
			prev = node;
			setPrevNode((TreeNode) node.right());
		}
	}

	private Node setNextNode(TreeNode node) {
		// Traverse till right child
	    TreeNode prev = null;
		while (node.right() != null) {
			node = (TreeNode) node.right();
		}

		while (node != null) {
		/*	prev = node;
			node = node.getLeft();
			node.setRight(prev);*/
			node.setRight(prev);
			prev = node;
			if(node.left()== null){
				return node;
			}else{
				node = (TreeNode) node.left();
			}
		}

		return node;
	}
	public static void main(String[] args) {
		GenericBST<Long> tr = new GenericBST<Long>();
		tr.add(4l);
		/*tr.add(2l, tr.getHead());
		tr.add(3l, tr.getHead());
		tr.add(1l, tr.getHead());
		tr.add(5l, tr.getHead());*/
		
	}
}
