package com.me.ds.template;

public class TestTree<T> {
	TreeNodeImpl<T> root ;
	
	public void add(T data, TreeNode<T> node){
		if(root == null){
			root = new  TreeNodeImpl<T>(data, null, null);
		}
		else{
			if((Integer)node.getData()>(Integer)data){
				if(node.getLeft() == null){
					node.setLeft(new TreeNodeImpl<T>(data, null, null));
				}else{
					add(data, node.getLeft());
				}
			}else{
				if(node.getRight() == null){
					node.setRight(new TreeNodeImpl<T>(data, null, null));
				}else{
					add(data, node.getRight());
				}
				
			}
		}
	}
	

private int findDepth(Node<T> node){
	if(node == null)
		return 0;
	else{
		int left = findDepth(node.getLeft());
		int right = findDepth(node.getRight());
		
		if(left>right){
			return left +1;
		}else{
			return right +1;
		}
	}
}

private void inOrderTraversal(TreeNodeImpl<T> node){
  if(node == null){
	  return;
  }else{
	  System.out.println(node.getData());
	  inOrderTraversal((TreeNodeImpl<T>)node.getLeft());
	  inOrderTraversal((TreeNodeImpl<T>)node.getRight());
  }
	
}

public static void main(String[] args) {
	TestTree<Integer> mytree = new TestTree<Integer>();
	mytree.add(5, mytree.root);
	mytree.add(3, mytree.root);
	mytree.add(4, mytree.root);
	mytree.add(1, mytree.root);
	mytree.add(7, mytree.root);
	mytree.add(6, mytree.root);
	
	System.out.println(mytree.findDepth(mytree.root));
	
	mytree.inOrderTraversal(mytree.root);
}

private void printAtGivenLevel(Node<T> node, int level){
	if(node == null) return;
	
	if(level ==1){
		
	}
	
}

}
