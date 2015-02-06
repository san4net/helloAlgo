package com.ds.qs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	
	
	class MyTree<T> {
		
		TreeNode<T> head = null;

		public void insert(T data, String name, TreeNode<T> tNode){
			if(head==null){
				head = new TreeNode<T>( data,name, null, null);
			}
			
			else{
				Integer i = (Integer) tNode.getData();
				
			  if(i>(Integer)data){
				   if(tNode.getLeft()==null){
					   tNode.left =    new TreeNode<T>(data,name, null, null);
				   }else{
					   insert(data,name,tNode.getLeft());
				   }
			  }else{
				   if(tNode.getRight()==null){
					   tNode.right = new TreeNode<T>(data,name, null, null);
				   }else{
					   insert(data,name,tNode.getRight());
				}
				  
			  }
			}
		}
	}
	
	private static class TreeNode<T> {
		T data;
		String name;
		TreeNode<T> left;
		TreeNode<T> right;

		public TreeNode(T data, String name, TreeNode<T> left, TreeNode<T> right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
			this.name = name;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public TreeNode getLeft() {
			return left;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public TreeNode getRight() {
			return right;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}

	}
	
	
	 public static void main(String args[] ) throws Exception {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));        
	        
	        String s = in.readLine();
	        while (s != null)
	        {         
	        	String[] nodeData = new String[3];
	           // Store line in some internal datastructure  
	            s = in.readLine();
	            if(s!=null && s.startsWith("#")){
	            	continue;
	            }else{
	             nodeData = s.split(",");
	            }
	            
	        }
	        
	        // Do some work
	        
	        // Output using System.out.println();
	    }
}
