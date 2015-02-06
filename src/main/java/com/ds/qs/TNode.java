package com.ds.qs;

public class TNode<T> {
T data;
TNode<?> left;
TNode<?> right;

TNode(T data, TNode<?> l, TNode<?> r){
	this.data = data;
	this.left =l;
	this.right=r;
}

TNode<?> getLeft(){
	return left;
}
TNode<?> getRight(){
	return left;
}
T getData(){
	return data;
}
}
