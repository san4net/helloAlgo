package com.ds.qs;


/**
 *Solution of the Balance the scale tree  for DeepValue
 * 
 * @author santosh kumar
 *
 * @version 1.0
 */
public class BalanceOfScaleTree<T> {
	
	TreeNode<T> head = null;
	
	/**
	 * 
	 * @param head
	 * @param tNode
	 */
	public void insert(TreeNode head, TreeNode<T> tNode) {

		if (this.head == null) {
			this.head = tNode;
		}

		else {
			if (head == null)
				return;
			if (head != null && head.leftName != null
					&& head.leftName.equalsIgnoreCase(tNode.name)) {
				head.left = tNode;
				return;
			}
			if (head != null && head.rightName != null
					&& head.rightName.equalsIgnoreCase(tNode.name)) {
				head.right = tNode;
				return;
			}
			insert(head.left, tNode);
			insert(head.right, tNode);
		}

	}

	/**
	 * Common method to print the tree node with balancing or without balancing
	 * 
	 * @param node
	 * @param balanced
	 */
	public void print(TreeNode node, boolean balanced) {
		if (node == null) {
			return;
		}

		if (balanced) {
			String output = "";
			if (node.isLeftBalanced) {
				output = output + node.balacingFactor + ",0";
			} else {
				output = output + "0," + node.balacingFactor;
			}
			System.out.println(node.name + "," + output);
			print(node.left, true);
			print(node.right, true);
		} else {
			String output = "";
			if (node.leftName == null && node.rightName == null) {
				output = output + node.leftInt + "," + node.rightInt;
			} else if (node.leftName == null && node.rightName != null) {
				output = output + node.leftInt + "," + node.rightName;
			} else if (node.leftName != null && node.rightName == null) {
				output = output + node.leftName + "," + node.rightInt;
			} else {
				output = output + node.leftName + "," + node.rightName;
			}
			System.out.println(node.name + "," + output);
			print(node.left, true);
			print(node.right, true);
		}

	}


	/**
	 * Below is the most important function that Balances the tree based on the
	 * left and right balances
	 * 
	 * @param balance
	 */
	private void balance(TreeNode head) {
		if (head.leftName == null && head.rightName == null) {
			if (head.leftInt > head.rightInt) {
				head.balacingFactor = (head.leftInt - head.rightInt);
				head.isRightBalanced = true;
			}
			if (head.leftInt < head.rightInt) {
				head.balacingFactor = (head.rightInt - head.leftInt);
				head.isLeftBalanced = true;
			}
		} else if (head.rightName == null && head.leftName != null) {
			balance(head.left);
			int sumLeft = getSum(head.left);
			if (sumLeft > head.rightInt) {
				head.balacingFactor = sumLeft - head.rightInt;
				head.isRightBalanced = true;
			} else {
				head.balacingFactor = head.rightInt - sumLeft;
				head.isLeftBalanced = true;
			}

		} else if (head.rightName != null && head.leftName == null) {
			balance(head.right);
			int sumRight = getSum(head.right);
			if (sumRight > head.leftInt) {
				head.balacingFactor = sumRight - head.leftInt;
				head.isLeftBalanced = true;
			} else {
				head.balacingFactor = head.leftInt - sumRight;
				head.isRightBalanced = true;
			}

		} else if (head.rightName != null && head.leftName != null) {
			balance(head.left);
			balance(head.right);
			int sumRight = getSum(head.right);
			int sumLeft = getSum(head.left);

			if (sumLeft > sumRight) {
				head.balacingFactor = sumLeft - sumRight;
				head.isRightBalanced = true;
			} else {
				head.balacingFactor = sumRight - sumLeft;
				head.isLeftBalanced = true;
			}
		}
	}

	private int getSum(TreeNode node) {
		int bal = 0;
		if (node == null)
			return 0;
		/*
		 * if (node.leftName != null && node.rightName != null) { return bal =
		 * bal + 1; } else
		 */if (node.leftName == null && node.rightName == null) {
			return bal = node.leftInt + node.rightInt + node.balacingFactor + 1;
		} else if (node.leftName != null && node.rightName == null) {
			return bal = node.balacingFactor + node.rightInt
					+ getSum(node.left) + 1;
		} else if (node.leftName == null && node.rightName != null) {
			return bal = node.balacingFactor + node.leftInt
					+ getSum(node.right) + 1;
		} else
			return node.balacingFactor + getSum(node.left) + getSum(node.right)
					+ 1;

	}
	
/**
 * TreeNodeImpl data structure of {@linkplain}{@link BalanceOfScaleTree}
 * 
 * @author santosh kumar
 *
 * @version 1.0
 */
	private static class TreeNode<T> {
		private int balacingFactor;
		private String data;
		private String name;
		private TreeNode<T> left;
		private TreeNode<T> right;
		private Integer leftInt;
		private Integer rightInt;
		private String leftName;
		private String rightName;
		@SuppressWarnings("unused")
		boolean isRightBalanced = false;
		boolean isLeftBalanced = false;

		/**
		 * Prepare the node depending upon the input like sample input is
		 * B1,25,0
		 * 
		 * @param data
		 */
		public TreeNode(String data) {
			super();
			this.data = data;
			String s[] = data.split(",");
			this.name = s[0];
			if (isNumber(s[1]) && !isNumber(s[2])) {
				leftInt = Integer.parseInt(s[1]);
				left = null;
				rightName = s[2];
			}
			if (isNumber(s[2]) && !isNumber(s[1])) {
				rightInt = Integer.parseInt(s[2]);
				right = null;
				leftName = s[1];

			}

			if (!isNumber(s[2]) && !isNumber(s[1])) {
				this.leftName = s[1];
				this.rightName = s[2];
			}

			if (isNumber(s[2]) && isNumber(s[1])) {
				rightInt = Integer.parseInt(s[2]);
				leftInt = Integer.parseInt(s[1]);
				right = null;
				left = null;

			}
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
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

		public boolean isNumber(String s) {

			try {
				Integer.parseInt(s);
				return true;
			} catch (NumberFormatException nfe) {
				return false;
			}
		}

		@Override
		public String toString() {
			return "TreeNodeImpl [data=" + data + ", name=" + name + ", left="
					+ left + ", right=" + right + ", leftInt=" + leftInt
					+ ", rightInt=" + rightInt + ", leftName=" + leftName
					+ ", rightName=" + rightName + "]";
		}
	}
	
	/**
	 * Main Method giving two approach to run and see the result
	 * 1. via input system console
	 * 2. via string array of inputs
	 * 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// 1.
		BalanceOfScaleTree balanceofScaleTree = new BalanceOfScaleTree();
		
		/**
		 * APPROACH 1: 
		 *    			 -  Taking input from the system
		 *            	 -  Making the required tree of scale
		 *            	 -  Balancing the tree 
		 *           	 -  printing the tree with and without balances 
		 *        
		 * 
		 */

		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String s = br.readLine();
//		while (s != null) {
//			if (s == null || s.startsWith("#")) {
//				continue;
//			}
//
//			TreeNodeImpl node = new TreeNodeImpl(s); // creating tree based on input
//			balanceofScaleTree.insert(balanceofScaleTree.head, node);
//		}

		/**
		 * APPROACH 2): 
		 * 				- Taking input in string array
		 *              - Making the required tree of scale
		 *              - Balancing the tree 
		 *          	-  printing the tree with and without balances 
		 */

		String[] arg = { "B1,10,B2", "B2,b3,4", "B3,7,8" };
		String[] arg1 = { "B1,5,6" };
		String[] arg2 = { "B1,10,B2", "B2,B3,B4", "B3,7,B5", "B4,6,3", "B5,1,2" };

		String[] arg3 = arg;

		/**
		 * Creating tree based on the argument provided
		 * 
		 */
		for (int i = 0; i < arg3.length; i++) {
			TreeNode node = new TreeNode(arg3[i]);
			balanceofScaleTree.insert(balanceofScaleTree.head, node);
		}
		
		/**
		 * printing trees without balancing
		 * 
		 */
		System.out.println("Printing the input:");
		balanceofScaleTree.print(balanceofScaleTree.head, false);
		/**
		 * doing the balancing
		 */
		balanceofScaleTree.balance(balanceofScaleTree.head);
		
		/**
		 * Printing the trees with balancing factor like 
		 */
		System.out.println("Balance of scale tree");
		balanceofScaleTree.print(balanceofScaleTree.head, true);
	}
}
