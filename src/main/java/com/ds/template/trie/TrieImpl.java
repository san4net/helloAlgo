package com.ds.template.trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * 
 * We are going to make our own contacts application. You are given the number
 * of operations to perform, . In any contacts application, two basic operations
 * are add and find. The input will be one of the following: 
 * <pre>
 *  1. add name
 *  2. find partial 
 *  </pre>
 *  For the find operation, you will have to print the number of contacts
 * who have a name starting with that partial name.
 * <p>
 * Input Format
 * 
 * The first line contains the integer , the number of operations to be
 * performed. The next lines contains one of the two operations defined above.
 * 
 * Constraints
 * 
 * 
 * The entire input consists of lowercase characters only.
 * 
 * Output Format
 * 
 * For each operation of type find partial, print the number of contacts
 * starting with the string partial.
 * 
 * Sample Input
 * 
 * <pre>
	4
	add hack
	add hackerrank
	find hac
	find hak
	Sample Output
	2
	0
 * </pre>
 * </p>
 * 
 * @author santosh
 * 
 *
 */

public class TrieImpl<T> {
	
	static class Node<T> {
		T data;
		Node<T> next;

		public Node(T data, Node<T> next) {
			super();
			this.data = data;
			this.next = next;
		}

		public T getData() {
			return data;
		}

	}
  // simple linked list for fast operation
	static class LinkedList<T> implements Iterable<T> {
		Node<T> head;
		Node<T> last;
		/**
		 * We are using head and last for quick response
		 * 
		 * @param data
		 */
		public void append(T data) {
			if (head == null) {
				head = new Node<T>(data, null);
				last = head;
			} else {
				Node n = new Node<T>(data, null);
				last.next = n;
				last = n;
			}
		}

		@Override
		public Iterator iterator() {
			return new Iterator<T>() {
				Node temp = head;

				@Override
				public boolean hasNext() {
					return temp != null;
				}

				@Override
				public T next() {
					T data = (T) temp.getData(); // one cost
					temp = temp.next; // one if data[count++] count =
					return data;
				}
			};
		}

	}

	static class TrieNode {
		char data;
		boolean isEnd;
		LinkedList<TrieNode> childs;
		TrieNode parent;
		int childCount = 0;

		public TrieNode(char data, boolean isEnd, TrieNode parent) {
			this.data = data;
			this.isEnd = isEnd;
			this.childs = new LinkedList();
			this.parent = parent;
		}

		public TrieNode subSet(char data) {

			for (TrieNode child : childs) {
				if (child.data == data)
					return child;
			}
			return null;
		}

		TrieNode addChild(char data, boolean isEnd) {
			TrieNode node = new TrieNode(data, isEnd, this);
			childs.append(node);
			return node;
		}

		private void incrementChildCount() {
			childCount++;
		}
		/**
		 * 
		 */
		public boolean addBack() {
			TrieNode temp = this;
			while (temp != null) {
				if(temp.isEnd){
					temp.incrementChildCount();
					if(temp.childCount>1){
						System.out.println("bad set");
						return false;
					}
				}
				temp = temp.parent;
			}
			
			return true;
		}
	}

	TrieNode head = new TrieNode(' ', false, null);

	// operation
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
			parent.addBack();
		}
		return false;
	}


	private TrieNode add(String data, int index, TrieNode parent) {
		parent = parent.addChild(data.charAt(index), index + 1 == data.length());
		return parent;
	}

	public int find(String data) {
		TrieNode current = head;

		for (int index = 0; index < data.length(); index++) {
			current = current.subSet(data.charAt(index));
			if (current == null)
				return 0;
		}

		if (current.isEnd) {
			return current.childCount;
		}
		return current.childCount;
	}

	public static void main(String[] args) throws IOException {
		// Read file
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File("E:/input.txt")));
			String nooftest = reader.readLine();
			Integer limit = new Integer(nooftest);
			TrieImpl<Character> dictionary = new TrieImpl();
			long st = System.currentTimeMillis();
			for (int i = 0; i < limit; i++) {
				String operation = reader.readLine();
				if (operation.startsWith("add")) {
					String[] data = operation.split(" ");
//					String data = operation.substring(operation.indexOf(' ') + 1, operation.length());
					dictionary.add(data[1]);
				} else if (operation.startsWith("find")) {
					String[] data = operation.split(" ");
//					String data = operation.substring(operation.indexOf(' ') + 1, operation.length());
					System.out.println(dictionary.find(data[1]));
				}
			}
			System.out.println("tt: " + (System.currentTimeMillis() - st)  + "sec");
		} finally {
			reader.close();
		}
	}
}
