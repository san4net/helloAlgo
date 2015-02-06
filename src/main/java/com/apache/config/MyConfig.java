package com.apache.config;

import java.util.Iterator;

import org.apache.commons.configuration.AbstractConfiguration;

public class MyConfig extends AbstractConfiguration {

	public MyConfig() {
		
	}
    
	@Override
	public boolean containsKey(String arg0) {
		return false;
	}

	@Override
	public Iterator<String> getKeys() {
		return null;
	}

	@Override
	public Object getProperty(String arg0) {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	protected void addPropertyDirect(String arg0, Object arg1) {

	}

}
