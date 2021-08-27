  package com.ds;
  public class LCA{
  
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lcaHelper(root, p, q);      
    }
    
    private TreeNode lcaHelper(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return null;
        
        if( p !=  null && root == p){
          return root;
        }
        
        if( q !=  null && root == q){
           return root;
        }
        
        TreeNode r1 = lcaHelper(root.left, p, q);
        TreeNode r2 = lcaHelper(root.right, p, q);
        
        if(r1 != null && r2 != null){
            return root;
        }
        else if(r1 != null){
            return r1;
        }
        else if(r2 != null){
            return r2;
        }else{
            return null;
        }
        
        
    }
    }
