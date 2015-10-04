package com.ds.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ds.template.impls.BSTTree;
import com.ds.template.TreeNode;

public class BinaryTreeInVerticalOrder {
   Map<Integer, List<Number>> map = new HashMap<Integer, List<Number>>();
   
	private void buildTree(){
		List<Number> nodeData = new ArrayList<>();
		nodeData.add(5);nodeData.add(2);nodeData.add(3);nodeData.add(4);nodeData.add(7);
		BSTTree tree = BSTTree.buildTree(nodeData);
		inorderTraversal(tree.getHead(), 0);
		System.out.println(map);
	}
	
	private void inorderTraversal(TreeNode node, int verticalDistance){
		if(node == null) return;
		else{
			inorderTraversal(node.getLeft(), verticalDistance - 1);
			inorderTraversal(node.getRight(), verticalDistance + 1);
			
			List<Number> nodeList = (List<Number>) (map.get(verticalDistance) == null ? new ArrayList<>():map.get(verticalDistance));
			nodeList.add((Number) node.getData());
			map.put(verticalDistance, nodeList);
		}
		
	}

	public static void main(String[] args) {
		new BinaryTreeInVerticalOrder().buildTree();
		
	}

}
