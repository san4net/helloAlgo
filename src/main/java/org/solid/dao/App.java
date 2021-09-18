package org.solid.dao;

public class App {
	
	public static void main(String[] args) {
		Database db = new Database();
		Person moni = new Person("moni", 32);
		db.insert(new Person("soni", 12));
		db.insert(moni);
		db.insert(new Person("tuni", 52));
		
		db.getAll().forEach(System.out::println);
		db.remove(moni);
		db.getAll().forEach(System.out::println);
	}

}
