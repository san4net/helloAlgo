package com.ds.template.impls;

import java.util.HashSet;
import java.util.Set;

import com.core.Queue;
import com.ds.template.Node;
import com.ds.template.TreeNode;

public class GenericTreePart2<T> {
    private TreeNode<T> head;

    public void addBinaryTree(T data, TreeNode<T> node)
            throws InterruptedException {
        if (node == null) {
            head = node = new TreeNodeImpl<T>(data, null, null);
            return;
        }
        
        Queue<TreeNode<T>> q = new QueueImpl<TreeNode<T>>(25, "queue");
        q.enqueue(node);

        while (!((QueueImpl) q).isEmpty()) {
            TreeNode<T> tmp = q.dequeue();

            if (tmp.getLeft() == null) {
                tmp.setLeft(new TreeNodeImpl<T>(data, null, null));
                break;
            } else if (tmp.getRight() == null) {
                tmp.setRight(new TreeNodeImpl<T>(data, null, null));
                break;
            }
            q.enqueue((TreeNode<T>) node.getLeft());
            q.enqueue((TreeNode<T>) node.getRight());
        }
    }

    boolean isBst(TreeNode<T> node) {
        if (node == null)
            return true;
        if (node.getLeft() != null && (Integer) node.getData() < (Integer) node.getLeft().getData()) {
            return false;
        }
        if (node.getRight() != null && (Integer) node.getData() > (Integer) node.getRight().getData()) {
            return false;
        }

        return (isBst((TreeNode<T>) node.getLeft()) && isBst((TreeNode<T>) node.getRight()));
    }

    public void add(T data, TreeNode<T> temp) {
        if (head == null) {
            head = new TreeNodeImpl<T>(data, null, null);
        } else {
            Object nodeData = null;
            if (data instanceof Long) {
                nodeData = (Long) temp.getData();
            } else if (data instanceof Integer) {
                nodeData = (Integer) temp.getData();
            }

            if (((Integer) nodeData) > (Integer) data) {
                if (temp.getLeft() == null) {
                    TreeNode<T> l = new TreeNodeImpl<T>(data, null, null);
                    temp.setLeft(l);
                } else {
                    add(data, (TreeNode<T>) temp.getLeft());
                }
            } else {
                if (temp.getRight() == null) {
                    TreeNode<T> l = new TreeNodeImpl<T>(data, null, null);
                    temp.setRight(l);
                } else {
                    add(data, (TreeNode<T>) temp.getRight());
                }
            }
        }
    }

    public TreeNode<T> printLCA(TreeNode<T> node, T data1, T data2) {
        if (node == null) {
            return null;
        } else {
            if (node.getData() == data1 || node.getData() == data2) {
                return node;
            }

            TreeNode<T> left = printLCA((TreeNode<T>) node.getLeft(), data1, data2);
            TreeNode<T> right = printLCA((TreeNode<T>) node.getRight(), data1, data2);
            if (left != null & right != null) {
                return node;
            }
            return left == null ? right : left;
        }

    }

    public boolean printAllAncestor(TreeNode<T> treeNode, T data) {
        if (treeNode == null) {
            return false;
        } else {
            if (treeNode.getData() == data) {
                return true;
            } else {
                if (printAllAncestor((TreeNode<T>) treeNode.getLeft(), data)
                        || printAllAncestor((TreeNode<T>) treeNode.getRight(), data)) {
                    System.out.println("ancestorof " + ">" + treeNode.getData());
                    return true;
                }
            }
        }
        return false;
    }

    // TODO
    public TreeNode<T> constructTree(T[] inorderElements, int index) {
        if (index >= inorderElements.length) {
            return null;
        }
        TreeNode<T> node = new TreeNodeImpl<T>(inorderElements[index], null, null);
        return node;
    }

    public static void main(String[] args)
            throws Exception {
        findEdits("AWS is awesome", "AWS ias asm");
        GenericTreePart2<Integer> tree = new GenericTreePart2<Integer>();
        /*
         * tree.add(4, tree.head); tree.add(2, tree.head); tree.add(3,
         * tree.head); tree.add(1, tree.head); tree.add(6, tree.head);
         * tree.add(5, tree.head);
         */

        tree.addBinaryTree(4, tree.head);
        tree.addBinaryTree(2, tree.head);
        tree.addBinaryTree(3, tree.head);
        tree.addBinaryTree(1, tree.head);
        tree.addBinaryTree(6, tree.head);
        tree.addBinaryTree(5, tree.head);
        GenericTree.inorderWalk(tree.head);
        System.out.println("printing children");
        tree.inorderTraverse(tree.head);
        System.out.println("ancestor of 1");
        tree.printAllAncestor(tree.head, 1);
        tree.printAncestor(tree.head, 1);
        System.out.println("lca of 1 and 5 == " + tree.printLCA(tree.head, 1, 5).getData());

    }

    public boolean printAncestor(Node<T> node, T key) {
        if (node == null)
            return false;
        else {
            if (node.getData().equals(key)) {
                return true;
            }

            if (printAncestor(((TreeNode<T>) node).getLeft(), key)
                    || printAncestor(((TreeNode<T>) node).getRight(), key)) {
                System.out.println(node.getData());
                return true;
            }

            return false;
        }
    }

    static int findEditHelper(String source, String destination) {
        Set<Character> sourceSet = new HashSet<Character>();
        for (int a = 0; a < source.length(); a++) {
            sourceSet.add(source.charAt(a));
        }

        if (source != null && destination != null) {

            int l1 = source.length();
            int l2 = destination.length();
            int editcount = 0;

            for (int i = 0; i < l2; i++) {
                /*
                 * if(i == l1 ){ editcount = editcount+ (l2-l1); }
                 */
                if (sourceSet.contains(destination.charAt(i))) {
                    continue;
                }
                if (source.charAt(i) != destination.charAt(i)) {
                    editcount++;
                }
            }

            if (l2 < l1) {
                editcount = editcount + (l1 - l2);
            }

            return editcount;
        }
        return 0;
    }

    static int findEdits(String s, String d) {
        if (s != null && d != null) {
            String[] sArray = s.split(" ");
            String[] dArray = d.split(" ");
            int editcount = 0;

            for (int j = 0; j < dArray.length; j++) {
                editcount = editcount + findEditHelper(sArray[j], dArray[j]);
            }
            return editcount;
        }
        return 0;
    }

    private static byte[] xor(final byte[] input, final byte[] secret) {
        final byte[] output = new byte[input.length];
        if (secret.length == 0) {
            throw new IllegalArgumentException("empty security key");
        }
        int spos = 0;
        for (int pos = 0; pos < input.length; ++pos) {
            output[pos] = (byte) (input[pos] ^ secret[spos]);
            ++spos;
            if (spos >= secret.length) {
                spos = 0;
            }
        }
        return output;
    }

    public void inorderTraverse(Node node) {
        if (node == null)
            return;
        else {
            inorderTraverse(((TreeNode<T>) node).getLeft());
            if (((TreeNode<T>) node).getLeft() == null && ((TreeNode<T>) node).getRight() == null) {
                System.out.println(node.getData());
            }
            inorderTraverse(((TreeNode<T>) node).getRight());
        }
    }
// TODO
    public Object printAncestorWR(Node<T> node, T key) {

        return null;
    }

    // TODO
    private Node<T> buildTree(Integer[] preorder, Integer[] inorder, int start, int end) {
        return null;
    }
}
