package com.ds.template;

public interface TreeNode<T> extends Node<T> {
	TreeNode<T> getLeft();
	
	void setLeft(TreeNode<T> left);
	
	TreeNode<T> getRight();
	
	void setRight(TreeNode<T> right);
}
