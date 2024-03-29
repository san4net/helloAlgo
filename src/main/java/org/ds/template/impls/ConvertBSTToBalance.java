package org.ds.template.impls;

import java.util.List;

import org.ds.template.node.TreeNode;

public class ConvertBSTToBalance {


	public static TreeNode<Integer> convertToBalanceBST(List<Integer> nodes, int start, int end) {
		if (start > end)
			return null;
		else {
			int mid = (start + end) / 2;
			TreeNode<Integer> root = new TreeNodeImpl<Integer>(nodes.get(mid), null, null);
			root.setLeft(convertToBalanceBST(nodes, start, mid - 1));
			root.setRight(convertToBalanceBST(nodes, mid + 1, end));
			return root;
		}

	}

	public static void main(String[] args) {

	}

}
