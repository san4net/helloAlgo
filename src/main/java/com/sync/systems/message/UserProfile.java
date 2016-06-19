package com.sync.systems.message;

import java.util.Set;

import com.sync.systems.Profile;
import com.sync.systems.Profile.Location;

public class UserProfile extends AbstractMessage {
	private static final long serialVersionUID = 8449807421585296997L;
	private String user;
	private Set<Location> locations;
	
	public UserProfile(MessageType msgType, String message, String user, Set<Location> locations) {
		super(msgType, message);
		this.user = user;
		this.locations = locations;
	}
	
	public String getUser() {
		return user;
	}

	public Set<Location> getLocations() {
		return locations;
	}

	public static UserProfile create(Profile profile) {
		return new UserProfile(MessageType.USER_PROFILE, "profile", profile.getUserName(), profile.getLocations());
	}

	@Override
	public String toString() {
		return "UserProfile [user=" + user + ", locations=" + locations + "]";
	}
 
}
