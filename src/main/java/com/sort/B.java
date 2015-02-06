package com.sort;

import com.tc.A;

public class B extends A {
	/**
	 * we will try to acces protected variable @iAmProtected using superclass
	 * reference i.e A
	 * 
	 * @param a
	 */
	public void accessProtectedBySuperClassReferrence(A a) {
//		int parentProtectedVariable = a.iAmProtected;
	}

	/**
	 * we will access protected variable @iAmProtected of the superclass in the subclass
	 * via the subclass referrence this is fine 
	 */
	public void accessProtectedByCurrentClassReferrence(B b) {
		int parentProtectedVariable = b.iAmProtected;
		System.out
				.println("Accessing protected member in the subclass B using B refference");
	}

	/**
	 * we will access protected variable @iAmProtected of the superclass in the subclass A
	 * via the subclass subtype referrence this is fine 
	 */
	public void accessProtectedBySubClassReferrence(C c) {
		int parentProtectedVariable = c.iAmProtected;
		System.out
				.println("Accessing protected member in the subclass B using B subclass refference i.e C");
	}
}
