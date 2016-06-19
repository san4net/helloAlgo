package com.sync.systems;

import static com.sync.systems.Utils.log;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.sync.systems.Profile.Location;
import com.sync.systems.message.ProfileUpdate;

public class ProfileManager {
	private Map<String, Profile> profiles = new ConcurrentHashMap<>();
	private Map<String, List<String>> locationToUser = new HashMap<>();
	private List<SyncNotifier> notifier = new ArrayList<>();
	private WatchService watchService;
	private HashSet<String> ignoreLocation = new HashSet();
	
	public synchronized Profile getOrCreate(String userName) {
		Profile p = profiles.get(userName);
		if (p == null) {
			profiles.putIfAbsent(userName, p = Profile.create(userName));
		}
		return p;
	}

	public synchronized void update(ProfileUpdate message) {
		Profile p = profiles.get(message.getUser());
		p = null;
		try {
			profiles.put(message.getUser(), Profile.create(message.getUser(), message.getLocations()));
			updateSingle(getOrCreate(message.getUser()));
			stopWatching();
			startWaching();
		} catch (InterruptedException e) {
			log(e.getMessage());
		}
	}

	private void updateInfo() {
		Iterator<Entry<String, Profile>> iterator = profiles.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Profile> each = iterator.next();
			updateSingle(each.getValue());
		}
	}

	private void updateSingle(Profile profile) {
		// may get Concurrent modification
		Set<Location> locations = profile.getLocations();
		for (Location l : locations) {
			List<String> users  = locationToUser.get(l.getLocation());
			if(users == null){
				locationToUser.put(l.getLocation(), users = new ArrayList<>());
			}
			users.add(profile.getUserName());
//			locationToUser.put(l.getLocation(), profile.getUserName());
		}
	}

	private void startWaching() throws InterruptedException {
		log("start watch servic");
		registerAll();
		watchInSeparate();
	}

	private void registerAll() {
		try {
			this.watchService = FileSystems.getDefault().newWatchService();
			Iterator<Entry<String, Profile>> iterator = profiles.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, Profile> each = iterator.next();
				for (Location l : each.getValue().getLocations()) {
					Path p = Paths.get(l.getLocation());
					p.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
							StandardWatchEventKinds.ENTRY_MODIFY);
				}
			}
		} catch (IOException e) {
			log(e.getMessage());
		}

	}

	private void stopWatching() {
		try {
			if(watchService != null)
			watchService.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void watchInSeparate() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				boolean valid = true;
				do {
					WatchKey watchKey = null;
					try {
						watchKey = watchService.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Set<String> updatedLocation = new HashSet<>();
					// This will aggregate all locations
					for (WatchEvent<?> event : watchKey.pollEvents()) {
						// any event is trigger
						if (StandardWatchEventKinds.ENTRY_CREATE == event.kind()
								|| StandardWatchEventKinds.ENTRY_DELETE == event.kind()
								|| StandardWatchEventKinds.ENTRY_MODIFY == event.kind()) {
							Path updatedFolder = (Path) watchKey.watchable();
							Path full = updatedFolder.resolve((Path) event.context());
							log("" + full);
							if(!ignoreLocation.contains(updatedFolder.toString())){
								updatedLocation.add(updatedFolder.toString());
							}
						}
					}	
					
					Map<String, List<String>> userLocations = new HashMap<>();
					for (String loc : updatedLocation) {
						for (String user : locationToUser.get(loc)) {
							fireNotifier(user, loc);
						}
					}
						
					valid = watchKey.reset();
				} while (valid);

			}
		}).start();
	}

	public void addSyncNotifier(SyncNotifier notifier) {
		this.notifier.add(notifier);
	}

	private void fireNotifier(String user, String location) {
		for (SyncNotifier each : notifier) {
			each.onUpdate(user, location);
		}
	}
	
	public void ignore(String location){
		ignoreLocation.add(location);
	}
	
	public void Noignore(String location){
		ignoreLocation.remove(location);
	}
}
