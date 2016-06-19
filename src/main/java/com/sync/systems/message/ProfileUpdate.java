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
	private String user;
	private Set<Location> locations;
	
	public ProfileUpdate(String user,Set<Location> location, MessageType msgType, String message) {
		super(msgType, message);
		this.user = user;
		this.locations = location;
	}
    
	public static ProfileUpdate create(String userName, List<String> locations) {
		return new ProfileUpdate(userName, Location.create(locations),MessageType.PROFILE_UPDATE, "update");
	}

	
	public String getUser() {
		return user;
	}
	
	public Set<Location> getLocations(){
		return new HashSet<>(locations);
	}

	@Override
	public String toString() {
		return "ProfileUpdate [user=" + user + "," + locations + "]";
	}
	
}
