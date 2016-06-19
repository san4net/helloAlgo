package com.sync.systems.message;

public class Login extends AbstractMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5630036580635047150L;
	private String user;
	private String password;

	public Login(String user, String password) {
		super(MessageType.LOGIN, "");
		this.user = user;
		this.password = password;
	}

	
	public String getUser() {
		return user;
	}


	public String getPassword() {
		return password;
	}


	@Override
	public MessageType getType() {
		return Message.MessageType.LOGIN;
	}

	@Override
	public String getMsgBody() {
		return "";
	}

	@Override
	public String toString() {
		return "Login [user=" + user + ", password=" + password + "]";
	}
	
	public static Login createMessage(String user, String pass) {
		return new Login(user, pass);
	}
}
