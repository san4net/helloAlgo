package com.tc;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Given a string consisting of a,b and c’s, we can perform the following operation: Take any two adjacent 
 * distinct characters and replace it with the third character. For example, if ‘a’ and ‘c’ are adjacent, 
 * they can replaced with ‘b’. What is the smallest string which can result by applying this operation 
 * repeatedly?
 * 
 * @author ivy4467
 *
 */
public class StringReduction {
  //take a stack
	//1. loop from 1 to n
	//2. keep result in another memory and keep comparing it with  result 
	// if changed loop entire result for change if two same found break
	
	//2.  abc 
	// 1. r =a  b r =c 
	
	
	public String getStringSize(String input){
		String result = "";
		
		for(int i =0;i<input.length(); i++){
			if(i==0){
				result = ""+input.charAt(i);
				continue;
			}
			
			if(input.charAt(i) != result.charAt(result.length()-1)){
				if(result.length()>1){
				char c = result.charAt(result.length()-1);
				result =  (String) result.subSequence(0,result.length()-1);
				result = result	+ getChar(input.charAt(i), c);
				
				result = processChange(result);
				
				} else {
				result = ""+ getChar(input.charAt(i), result.charAt(0));
				}
			}else{
			result = result + input.charAt(i);	
			}
			
		}
		System.out.println(result);
		return result;
	}
	
	public static void main(String[] args) {
		StringReduction sr = new StringReduction();
		sr.getStringSize("abcabc");
		//j 1. ab-c  cca -> cb -> a    ab -cc
	}
   private String processChange(String input){
	   String result =input;
	   
	   for(int i =input.length()-1;i>0;i--){
		if(input.charAt(i) == input.charAt(i-1)){
			break;
		}else{
		  
		  input = input.substring(0, i-1)+ getChar(input.charAt(i), input.charAt(i-1));	
		}
	   }
	  return input;
   }
   
	public Character getChar(Character first, Character second){
		Character r = null;
		if( (first=='a' || second=='a') &&(first=='b' || second=='b') ){
			r = 'c';
		}
		else if( (first=='a' || second=='a') &&(first=='c' || second=='c') ){
			r = 'b';
		}
		else if( (first=='c' || second=='c') &&(first=='b' || second=='b') ){
			r = 'a';
		}
		return r;
	}
	//1. it has top and u can put and get
}

