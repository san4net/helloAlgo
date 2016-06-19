package com.sync.systems.message;

public class SyncConfirmResponse extends AbstractMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3945955650153643734L;
	private String src ;
	private String user;
	public SyncConfirmResponse(MessageType msgType, String message, String src, String user) {
		super(msgType, message);
		this.src = src;
		this.user = user;
	}
	
	public static SyncConfirmResponse create(String message, String src, String user) {
		return new SyncConfirmResponse(MessageType.SYNC_CONFIRM_RESPONSE, message, src, user);
	}
 
	public String getSrc() {
		return src;
	}
	
	public String getUser() {
		return user;
	}
	@Override
	public String toString() {
		return "SyncConfirmResponse [src=" + src + ", msgType=" + msgType + ", message=" + message + "]";
	}

	
}
