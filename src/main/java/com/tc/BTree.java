package com.tc;


 class Node {
    Node left;
    Node right;
    int value;

    Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;  
    }
}
public class BTree {
    Node root;

    BTree(){
        this.root = null;
    }

    public static void inorderWalk(Node n){
        if(n != null){
            inorderWalk(n.left);
            System.out.print(n.value + " ");
            inorderWalk(n.right);
        }
    }

    public static Node getParent(BTree t, Node n){
        Node current = t.root;
        Node parent = null;


        while(true){
            if (current == null)
                return null;

            if( current.value == n.value ){
                break;
            }

            if (current.value > n.value){
                parent = current;
                current = current.left;
            }
            else{ //(current.value < n.value)
                parent = current;
                current = current.right;
            }       
        }
        return parent;
    }


    public static Node search(Node n,int key){
        if(n == null || key == n.value ){
            return n;
        }
        
        if(key < n.value){
            return search(n.left,key);
        }
        else{
            return search(n.right,key);

        }
    }

   /* private static int isPresent(Node root, int element){
    	 For your reference
    	class Node {
    			Node left, right;
    			int data;
    	                Node(int newData) {
    				left = right = null;
    				data = newData;
    			}
    		}
    	
    	if(root == null )
    		return 0;
//    	 if(root.data == element){
//    		 return 1;
//    	 }
    	if(element > root.value){
    		isPresent(root.right, element);
    	}else{
    		isPresent(root.left, element);	
    	}
    	}*/
    public static Node treeMinimum(Node x){
        if(x == null){
            return null;
        }


        while(x.left != null){
            x = x.left;
        }
        return x;
    }

    public static Node treeMaximum(Node x){
        if(x == null){
            return null;
        }

        while(x.right != null){
            x = x.right;
        }
        return x;   
    }

    public static Node treeSuccessor(BTree t,Node x){
        if (x.right == null){
            return treeMinimum(x.right);
        }
        Node y = getParent(t,x);
        while(y != null && x == y.right){
            x = y;
            y = getParent(t,y);
        }
        return y;   
    }

    public static BTree insert(BTree t,Node z){
        Node y = null;
        Node x = t.root;

        while(x != null){
            y = x;
            if(z.value < x.value)
                x = x.left;
            else
                x = x.right;
        }
        
        Node tmp = getParent(t,z);
        tmp = y;
        if(y == null){
            t.root = z;
        }
        else if(z.value < y.value)
            y.left = z;
        else
            y.right = z;

        return t;
    }


    private static BTree delete(BTree t, Node z){
        Node y,x;
        if (z.left == null || z.right == null)
            y = z;
        else
            y = treeSuccessor(t,z);

        if (y.left != null)
            x = y.left;
        else
            x = y.right;
        if (x != null){
            Node tmp = getParent(t,x);
            tmp = getParent(t,y);
        }

        if (getParent(t,y) == null ){
            t.root = x;
        }
        else{
            if( y == getParent(t,y).left ){
                getParent(t,y).left = x;
            }
            else{
                getParent(t,y).right = x;

            }
    }
        if(y != z){
            z.value = y.value;
        }
    return t;
}

public static void main(String[] args){
    BTree test = new BTree(); 
    Node n1 = new Node(6);
    Node n2 = new Node(3);
    Node n3 = new Node(9);
    Node n4 = new Node(1);
    Node n5 = new Node(16);
    Node n6 = new Node(4);
    Node n7 = new Node(2);
    Node n8 = new Node(11);
    Node n9 = new Node(13);


    test = insert(test,n1);
    test = insert(test,n2);
    test = insert(test,n3);
    test = insert(test,n4);
    test = insert(test,n5);
    test = insert(test,n6);
    test = insert(test,n7);
    test = insert(test,n8);
    test = insert(test,n9);
    inorderWalk(test.root);
    System.out.println();
    test = delete(test,n8);
    inorderWalk(test.root);

    System.out.println();
    test = delete(test,n5);
    inorderWalk(test.root);

    System.out.println();
    test = delete(test,n2);
    inorderWalk(test.root);

    System.out.println();
    test = delete(test,n1);
    inorderWalk(test.root);

}

}