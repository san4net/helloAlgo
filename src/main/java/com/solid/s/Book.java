package com.solid.s;

public class Book implements BookOperations{
	private int pageNumber;
	private String author;

	public Book(int pageNumber, String author) {
		super();
		this.pageNumber = pageNumber;
		this.author = author;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [pageNumber=" + pageNumber + ", author=" + author + "]";
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

}
