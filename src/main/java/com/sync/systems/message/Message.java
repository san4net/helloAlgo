package com.sync.systems.message;

import java.io.Serializable;

public interface Message extends Serializable {
	public static enum MessageType{
		SUCCESS,LOGIN,LOGOUT,UPDATE,SUCCESFULLOGIN,FAILEDLOGIN,PROFILE_REQ,PROFILE_UPDATE,USER_PROFILE,SYNC_CONFIRM_RESPONSE,SYNC_CONFIRM_REQ  ;
	}
	MessageType getType();
	String getMsgBody();
}
