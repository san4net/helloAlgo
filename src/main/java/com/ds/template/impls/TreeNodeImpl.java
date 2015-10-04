package com.ds.template.impls;

import com.ds.template.TreeNode	;

public class TreeNodeImpl<T> implements TreeNode<T> {
	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;

	public TreeNodeImpl(T data, TreeNode<T> left, TreeNode<T> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public T getData() {
		return data;
	}

	@Override
	public TreeNode<T> getLeft() {
		return left;
	}

	@Override
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	@Override
	public TreeNode<T> getRight() {
		return right;
	}

	@Override
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return super.toString() + data;
	}

}
