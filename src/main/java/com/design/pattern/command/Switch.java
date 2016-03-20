package com.design.pattern.command;
// Invoker
public class Switch {
	public void invoke(Command command){
		command.execute();
	}
	
	public static void main(String[] args) {
		//Receiver
		Light light = new Light();
		//invoker
		Switch swith = new Switch();
		//command
		Command command = new ToggleCommand(light);
		swith.invoke(command);
		swith.invoke(command);
		swith.invoke(command);
	}
}
