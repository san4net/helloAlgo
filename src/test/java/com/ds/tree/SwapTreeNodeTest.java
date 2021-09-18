package com.ds.tree;

import org.ds.tree.SwapTreeNode;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/swap-nodes-algo/problem
 */
public class SwapTreeNodeTest {

    private  static SwapTreeNode.TreeNode root;

    @BeforeClass
    public static  void setup() throws Exception {
        int nodes[][] = {{2 , 3} , {4 , 5}, {-1,-1}};
        int n = 5;
        root = SwapTreeNode.createTree(nodes , n);
    }

    @Test
    public void testTreeCreation() throws Exception {
        assertEquals(1, root.getData());
    }

    @Test
    public void testInorderTraversal() {
      int[] data = SwapTreeNode.getInorderTraversal(root, 5);
      int[] exp = {4,2,5,1,3};
      assertArrayEquals( exp, data);
    }

    /**    1          1                1
     *   2   3  - > 3   2      ->    3   2
     * 4  5           4   5            5   4
     *
     */
    @Test
    public void testSwap() {
        int height = SwapTreeNode.findHeight(root, 0);
        assertEquals(3, height);
        SwapTreeNode.swap(root,1, 1);
        SwapTreeNode.displayInorder(root, 5);

    }
}