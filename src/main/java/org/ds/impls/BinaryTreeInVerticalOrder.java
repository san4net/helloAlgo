package org.ds.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ds.template.impls.GenericBST;
import org.ds.template.node.TreeNode;

public class BinaryTreeInVerticalOrder {
   Map<Integer, List<Number>> map = new HashMap<Integer, List<Number>>();
   
	private static int count(int n) {
		if (n == 0 || n == 1)
			return 1;
		else {
			int sum = 0;
			for(int k =1; k<=n; n++){
			int left = count(k - 1);
			int right = count(n - k);
			sum = sum + left*right;
			}
			return sum;
		}
	}
   /**
    * finding the sum of all possible bst 
    * c(n) = sum of  
    * @param n
    * @return
    */
	private static int getAllPossible(int n) {
		if ( n == 0 || n == 1)
			return 1;
		// all possible bst where all can be node at some time
		int allCount = 0;
		for (int i = 1; i <= n; i++) {
			// number ot tree if i is root
			int left = getAllPossible(i-1);
			int right = getAllPossible(n-i);
			allCount = allCount + left * right;
		}
		System.out.println(allCount);
		return allCount;
	}
   
	private void buildTree(){
		List<Integer> nodeData = new ArrayList<>();
		nodeData.add(5);nodeData.add(2);nodeData.add(3);nodeData.add(4);nodeData.add(7);
		GenericBST<Integer> tree = GenericBST.buildTree(nodeData);
		inorderTraversal(tree.getHead(), 0);
		System.out.println(map);
	}
	
	private void inorderTraversal(TreeNode node, int verticalDistance){
		if(node == null) return;
		else{
			inorderTraversal(node.left(), verticalDistance - 1);
			inorderTraversal(node.right(), verticalDistance + 1);
			
			List<Number> nodeList = (List<Number>) (map.get(verticalDistance) == null ? new ArrayList<>():map.get(verticalDistance));
			nodeList.add((Number) node.data());
			map.put(verticalDistance, nodeList);
		}
		
	}

	public static void main(String[] args) {
//		new BinaryTreeInVerticalOrder().buildTree();
		getAllPossible(3);
//		getAllPossible(4);
	}

}
