package com.sync.systems.message;

public abstract class AbstractMessage implements Message {
	protected MessageType msgType;
	protected String message;
	public AbstractMessage(MessageType msgType, String message) {
		super();
		this.msgType = msgType;
		this.message = message;
	}
	
	@Override
	public MessageType getType() {
		return msgType;
	}

	@Override
	public String getMsgBody() {
		return message;
	}
	
}
