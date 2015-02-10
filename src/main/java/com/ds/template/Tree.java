package com.ds.template;

import com.ds.template.impls.Traversal;

/**
 * 
 * @author kumars57
 *
 */

public interface Tree<T> {
    public void add(T data);
    public TreeNode<T> getHead(); 
    public void traversal(Traversal traversal );
}
