package com.pattern.observer;

import java.util.Date;

public class HinduReader implements Observer {
  private String  reader;
	public HinduReader(String name) {
		reader = name;
	}
	@Override
	public void update(Date date) {
    System.out.println("reade"+reader +"paper dated" +date+"delivered");
	}

}
