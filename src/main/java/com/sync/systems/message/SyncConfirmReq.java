package com.sync.systems.message;

public class SyncConfirmReq extends AbstractMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7834589747157084510L;
	private String src;
	public SyncConfirmReq(MessageType msgType, String message, String src) {
		super(msgType, message);
		this.src = src;
	}
	
	public static SyncConfirmReq create(String message, String src) {
		return new SyncConfirmReq(MessageType.SYNC_CONFIRM_REQ, message, src);
	}

	
	public String getSrc() {
		return src;
	}

	@Override
	public String toString() {
		return "SyncConfirmReq [msgType=" + msgType + ", message=" + message + "]";
	}
	
}
