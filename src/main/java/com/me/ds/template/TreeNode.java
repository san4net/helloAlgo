package com.me.ds.template;

public interface TreeNode<T> extends Node<T> {
	Node<T> getLeft();
	
	void setLeft(Node<T> left);
	
	Node<T> getRight();
	
	void setRight(Node<T> right);
}
