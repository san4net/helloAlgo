package org.ds.template.node;

public interface TreeNode<T> extends Node<T> {
	TreeNode<T> left();
	
	void setLeft(TreeNode<T> left);
	
	TreeNode<T> right();
	
	void setRight(TreeNode<T> right);

}
