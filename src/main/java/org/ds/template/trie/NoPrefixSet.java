package org.ds.template.trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.ds.template.trie.TrieImpl.TrieNode;
/**
 * Given  strings. Each string contains only lowercase letters from (both inclusive). The set of  strings is said to be GOOD SET if 
 * no string is prefix of another string else, it is BAD SET. (If two strings are identical, they are considered prefixes of each other.)

	For example, aab, abcde, aabcd is BAD SET because aab is prefix of aabcd.

	Print GOOD SET if it satisfies the problem requirement. 
	Else, print BAD SET and the first string for which the condition fails.
 * @author santosh
 *
 */
public class NoPrefixSet {
    
	TrieNode head = new TrieNode(' ', false, null);
   
	public boolean add(String data) {
		TrieNode parent = head;
		boolean added = false;
		for (int i = 0; i < data.length(); i++) {
			TrieNode previouParent = parent;
			parent = parent.subSet(data.charAt(i));

			if (parent == null) {
				added = true;
				parent = add(data, i, previouParent);
			}
		}

		if (added) {
			return parent.addBack();
		}
		return false;
	}
	
	// we need to traverse and find in case child greater than 1
	private boolean isGoodSet(){
		TrieNode current = head;
		return traverseRecursive(current);
	}
	
	private boolean traverseRecursive(TrieNode node) {
		boolean result = true;
		if (node == null)
			return result;
		else {
				// iterate childs
				for (TrieNode n : node.childs) {
					if(n.isEnd && n.childCount >1){
						return false;
					}
					result = result & traverseRecursive(n);
				}
			return result;
		}
	}
	
	private TrieNode add(String data, int index, TrieNode parent) {
		parent = parent.addChild(data.charAt(index), index + 1 == data.length());
		return parent;
	}
    
    public static void main(String[] args) throws Exception {
        	
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(new File("E:/input_noprefix2.txt")));
        NoPrefixSet noPrefixSet = new NoPrefixSet();
        int numOps = new Integer(br.readLine());
        String line = null; 
        boolean result = true;
			while (numOps > 0) {
				line = br.readLine();
				result = noPrefixSet.add(line);
				if (!result) {
					break;
				}
				numOps--;
			}
			
        if(!result){
        	System.out.println("bad set\n"+line);
        }
        
        if(result){
        	System.out.println("good set");
        }
    }
    
    /*public static void addValue(String s){
        char[] chars = s.toCharArray();
        TrieNode root = null;
        for(char c : chars){
            if(root == null) 
            {
                if(globalSet[getPosition(c)] != null)
                {                
                    root = globalSet[getPosition(c)];
                } else {               
                    int a = getPosition(c);                  
                    NoPrefixSet.TrieNode t = new NoPrefixSet.TrieNode();                
                    globalSet[getPosition(c)] = t;                 
                    root = t;
                }
            }
             else {
                if(root.hasChild(c)){                
                    root = root.getChild(c);              
                } else {               
                    root.addChild(c);
                    TrieNode oldRoot = root;
                    root = root.getChild(c);
                    root.backRoute = oldRoot;                 
                }
            }           
        }
        
        root.addChild('*');
        TrieNode back = root;
        while(back.backRoute != null){
            back = back.backRoute;
            back.numberOfEndsBelow++;
        }
    }*/
    
  /*  public static void findValue(String s){        
        char[] chars = s.toCharArray();
        TrieNode root = globalSet[getPosition(chars[0])];
        if(root == null){
            System.out.println("0");
        } else {          
  
          System.out.println(findInner(1, s, root));  
        }       
        
    }*/
    
}
