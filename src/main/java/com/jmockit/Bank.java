package com.jmockit;

public class Bank {
	 
	 DBManager dbManager =new DBManager();
	  
	   
	 public String processAccount(String accountHolder){
	   
	  //Some other code goes here
	   
	  String accountHolderName = dbManager.retrieveAccountHolderName(accountHolder);
	   
	  //some more processing code
	   
	  return accountHolderName;
	 }
	}