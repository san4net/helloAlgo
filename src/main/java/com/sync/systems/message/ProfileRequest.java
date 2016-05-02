package com.sync.systems.message;

public class ProfileRequest extends AbstractMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7328714094711233202L;
	private String user;
	public ProfileRequest(String user, MsgType msgType, String message) {
		super(msgType, message);
		this.user = user;
	}

	public String getUser() {
		return user;
	}
	
	public static ProfileRequest create(String userName) {
		return new ProfileRequest(userName, MsgType.PROFILE_REQUEST, "profile");
	}
	@Override
	public String toString() {
		return "ProfileRequest [user=" + user + ", msgType=" + msgType + ", message=" + message + "]";
	}
}
