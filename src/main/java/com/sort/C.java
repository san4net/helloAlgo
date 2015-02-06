package com.sort;

import com.tc.A;

public class C extends B {

public void accessProtectedBySuperClassReferrence(A a){
	/**
	 *  we will try to acces protected variable @iAmProtected 
	 *  using superclass reference i.e A
	 */
//  int parentProtectedVariable = a.iamp;
}

public void accessProtectedByCurrentClassReferrence(B b){
	/**
	 *  we will try to acces protected variable @iAmProtected  \
	 *  this is fine 
	 */
//	int parentProtectedVariable = b.iAmProtected;
	System.out.println("Accessing protected member in the subclass B using B refference");
}

public void accessProtectedBySubtClassReferrence(C c){
	/**
	 *  we will try to acces protected variable @
	 *  this is fine 
	 */
	int parentProtectedVariable = c.iAmProtected;
	System.out.println("Accessing protected member in the subclass B using B subclass refference i.e C");
}
}
