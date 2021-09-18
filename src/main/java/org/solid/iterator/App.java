package org.solid.iterator;

public class App {

	public static void main(String[] args) {
		StringRepository sr = new StringRepository();
		Iterator<String> iter = sr.getIterator();
		while(iter.hashNext()) {
			System.out.println(iter.next());
		}
	}
}
