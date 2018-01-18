package com.ds.template.impls;

import com.ds.impls.StackImpl;
import com.ds.template.Stack;
import com.ds.template.node.TreeNode;

public class SerializeTree {
	
	public static TreeNode<Integer>  serializeBSTGivenPreorder(int[] preOrderTraversal){
		TreeNode<Integer> root = TreeNodeImpl.createTreeNode(preOrderTraversal[0]);
		
		Stack<TreeNode<Integer>> stack = new StackImpl<TreeNode<Integer>>();
		 stack.push(root);
		 TreeNode<Integer> temp = null;
		 for(int i=1; i<preOrderTraversal.length;i++){
			 temp = null;
			 while(!stack.isEmpty() && stack.peek().data() < preOrderTraversal[i]){
				 temp = stack.pop();
			 }
			 
			 if(temp!=null){
				 //this is right child
				 temp.setRight(TreeNodeImpl.createTreeNode(preOrderTraversal[i]));
				 stack.push(temp.right());
			 }else{
				 stack.peek().setLeft(TreeNodeImpl.createTreeNode(preOrderTraversal[i]));
				 stack.push(stack.peek().left());
			 }
			 
		 }
		 return root;
	}
	
	public static void main(String[] args) {
		int[] a = {4,2,1,3,6,5,7};
		TreeNode<Integer> root = serializeBSTGivenPreorder(a);
		
		GenericTree.inorderWalk(root);
	}
}
