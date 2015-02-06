package com.me.ds.template;

public class TreeNodeImpl< T> implements TreeNode<T> {
	private T data;
	private Node<T> left;
	private Node<T> right;

	public TreeNodeImpl(T data, Node<T> left, Node<T> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public T getData() {
		return data;
	}

	@Override
	public Node<T> getLeft() {
		return left;
	}

	@Override
	public void setLeft(Node<T> left) {
		this.left = left;
	}

	@Override
	public Node<T> getRight() {
		return right;
	}

	@Override
	public void setRight(Node<T> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return super.toString() + data;
	}

	public static void main(String[] args) {
	}

}
