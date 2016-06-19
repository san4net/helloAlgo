package com.sync.systems.message;

public class SuccesfullLogin extends AbstractMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MessageType msgType = MessageType.SUCCESFULLOGIN;
	private String body ;
	public SuccesfullLogin(Login login) {
		super(MessageType.SUCCESFULLOGIN, "success");
	}
		@Override
	public String toString() {
		return "Response [msgType=" + msgType + "]";
	}
	
	public static SuccesfullLogin createMsg(Login login){
		return new SuccesfullLogin(login);
	}
}
