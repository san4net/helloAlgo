package com.sync.systems;

import java.util.Properties;

public class ServerProperties extends Properties {
public String getServerIp() {
	return getProperty("server.ip");
}

public int getPort() {
	// TODO Auto-generated method stub
 return Integer.valueOf(getProperty("server.ip"));
}
}
