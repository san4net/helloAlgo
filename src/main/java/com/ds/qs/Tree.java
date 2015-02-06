package com.ds.qs;



public class Tree<T> {
	
TreeNode<T> head = null;

public void insert(Integer data){
	if(head==null){
		head = (TreeNode<T>) new Tree.TreeNode<Integer>(data);
	}
	
	/*else{
		Integer i = (Integer) tNode.getData();
		
	  if(i>(Integer)data){
		   if(tNode.getLeft()==null){
			   tNode.left =    new TreeNodeImpl<T>(data,name, null, null);
		   }else{
			   insert(data,name,tNode.getLeft());
		   }
	  }else{
		   if(tNode.getRight()==null){
			   tNode.right = new TreeNodeImpl<T>(data,name, null, null);
		   }else{
			   insert(data,name,tNode.getRight());
		}
		  
	  }
	}*/
	
}
public void insertNode(T data, String name, TreeNode<T> tNode){
	
}
private void prepare(String[] eachNode){
	
}
/*
 * 
 * 
 * B1,10,B2                                  
 * 

B2,B3,4

B3,7,8

  b1
*/
public void find(Integer key , TreeNode<T> headTemp){
//	TNode<?> temp = head;
	
	if(headTemp!=null){
	
		if(key>(Integer)headTemp.getData()){
			find(key,headTemp.getRight());
			
		}else if(key<(Integer)headTemp.getData()){
			find(key,headTemp.getLeft());
		}else{
			if(key ==headTemp.getData()){
				System.out.println("found");
				return;
			}
			
		}
	}  else{
	  System.out.println("notFound");	
	}
}

public void inOrder(TreeNode headTemp){
	if(headTemp != null){
		 inOrder(headTemp.getLeft());
		 System.out.println(headTemp.getData());
		 inOrder(headTemp.getRight());
	}
}

public void preOrder(TreeNode headTemp){
	if(headTemp!=null){
		System.out.println(headTemp.getData());
		preOrder(headTemp.getLeft());
		preOrder(headTemp.getRight());
		
	}
}
public static void main(String[] args) {
    
}

	private static class TreeNode<T> {
		T data;
		String name;
		TreeNode<T> left;
		TreeNode<T> right;
		Integer leftInt;
		Integer rightInt;
		String leftName;
		String rightName;

		public TreeNode(T data) {
			super();
			this.data = data;
//			String tmp[] = data.split(",");
//			this.name = tmp[0];
//			
//			//this.left = left;
//			//this.right = right;
//			this.name = name;
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


}
