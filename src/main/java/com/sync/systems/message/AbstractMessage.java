package com.sync.systems.message;

public abstract class AbstractMessage implements Message {
	protected MsgType msgType;
	protected String message;
	
	public AbstractMessage(MsgType msgType, String message) {
		super();
		this.msgType = msgType;
		this.message = message;
	}

	@Override
	public MsgType getType() {
		return msgType;
	}

	@Override
	public String getMsgBody() {
		return message;
	}

	
}
