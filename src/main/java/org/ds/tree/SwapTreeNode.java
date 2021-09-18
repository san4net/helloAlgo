package org.ds.tree;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SwapTreeNode {

    public static TreeNode createTree(int[][] nodes , int n) throws Exception {
        if (nodes == null) return null;
        return buildTree(nodes , n);
    }


    public static TreeNode buildTree(int[][] indexes , int n) throws Exception {
        ArrayBlockingQueue<TreeNode> queue = new ArrayBlockingQueue(n);
        TreeNode root = new TreeNode(1);
        queue.put(root);

        // we need to make tree
        //  TreeNode temp = root;
        for (int each[] : indexes) {
            TreeNode temp = queue.remove();

            if (each[0] != -1) {
                TreeNode node = new TreeNode(each[0]);
                temp.leftTree = node;
                queue.put(node);
            }
            if (each[1] != -1) {
                TreeNode node = new TreeNode(each[1]);
                temp.rightTree = node;
                queue.put(node);
            }
        }
        return root;
    }

    public static int[] getInorderTraversal(TreeNode node , int n) {
        int[] inorder = new int[n];
        inorderTraversal(node , inorder , new AtomicInteger());
        return inorder;
    }

    public static void inorderTraversal(TreeNode node , int[] data , AtomicInteger index) {
        if (node == null) return;
        else {
            inorderTraversal(node.leftTree , data , index);
            data[index.getAndIncrement()] = node.data;
            inorderTraversal(node.rightTree , data , index);
        }
    }

    public static void swap(TreeNode node , int currentHeight , int k) {
        if (node == null) return;

        if (currentHeight % k == 0) {
            // we need to swap here
            swap(node);
            swap(node.leftTree , currentHeight + 1 , k);
            swap(node.rightTree , currentHeight + 1 , k);
        } else {
            swap(node.leftTree , currentHeight + 1 , k);
            swap(node.rightTree , currentHeight + 1 , k);
        }

    }

    public static void swap(TreeNode node) {
        TreeNode left = node.leftTree;
        node.leftTree = node.rightTree;
        node.rightTree = left;
    }

    public static int findHeight(TreeNode node , int depth) {
        if (node == null) return depth;
        else {
            int lDepth = findHeight(node.leftTree , depth + 1);
            int rDepth = findHeight(node.rightTree , depth + 1);
            return lDepth > rDepth ? lDepth : rDepth;
        }
    }

    public static void displayInorder(TreeNode node , int size) {
        for (int d : getInorderTraversal(node , size)) {
            System.out.print(d + " : ");
        }
    }

    public static class TreeNode {
        private int data;
        private TreeNode leftTree;
        private TreeNode rightTree;

        TreeNode(int data) {
            this.data = data;
            // this.leftTree = leftTree;
            //this.rightTree = rightTree;
        }

        public void setLeft(TreeNode node) {
            this.leftTree = node;
        }

        public void setRight(TreeNode node) {
            this.rightTree = node;
        }

        public int getData() {
            return data;
        }
    }

}
