package org.solid.command;

import java.util.List;

/**
 * 
 * Invoker
 * 
 * @author santosh kumar
 *
 */
public class Switcher {
	private List<Command> commands;

	public Switcher(List<Command> commands) {
		super();
		this.commands = commands;
	}
	
	public void addCommand(Command command) {
		commands.add(command);
	}
	public void executeCommands() {
		commands.forEach(Command::execute);
	}
	

}
