package org.solid.command.example2;

public class Task {
	private int id;
	
	public Task(int id) {
		super();
		this.id = id;
	}


	public void solveTask() {
		System.out.println("solving task with id:"+id);
	}

}
