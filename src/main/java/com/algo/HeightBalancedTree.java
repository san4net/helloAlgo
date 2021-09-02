package com.ds;

public class HeightBalancedTree{
   
  
	public boolean isBalanced(TreeNode root) {
      if(root == null) return true;
       
        return (isBalancedHeight(root) != -1);  
        
    }
    
    private int isBalancedHeight(TreeNode node){
        if(node == null) return 0;
        
        int l = isBalancedHeight(node.left);
        if(l == -1) return -1;
        
        int r = isBalancedHeight(node.right); 
        if (r == -1) return -1;
        
        if(Math.abs(l - r) > 1) return -1;
         
        else {
            return Math.max(l,r)+1;
        }
        
    }
}	
