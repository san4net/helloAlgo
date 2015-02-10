package com.ds.template.impls;

import java.util.ArrayList;
import java.util.List;

import com.ds.template.TreeNode;

public class TreeeQuestions<T> {
	/**
	 * given 11 01 11 00 00 
	 */
	
	private TreeNode<T> head;

//	private static Integer index=0;
	private T data;
	
//	private static int data=1;
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	private TreeNode<T> constructTree(Integer[] input , TreeNode<T> node, Integer index){
		
		if (index < input.length) {
			
			setData((T) index);
			
			if (index == 0) {
				head = new TreeNodeImpl<T>(data, null, null);
				node = head;
			} else {
				node = new TreeNodeImpl<T>(data, null, null);
			}

			if (input[index].equals(1)) {
//				index = index + 2;
				node.setLeft(constructTree(input, node, index+2));
			}

			if (input[index + 1].equals(1)) {
//				index = index + 4;
				if(input[index].equals(1)){
					node.setRight(constructTree(input, node,index + 4 ));
				}else {
					node.setRight(constructTree(input, node, index+2));	
				}
			}
			return node;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
//		buildTreeFromTraversal();
		Integer a = null;
		
		System.out.println(a);
		print(a);
	}
	
	private static void print(Integer a){
		System.out.println(a);
		a = new Integer(10);
	}
	
	public void printInorder(TreeNode<T> node){
		if(node != null){
			System.out.println(node.getData());
			printInorder((TreeNode<T>) node.getLeft());
			printInorder((TreeNode<T>) node.getRight());
		}
	}
	
	/**
	 * 
	 */
	public static BSTTree getTree(){
		 List<Integer> node = new ArrayList<>();
		 node.add(2);
		 node.add(1);
		 node.add(3);
		 /*node.add(7);
		 node.add(1);
		 node.add(3)*/;
		 BSTTree tree =  BSTTree.buildTree(node);
		 List<Integer> elements =  tree.getInorderTraversal();
		 System.out.println(elements);
		 System.out.println(tree.getPostorderTraversal());
		 return tree;
	}
	public static void buildTreeFromTraversal(){
		//1. get bst tree
		BSTTree<Integer> tree = getTree();
		List<Integer> inoorderTraversal = tree.getInorderTraversal();
		List<Integer> postorderTraversal = tree.getPostorderTraversal();
		
		com.ds.template.TreeNode<Integer> head = new TreeNodeImpl<Integer>(null, null, null);
		buildTreeUtils(inoorderTraversal, postorderTraversal, head);
		
		BSTTree<Integer> tree1 = new BSTTree<>();
		
		tree1.setHead(head);
		
	}
	
	public static void buildTreeUtils(List<Integer> inorder, List<Integer> postorder, com.ds.template.TreeNode<Integer> root){
		if(root.getData() == null && inorder != null && postorder !=null){
		    TreeNode<Integer> left= new TreeNodeImpl<Integer>(null, null, null);
		    TreeNode<Integer> right=new TreeNodeImpl<Integer>(null, null, null);
			root = new TreeNodeImpl<Integer>(postorder.get(postorder.size()-1), left, right);
			
			// left subtree
			Integer rootElement = postorder.get(postorder.size()-1);
			if (inorder.size() > 1 && postorder.size() > 1) {
				int index = inorder.indexOf(rootElement);
				List<Integer> leftChildInorderTraversal = null;
				
				 leftChildInorderTraversal = inorder.subList(0,index );
				 
				List<Integer> rightChildInorderTraversal = inorder.subList(index + 1, inorder.size());

				Integer firstElementInRightSubTree = inorder.get(index + 1);
				int index1 = postorder.indexOf(firstElementInRightSubTree); 
				
				List<Integer> leftPostOrderTraversal = postorder.subList(0,
						index1);
				List<Integer> rightPostOrderTraversal = postorder.subList(
						index1, postorder.size() - 1);
				
				buildTreeUtils(leftChildInorderTraversal, leftPostOrderTraversal, left);
				buildTreeUtils(rightChildInorderTraversal, rightPostOrderTraversal, right);
			}
		}
	}

}
