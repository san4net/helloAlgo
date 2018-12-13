package com.solid.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database implements PersonDao<Person> {

	private List<Person> personRepo;
	
	public Database() {
	   personRepo = new ArrayList<>();
	}
	
	@Override
	public void insert(Person p) {
		personRepo.add(p);
	}

	@Override
	public void remove(Person p) {
		personRepo.remove(p);
	}

	@Override
	public List<Person> getAll() {
		return Collections.unmodifiableList(personRepo);
	}

}
