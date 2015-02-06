package com.ds.qs;


public class MyTree<T> {
	
TNode<T> head = null;
TNode<T> lastNode = null;
public void insert(T data,TNode<T> tNode, String[] nodeData){
	String name = nodeData[0];
	
		if (head == null) {
			head = new TNode<T>(data, name, null, null);
			String leftData = nodeData[1];

			boolean isLeftNum = true, isRightNum = true;
			try {
				int leftInt = Integer.valueOf(leftData);
				head.leftInt = leftInt;

			} catch (NumberFormatException nfe) {
				isLeftNum = false;
			}

			if (!isLeftNum) {
				head.setLeft(new TNode<Integer>(1, leftData, null, null));
			}

			try {
				int rightInt = Integer.valueOf(nodeData[2]);
				head.rightInt = rightInt;

			} catch (NumberFormatException nfe) {
				isRightNum = false;
			}
			if (!isRightNum) {
				head.setLeft(new TNode<Integer>(1, nodeData[2], null, null));
			}
		  lastNode = head;
		}
	
	
	else{
		
	}
	
}
public void insertNode(T data, String name, TNode<T> tNode){
	
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
public TNode find(T data , TNode<T> headTemp){
//	TNode<?> temp = head;
	
	if(headTemp!=null){
	  
		if(headTemp.getName()==data){
//			find(key,headTemp.getRight());
			return headTemp;
			
		}else if(headTemp.left.getName()!=null){
			find(headTemp.getData(),headTemp.getLeft());
		}else{
			if(data ==headTemp.getName()){
				System.out.println("found");
				return null;
			}
			
		}
	}  else{
	  System.out.println("notFound");	
	}
	return headTemp;
}

public void inOrder(TNode headTemp){
	if(headTemp != null){
		 inOrder(headTemp.getLeft());
		 System.out.println(headTemp.getData());
		 inOrder(headTemp.getRight());
	}
}

public void preOrder(TNode headTemp){
	if(headTemp!=null){
		System.out.println(headTemp.getData());
		preOrder(headTemp.getLeft());
		preOrder(headTemp.getRight());
		
	}
}
public static void main(String[] args) {
	MyTree<Integer> t = new MyTree<Integer>();
	
//	t.insert(5, null, t.head);
//	t.insert(3, t.head);
//	t.insert(1, t.head);
//	t.insert(4, t.head);
//	
//	t.insert(7, t.head);
//	t.insert(6, t.head);
//	t.insert(8, t.head);
	
	
//	TreeNodeImpl<?> head1 = t.head;
//   System.out.println(head);
//    t.inOrder(head1);
  // 
    t.find(89, t.head);
    
}

	private static class TNode<T> {
		T data;
		String name;
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		TNode<T> left;
		TNode<T> right;
		Integer leftInt;
		Integer rightInt;
		String leftName;
		String rightName;

		public TNode(T data, String name, TNode<T> left, TNode<T> right) {
			super();
			this.data = data;
			this.name = name;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public TNode getLeft() {
			return left;
		}

		public void setLeft(TNode left) {
			this.left = left;
		}

		public TNode getRight() {
			return right;
		}

		public void setRight(TNode right) {
			this.right = right;
		}

	}
}
