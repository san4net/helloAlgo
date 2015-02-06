package com.event;

import java.util.EventObject;

public class MyEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	public MyEvent(Object source) {
		super(source);
	}
}
