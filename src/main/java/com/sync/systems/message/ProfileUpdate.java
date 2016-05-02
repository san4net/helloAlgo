package com.sync.systems.message;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sync.systems.Profile.Location;

public class ProfileUpdate extends AbstractMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6490887477567442927L;
	private String userName;
	private Set<Location> locations;
	
	public ProfileUpdate(String userName,Set<Location> location, MsgType msgType, String message) {
		super(msgType, message);
		this.userName = userName;
		this.locations = location;
	}
    
	public static ProfileUpdate create(String userName, List<String> locations) {
		return new ProfileUpdate(userName, Location.create(locations),MsgType.PROFILE_UPDATE, "update");
	}

	
	public String getUserName() {
		return userName;
	}
	
	public Set<Location> getLocations(){
		return new HashSet<>(locations);
	}

	@Override
	public String toString() {
		return "ProfileUpdate [userName=" + userName + "," + locations + "]";
	}
	
}
