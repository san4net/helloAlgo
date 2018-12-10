package com.solid.dao;

import java.util.List;

public interface PersonDao<T> {

	public void insert(T p);
	
	public void remove(T p);
	
	public List<T> getAll();
}
