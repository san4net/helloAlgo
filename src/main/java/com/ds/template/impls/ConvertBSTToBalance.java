package com.ds.template.impls;

import java.util.List;

import com.ds.template.node.TreeNode;
import com.google.common.collect.Lists;

public class ConvertBSTToBalance {

	public static GenericBST<Integer> createUnbalanceBST(List<Integer> nodes) {
		GenericBST<Integer> instance = GenericBST.getInstance();
		return instance.buildBST(nodes);
	}

	public static void main(String[] args) {
		GenericBST<Integer> tree = createUnbalanceBST(Lists.newArrayList(3, 2, 1, 4, 5));

		List<Integer> traversal = tree.inorderIterative(tree.head);
		System.out.println(tree.inorderIterative(tree.head));

		TreeNode<Integer> r = convertToBalanceBST(traversal, 0, traversal.size() - 1);
		System.out.println(GenericBST.inorderIterative(r));

	}

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

}
