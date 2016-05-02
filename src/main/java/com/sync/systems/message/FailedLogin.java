package com.sync.systems.message;

public class FailedLogin extends AbstractMessage{
	private static final long serialVersionUID = 6107325235440609410L;
	
	public FailedLogin(Login msg) {
		super(MsgType.FAILEDLOGIN, "failed");
	}

	public static FailedLogin create(Login login){
	 return new FailedLogin(login);
 }

	@Override
	public String toString() {
		return "FailedLogin [msgType=" + msgType + ", message=" + message + "]";
	}
}
