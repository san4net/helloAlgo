package com.thread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BaiterConsumer1 implements Callable<String> {
ReentrantLock lock;
List<String> plates;
Condition notEmpty;
public BaiterConsumer1(ReentrantLock lock, List<String> plates, Condition condition ) {
	super();
	this.lock = lock;
	this.plates = plates;
	this.notEmpty = condition;
}

@Override
public String call() throws Exception {
    lock.lock();
     try {
		while(plates.size()==0){
			 notEmpty.await();
		 }
	} catch (Exception e) {
		e.printStackTrace();
		notEmpty.signal();
	}
	return null;
}


}
