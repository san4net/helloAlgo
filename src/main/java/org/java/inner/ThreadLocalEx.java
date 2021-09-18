package org.java.inner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadLocalEx {
	private  String hey="";
	@SuppressWarnings("unused")
	private final ThreadLocal<String> abc = new ThreadLocal<String>(){
		protected String initialValue() {
			new  ThreadLocalEx(){
				String a = hey;
			};
			new ActionListener() {
				int a = 5;
				@Override
				public void actionPerformed(ActionEvent e) {
				hey = "dkd";
				}
			};
			return hey;
		};
		public String get() {
			return abc.get();
		};
	};

}
