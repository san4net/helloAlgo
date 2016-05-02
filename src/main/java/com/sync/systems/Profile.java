package com.sync.systems;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Profile per user
 * 
 * @author santosh kumar
 *
 */
public class Profile {
	private String userName;
	private Set<Location> locations = new HashSet<>();
	private WatchService watchService;

	public Profile(String userName) {
		this.userName = userName;
	}

	public Profile(String userName, Set<Location> locations) {
		this(userName);
		this.locations = locations;
		startWaching();
	}

	private void startWaching() {
		try {
			this.watchService = FileSystems.getDefault().newWatchService();

			for (Location l : locations) {
				Path p = Paths.get(l.getLocation());
				p.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
						StandardWatchEventKinds.ENTRY_MODIFY);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void stopWatching() {
		try {
			watchService.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUserName() {
		return userName;
	}

	public Set<Location> getLocations() {
		return new HashSet<>(locations);
	}

	public void updateLocations(Set<Location> locations) {
		stopWatching();
		locations = locations;
		startWaching();
	}

	public static Profile create(String userName) {
		return new Profile(userName);
	}

	public static Profile create(String userName, Set<Location> locations) {
		return new Profile(userName, locations);
	}

	public static class Location implements Serializable {
		private String location;

		public Location(String location) {
			super();
			this.location = location;
		}

		public Path getPath() {
			return Paths.get(location);
		}

		public String getLocation() {
			return location;
		}

		public static Location create(String location) {
			return new Location(location);
		}

		public static Set<Location> create(List<String> strLocation) {
			Set<Location> loc = new HashSet<>();
			for (String l : strLocation) {
				loc.add(create(l));
			}
			return loc;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((location == null) ? 0 : location.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Location other = (Location) obj;
			if (location == null) {
				if (other.location != null)
					return false;
			} else if (!location.equals(other.location))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Location [location=" + location + "]";
		}

		public static void main(String[] args) throws IOException {
			InetAddress addr = InetAddress.getLocalHost();
			String hostname = "\\\\" + addr.getHostName();
			System.out.println(hostname);
			String a = "books" + File.separator + "careercup_4th.pdf";
			Path p1 = Paths.get(hostname, a);
			String b = "books" + File.separator + "test" + File.separator + "careercup_4th.pdf";
			Path p2 = Paths.get(hostname, b);
			Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);

		}
	}

}
