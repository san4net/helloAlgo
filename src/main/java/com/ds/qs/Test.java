package com.ds.qs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Test {
	static List sngGamePlayTypeList;
	static{
		sngGamePlayTypeList = new ArrayList<Integer>();
		sngGamePlayTypeList.add(0);
		sngGamePlayTypeList.add(1);
		sngGamePlayTypeList.add(3);
		sngGamePlayTypeList.add(5);
	}
	
	AtomicLong sngGamePlayIndex = new AtomicLong(Long.MAX_VALUE);
	
	public void test(){
		
		for(int i =0; i<12;i++){
		System.out.println(sngGamePlayIndex.get()%2);
		sngGamePlayIndex.incrementAndGet();
		
		}
		/*
		//
		 try{
		sngGamePlayIndex.getAndIncrement();
		 if(sngGamePlayIndex.intValue() == sngGamePlayTypeList.size()){
			 sngGamePlayIndex.set(0);
		 }
		 
//		 jmeterService.setThreadContextVariable("sngGamePlayType", ""+sngGamePlayTypeList.get(sngGamePlayIndex.get()));
		 
		 String sg = "gamePlayType"+sngGamePlayTypeList.get(sngGamePlayIndex.get());
		 
		 System.out.println(sg);
			 
//		System.out.println(sngGamePlayIndex.getAndIncrement());
		 }catch(Exception e){
		 }
		 
      }
	*/}
	 public static void main(String[] args) {
	 new Test().test();
}
}
