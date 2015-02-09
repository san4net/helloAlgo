package com.me.ds.template.begining;

import java.util.ArrayList;
import java.util.List;

import com.core.Queue;
import com.ds.template.TreeNode;
import com.ds.template.impls.QueueImpl;
import com.ds.template.impls.TreeNodeImpl;

public class BSTTree<T> {
	private TreeNode<T> head;
	
	public TreeNode<T> getHead() {
		return head;
	}

	public void setHead(TreeNode<T> head) {
		this.head = head;
	}

	public void add(TreeNode<T> node, T data){
		if(node == null){
			head =  new TreeNodeImpl<>(data, null, null);
		}
		else{
//			Integer tempData = (Integer)node.getData();
		    T tempData = node.getData();
			if((Integer)tempData > (Integer)data){
				if(node.getLeft()== null)
					node.setLeft(new TreeNodeImpl<T>(data, null, null));
				else
				 add((TreeNode<T>) node.getLeft(), data);
			}else {
				if(node.getRight()== null)
					node.setRight(new TreeNodeImpl<T>(data, null, null));
				else
				 add((TreeNode<T>)node.getRight(), data);
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		 List<Number> node = new ArrayList<>();
		 node.add(1);
		 node.add(2);
		 node.add(3);
		 buildTree(node);
		 
	}
	
	public List<T> getInorderTraversal(){
		List<T> elements = new ArrayList<>();
	    inorderTraversal(this.head, elements);
	    return elements;
	} 
	
	public void inorderTraversal(TreeNode<T> node, List<T> elements) {
		if (node == null)
			return;
		else {
			inorderTraversal((TreeNode<T>) node.getLeft(), elements);
			System.out.println(node.getData() + " ");
			elements.add(node.getData());
			inorderTraversal((TreeNode<T>) node.getRight(), elements);
		}
	}
	
	public void inorderTraversal(TreeNode<T> node) {
		if (node == null)
			return;
		else {
			inorderTraversal((TreeNode<T>) node.getLeft());
			System.out.println(node.getData() + " ");
			inorderTraversal((TreeNode<T>) node.getRight());
		}
	}
	
	public List<T> getPostorderTraversal(){
		List<T> elements = new ArrayList<>();
	    postorderTraversal(this.head, elements);
	    return elements;
		
	}
	public void postorderTraversal(TreeNode<T> node, List<T> elements){
		if(node == null ) return;
		else {
			postorderTraversal((TreeNode<T>) node.getLeft(), elements);
			postorderTraversal((TreeNode<T>) node.getRight(), elements);
			elements.add(node.getData());
			System.out.println(node.getData());
		}
	}
	/**
     * 
     * @throws InterruptedException
     */

	public void printLevelTraversal() throws InterruptedException {
		Queue<TreeNode<T>> queue = new QueueImpl<>();
		queue.enqueue(getHead());
		TreeNode<T> endOfLevel = new TreeNodeImpl<T>(null, null, null);
		int i = 1;

		while (queue.size() != 0) {
			queue.enqueue(endOfLevel);
			System.out.print("\n Level " + i +"-");
			i++;
			TreeNode<T> data = (TreeNode<T>) queue.dequeue();
			while (!data.equals(endOfLevel)) {
				System.out.print(data.getData() + ",");
				if (data.getLeft() != null)
					queue.enqueue((TreeNode<T>) data.getLeft());
				if (data.getRight() != null) {
					queue.enqueue((TreeNode<T>) data.getRight());
				}
				data = queue.dequeue();
			}
		}
	}
	
	public static BSTTree< ? extends Number> buildTree(List<? extends Number> nodeData){
	 System.out.println("nodes data" + nodeData);	
	 BSTTree<Number> tree = new BSTTree<>();
	 for(Number node: nodeData){
		 tree.add(tree.getHead(), node);
	 }
	 return tree;
	}
}
