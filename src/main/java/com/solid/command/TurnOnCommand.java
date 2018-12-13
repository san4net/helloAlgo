package com.solid.command;

public class TurnOnCommand implements Command {
	
	Light light;
	
	public TurnOnCommand(Light light) {
		super();
		this.light = light;
	}



	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		light.lightOn();
	}

}
