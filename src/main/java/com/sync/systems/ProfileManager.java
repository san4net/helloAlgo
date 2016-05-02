package com.sync.systems;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sync.systems.message.ProfileUpdate;

public class ProfileManager {
	private Map<String, Profile> profiles = new ConcurrentHashMap<>();

	public synchronized Profile get(String userName) {
		Profile p = profiles.get(userName);
		if (p == null) {
			profiles.putIfAbsent(userName, p = Profile.create(userName));
		}
		return p;
	}

	public synchronized void update(ProfileUpdate message) {
		Profile p = profiles.get(message.getUserName());
		p = null;
		profiles.put(message.getUserName(), Profile.create(message.getUserName(), message.getLocations()));
	}
}
