package com.design.pattern.command;
// concrete command
public class ToggleCommand implements Command {
	private ToggleState state = ToggleState.OFF;
	private Light light;
	
	public ToggleCommand(Light light) {
		super();
		this.light = light;
	}

	@Override
	public void execute() {
		if(state == ToggleState.ON){
			light.off();
		}else {
			light.on();
		}
		// toggle the state
		state = state == ToggleState.ON ? ToggleState.OFF : ToggleState.ON;
	}

	enum ToggleState {
		ON, OFF
	};
}
