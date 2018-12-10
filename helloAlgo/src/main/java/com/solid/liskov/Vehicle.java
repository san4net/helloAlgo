package com.solid.liskov;

/**
 * <ol> L: Liskov substitution principle </ol>
 * 
 * Every subclass or derived class should be substitutable for their parent or base class.
 * 
 * <pre>
 * Subtype Requirement:
 * <p> 
 * Let f(x) be a property provable about objects  x of type T .Then f(y) should be true for objects y of type S where S is a subtype of T.
 * </p>
 * </pre>
 * 
 * @author santosh kumar
 *
 */

public interface Vehicle {
	void addFuel(int liters);
	boolean speed(boolean increase, int delta);
}