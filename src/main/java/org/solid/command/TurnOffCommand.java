package org.solid.command;

public class TurnOffCommand implements Command {
	
	private Light light;
	
	public TurnOffCommand(Light light) {
		super();
		this.light = light;
	}



	@Override
	public void execute() {
		light.lightOff();
	}

}
