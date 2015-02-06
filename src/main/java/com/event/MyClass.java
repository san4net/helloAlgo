package com.event;

import javax.swing.event.EventListenerList;

public class MyClass {
   protected EventListenerList eventListenerList = new EventListenerList();

   /**
    * 
    * @param myEventListener
    */
	public void addMyEventListener(MyEventListener myEventListener) {
     eventListenerList.add(MyEventListener.class, myEventListener);
	}
	/**
	 * Allowing classes to unregister from 
	 * 
	 * @param myEventListener
	 */
	public void removeMyEventListener(MyEventListener myEventListener){
	eventListenerList.remove(MyEventListener.class, myEventListener);
	}
	
	private void fireMyEvent(){
		// get all listener
		Object[] listenersList = eventListenerList.getListenerList();
		
		for(int i=0;i<listenersList.length; i = i+2){
			if(listenersList[i] == MyEventListener.class){
				MyEventListener myEventListener = (MyEventListener) listenersList[i+1];
				// make event and pass to listner
				myEventListener.myEventOccurred(new MyEvent(myEventListener));
			}
		}
		
	}
	
}
