package com.solid.command;

import java.util.ArrayList;

public class CommandDriver {

	/**
	 * Command pattern -> we can encapsulate method invocation, it encapsulate a request
	 *     as an object...
	 *  Good -> the object invoking computation knows nothing about the implementation
	 *  
	 *  component: command, receiver, invoker, client
	 *  
	 *  command: knows about receiver and invokes a method of the receiver
	 *           values for parameters of the receiver method are stored in the command!!!
	 *           
	 *  Receiver: does the work itself
	 *  Invoker : knows how to execute a command and optionally does bookkeeping about the command
	 *  			execution. This knows only about command interface.
	 *  client: decides which command to execute
	 * 
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
	
	Switcher switcher = new Switcher(new ArrayList());	
	Light light = new Light();
	Command on = new TurnOnCommand(light);
	Command off = new TurnOffCommand(light);
	switcher.addCommand(on);
	switcher.addCommand(off);
	switcher.executeCommands();
	
	
	}
}
