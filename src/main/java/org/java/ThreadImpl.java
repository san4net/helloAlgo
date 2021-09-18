package org.java;

import java.util.concurrent.locks.Lock;


/**
 * <properties>
 * <slf4jVersion>1.6.1</slf4jVersion>
 * </properties>
 *         <dependency>
 * <groupId>org.slf4j</groupId>
 * <artifactId>slf4j-api</artifactId>
 * <version>${slf4jVersion}</version>
 * </dependency>
 *
 *
 */

import org.apache.logging.log4j.Logger;

public class ThreadImpl implements Runnable {
	private static final Logger logger = null;
	private Driver.Count id;
	private volatile Driver.MyObject myObject;
	private Lock lock;

	ThreadImpl(Driver.Count id, Driver.MyObject myObject) {
		this.id = id;
		this.myObject = myObject;
	}

	@Override
	public void run() {
		while (true) {
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			
			synchronized (myObject) {
				
				while (!myObject.equals(id)) {
					try {
//						System.out.println("[" + id + "] waiting");
						logger.info("[" + id + "] waiting");
						myObject.wait();
//						System.out.println("[" + id + "] notified");
						logger.info("[" + id + "] notified");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
//				System.out.println("running [" + id+"]");
				logger.info("running [" + id+"]");
				
				if (myObject.equals(Driver.Count.ONE)) {
					logger.info( id+" is setting id to" + Driver.Count.TWO);
					myObject.setId(Driver.Count.TWO);
				} else if (myObject.equals(Driver.Count.TWO)) {
					myObject.setId(Driver.Count.THREE);
					logger.info( id+" is setting id to" + Driver.Count.THREE);
				} else {
					myObject.setId(Driver.Count.ONE);
					logger.info( id+" is setting id to" + Driver.Count.ONE);
				}
				logger.info("[" + id+"] is notifying");
				myObject.notify();
			}
		}

	}

}
