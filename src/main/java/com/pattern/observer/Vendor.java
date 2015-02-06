package com.pattern.observer;

import java.util.Date;

public class Vendor {

	private TheHindu hindu = new TheHindu("TheHindu"); 
	
	private void registerHindu(HinduReader r){
	hindu.registerObserver(r);	
	}
	
	public static void main(String[] args) {
		Vendor v = new Vendor();
		v.registerHindu(new HinduReader("Santosh"));
		v.paperPublished();
	}
	
  public void paperPublished(){
	  hindu.paperPublished(new Date());
  }
}
