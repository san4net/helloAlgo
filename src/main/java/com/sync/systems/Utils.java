package com.sync.systems;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {
	static enum LEVEL {
		INFO, ERRO;
	}
	
	public static void trace(String msg){
		trace(LEVEL.INFO, msg);
	}
	
	public static void trace(Throwable t){
		trace(LEVEL.ERRO, t.getMessage());
	}
	
	public static void trace(LEVEL level, String msg) {
		if (LEVEL.INFO == level) {
			System.out.println(msg);
		} else if (LEVEL.ERRO == level) {
			System.err.println(msg);
		}
	}
	
	public static ServerProperties load(){
		 ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		 InputStream is = classloader.getResourceAsStream("config.properties");
		 ServerProperties defaultProps = new ServerProperties();
		 try {
			defaultProps.load(is);
		} catch (IOException e) {
			trace(e);
		}
		 return  defaultProps;
	}
	
	public static void main(String[] args) {
	load();	
	}
}
