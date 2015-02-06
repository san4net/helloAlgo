package com.tc;

import java.io.IOException;

public class Tree {
  Node head;
	private class Node{
		Integer value;
		Node left;
		Node right;
		Node(){
			
		}
		
		Node(Integer v, Node l , Node r){
			value =v;
			left = l;
			right =r;
		}
	}
	
	public void insert(Node head, int val) {
		if (this.head == null) {
			this.head = new Node(val, null, null);

		}

		else if (head == null) {
			head = new Node(val, null, null);
		}

		else {
			if (head.value > val) {
				if (head.left == null) {
					head.left = new Node(val, null, null);
				} else {
					insert(head.left, val);
				}
			} else {
				if (head.right == null) {
					head.right = new Node(val, null, null);
				} else {
					insert(head.right, val);
				}
			}
		}

	}
	
	/**
	 * 
	 * @param name
	 * @param n
	 * @return
	 */
	/**private Node find(String name, Node n){
		   if(n==null){
			   return null;
		   }
		  if(n ==null ||n.name.equals(name)){
			   return n;
		  }
		    find(name, n.left);
		    return find(name, n.right);
	}*/
	
	public void inOrder(Node head){
		if(head!=null){
			inOrder(head.left);
			System.out.println(head.value);
			inOrder(head.right);
		}
	}
	public static boolean isString(String s){
		boolean isNum =true;
		try{
			Integer.valueOf(s);
		}catch(NumberFormatException nfef){
			isNum = false;
		}
		return isNum;
	}
	public static void main(String[] args) throws IOException {
		
		Tree t  = new Tree();
		t.insert(t.head, 3);
		t.insert(t.head, 2);
		t.insert(t.head, 4);
		t.insert(t.head, 1);
		t.inOrder(t.head);
		
	}
}
