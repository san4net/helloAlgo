package org.solid.iterator;

public class StringRepository {

	private String[] names = {"santos","man","dravid","ashraf","at"};
	
	public Iterator<String> getIterator(){
		return new StringArrayIterator(names);
	}
}
