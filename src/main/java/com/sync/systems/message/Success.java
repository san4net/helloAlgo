package com.sync.systems.message;

public class Success extends AbstractMessage {
	private static final long serialVersionUID = -1377010830656904157L;
	
	public Success(MsgType msgType, String message) {
		super(msgType, message);
	}

	public static Success create() {
		return new Success(MsgType.SUCCESS, "success");
	}

	@Override
	public String toString() {
		return "Success [msgType=" + msgType + ", message=" + message +"]";
	}

	
	
}
