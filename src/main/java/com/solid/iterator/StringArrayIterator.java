package com.solid.iterator;

public class StringArrayIterator implements Iterator<String> {

	private int index; 
	
	private String[] stringArray;
	
	
	public StringArrayIterator(String[] stringArray) {
		this.stringArray = stringArray;
	}

	@Override
	public boolean hashNext() {
		return index < stringArray.length;
	}

	@Override
	public String next() {
		if(hashNext()) {
			return stringArray[index++];
		}
		
		return null;
	}

}
