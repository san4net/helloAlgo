package com.solid.command.example2;

import com.solid.command.Command;

public class TaskSolver implements Command {
	
	private Task task;
	
	public TaskSolver(Task task) {
		super();
		this.task = task;
	}



	@Override
	public void execute() {
		task.solveTask();
	}

}
