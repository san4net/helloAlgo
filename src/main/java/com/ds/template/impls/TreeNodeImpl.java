package com.ds.template.impls;

import com.ds.template.node.Node;
import com.ds.template.node.TreeNode;
/**
 * This class represent binary tree node
 * 
 * @author santosh kumar
 *
 * @param <T>
 */
public class TreeNodeImpl<T> implements TreeNode<T> {
	private T data ;
	private TreeNode<T> left;
	private TreeNode<T> right;

	public TreeNodeImpl(T data, TreeNode<T> left, TreeNode<T> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public static TreeNode<Integer> createTreeNode(int i) {
		return null;
	}

	public T data() {
		return data;
	}

	@Override
	public TreeNode<T> left() {
		return left;
	}

	@Override
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	@Override
	public TreeNode<T> right() {
		return right;
	}

	@Override
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "[TreeNodeImpl data=" + data + "]";
	}


}
