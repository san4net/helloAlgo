package org.ds.template.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.ds.template.node.TreeNode;

public class TreeeQuestions<T> {
	/**
	 * given 11 01 11 00 00
	 */

	private TreeNode<T> head;

	// private static Integer index=0;
	private T data;
//
	// private static int data=1;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	private TreeNode<T> constructTree(Integer[] input, TreeNode<T> node, Integer index) {

		if (index < input.length) {

			setData((T) index);

			if (index == 0) {
				head = new TreeNodeImpl<T>(data, null, null);
				node = head;
			} else {
				node = new TreeNodeImpl<T>(data, null, null);
			}

			if (input[index].equals(1)) {
				// index = index + 2;
				node.setLeft(constructTree(input, node, index + 2));
			}

			if (input[index + 1].equals(1)) {
				// index = index + 4;
				if (input[index].equals(1)) {
					node.setRight(constructTree(input, node, index + 4));
				} else {
					node.setRight(constructTree(input, node, index + 2));
				}
			}
			return node;
		}

		return null;
	}
 
	public static void main(String[] args) {
		buildTreeFromTraversal();
		TreeNode<Integer> root = createBinaryTree();
		GenericBST.inorderWalk(root);
		AtomicInteger count = new AtomicInteger(0);
		countSingleValuedTree(root, count );
		System.out.println(count);
	}
	
	private static TreeNode createBinaryTree() {
		TreeNode<Integer> root = new TreeNodeImpl<>(5, null, null);
		root.setLeft(new TreeNodeImpl<Integer>(1, null, null));
		root.setRight(new TreeNodeImpl<>(5, null, null));
		root.left().setLeft(new TreeNodeImpl<>(5, null, null));
		root.left().setRight(new TreeNodeImpl<>(5, null, null));
		root.right().setRight(new TreeNodeImpl<>(5, null, null));
		return root;
	}
	
	private static void print(Integer a) {
		System.out.println(a);
		a = new Integer(10);
	}

	public void printInorder(TreeNode<T> node) {
		if (node != null) {
			System.out.println(node.data());
			printInorder((TreeNode<T>) node.left());
			printInorder((TreeNode<T>) node.right());
		}
	}

	/**
	 * 
	 */
	public static <T> GenericBST<T> getTree() {
		List<Integer> node = new ArrayList<>();
		node.add(2);
		node.add(1);
		node.add(3);
		node.add(7); 
		GenericBST tree = GenericBST.buildTree(node);
		List<Integer> elements = GenericBST.inorderIterative(tree.getHead());
		System.out.println(elements);
		System.out.println(GenericBST.postorderIterative(tree.getHead()));
		return tree;
	}
	//TODO
	// to check
	public static void buildTreeFromTraversal() {
		// 1. get bst tree
		GenericBST<Integer> tree = getTree();
		List<Integer> inoorderTraversal = GenericBST.inorderIterative(tree.getHead());
		List<Integer> postorderTraversal = GenericBST.postorderIterative(tree.getHead());

		TreeNode<Integer> head = new TreeNodeImpl<Integer>(null, null, null);
		buildTreeUtils(inoorderTraversal, postorderTraversal, head);
		
	}

	@Question(description = "Generating tree from inororder and preorder")
	/**
	 * 
	 * Given inorder and postorder build tree
	 * 
	 * @param inorder
	 * @param postorder
	 * @param root
	 */
	public static void buildTreeUtils(List<Integer> inorder, List<Integer> postorder,
			TreeNode<Integer> root) {
		if (root.data() == null && inorder != null && postorder != null) {
			TreeNode<Integer> left = new TreeNodeImpl<Integer>(null, null, null);
			TreeNode<Integer> right = new TreeNodeImpl<Integer>(null, null, null);
			root = new TreeNodeImpl<Integer>(postorder.get(postorder.size() - 1), left, right);

			// left subtree
			Integer rootElement = postorder.get(postorder.size() - 1);
			if (inorder.size() > 1 && postorder.size() > 1) {
				int index = inorder.indexOf(rootElement);
				List<Integer> leftChildInorderTraversal = null;

				leftChildInorderTraversal = inorder.subList(0, index);

				List<Integer> rightChildInorderTraversal = inorder.subList(index + 1, inorder.size());

				Integer firstElementInRightSubTree = inorder.get(index + 1);
				int index1 = postorder.indexOf(firstElementInRightSubTree);

				List<Integer> leftPostOrderTraversal = postorder.subList(0, index1);
				List<Integer> rightPostOrderTraversal = postorder.subList(index1, postorder.size() - 1);

				buildTreeUtils(leftChildInorderTraversal, leftPostOrderTraversal, left);
				buildTreeUtils(rightChildInorderTraversal, rightPostOrderTraversal, right);
			}
		}
	}
	
	private void countSingleValuedTree( ){
		TreeNode<Integer> root = createBinaryTree();
		AtomicInteger count = new AtomicInteger(0);
		countSingleValuedTree(root, count );
		System.out.println("number of singlevaluedTree:"+count);
	}
	

	/**
	 * Find Count of Single Valued Subtrees Given a binary tree, write a program
	 * to count the number of Single Valued Subtrees. A Single Valued Subtree is
	 * one in which all the nodes have same value. Expected time complexity is
	 * O(n). Example 5 1 5 5 5
	 * here answer is  3. 
	 * 
	 * Pseudo code 
	 * 1. traverse the tree in bottom up fashion  and increment count for nodes
	 * and for all where left and right is same  
	 * 
	 * @return
	 */
	
	public static boolean countSingleValuedTree(TreeNode node, AtomicInteger count) {
		if (node == null)
			return true;
		else {
			boolean leftResult = countSingleValuedTree(node.left(), count);
			boolean rightResult = countSingleValuedTree(node.right(), count);
			
			if (leftResult && rightResult) {
				if (node.left() == null && node.right() == null) {
					count.incrementAndGet();
					return true;
				} else if (node.left() == null && node.right() != null) {
					if (node.data().equals(node.right().data())) {
						count.incrementAndGet();
						return true;
					}
				} else if (node.left() != null && node.right() == null) {
					if (node.data().equals(node.left().data())) {
						count.incrementAndGet();
						return true;
					}
				} else {
					if (node.data().equals(node.left().data())
							&& node.data().equals(node.right().data())) {
						count.incrementAndGet();
						return true;
					}
				}
				return false;
			} else {
				return false;
			}
		}
	}
	
}
