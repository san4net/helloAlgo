package com.ds.template.impls;

import com.ds.template.node.Node;
import com.ds.template.node.TreeNode;

public class TestTree<T> {
	TreeNodeImpl<T> root ;
	
	public void add(T data, Node<T> node){
		if(root == null){
			root = new  TreeNodeImpl<T>(data, null, null);
		}
		else{
			if((Integer)node.data()>(Integer)data){
				if(((TreeNode)node).left() == null){
					((TreeNode)node).setLeft(new TreeNodeImpl<T>(data, null, null));
				}else{
					add(data, ((TreeNode)node).left());
				}
			}else{
				if(((TreeNode)node).right() == null){
					((TreeNode)node).setRight(new TreeNodeImpl<T>(data, null, null));
				}else{
					add(data, ((TreeNode)node).right());
				}
				
			}
		}
	}
	

private int findDepth(Node<T> node){
	if(node == null)
		return 0;
	else{
		int left = findDepth(((TreeNode)node).left());
		int right = findDepth(((TreeNode)node).right());
		
		if(left>right){
			return left +1;
		}else{
			return right +1;
		}
	}
}

private int findHeight(Node<T> node){
	if(node == null)
		return 0;
	else{
		int left = findDepth(((TreeNode)node).left());
		int right = findDepth(((TreeNode)node).right());
		
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
	  System.out.println(node.data());
	  inOrderTraversal((TreeNodeImpl<T>)node.left());
	  inOrderTraversal((TreeNodeImpl<T>)node.right());
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
	
	System.out.println("depth" +mytree.findDepth(mytree.root));
	
	mytree.inOrderTraversal(mytree.root);
}

	private void printAtGivenLevel(Node<T> node, int level) {
		if (node == null)
			return;

		if (level == 1) {

		}

	}

}
