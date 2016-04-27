package com.sync.systems;

import java.util.Set;

/**
 * Profile per user
 * @author santosh kumar
 *
 */
public class Profile {
	private String userName;
    private Set<Location> locations;
    
    public boolean mapLocation(Location location){
    	return locations.add(location);
    }
    
    public void mapFile(String location){
    	
    }
    
    public static class Location{
    	String machineMacAddress;
    	String location;
    	
		public Location(String machineMacAddress, String location) {
			super();
			this.machineMacAddress = machineMacAddress;
			this.location = location;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((location == null) ? 0 : location.hashCode());
			result = prime * result + ((machineMacAddress == null) ? 0 : machineMacAddress.hashCode());
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
			if (machineMacAddress == null) {
				if (other.machineMacAddress != null)
					return false;
			} else if (!machineMacAddress.equals(other.machineMacAddress))
				return false;
			return true;
		}
    	
    }
    
}
