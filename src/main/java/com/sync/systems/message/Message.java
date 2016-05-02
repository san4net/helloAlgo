package com.sync.systems.message;

import java.io.Serializable;

public interface Message extends Serializable {
	public enum MsgType{
		SUCCESS,LOGIN,LOGOUT,UPDATE,SUCCESFULLOGIN,FAILEDLOGIN,PROFILE_REQUEST,PROFILE_UPDATE,USER_PROFILE ;
	}
	
	MsgType getType();
	String getMsgBody();
}
