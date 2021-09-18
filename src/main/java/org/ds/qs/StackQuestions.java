package org.ds.qs;

import org.ds.impls.StackImpl;
import org.ds.template.Stack;

public class StackQuestions {

	/**
	 * Next Greater Element 
	 * Given an array, print the Next Greater Element (NGE)
	 * for every element. The Next greater Element for an element x is the first
	 * greater element on the right side of x in array. Elements for which no
	 * greater element exist, consider next greater element as -1.
	 */
	
	public static void findNextGreaterElements(Integer[] elements){
		/**
		 * pseudo code 
		 * 1. push first elemet to stack 
		 * 2. Iterate for each 2.. N elements call it NEXT 
		 *    - If stack is not empty pop element from stack call it ELEMENT .
		 *       - while NEXT > ELEMENT (this is its next NGN)
		 *         and keep poping elements till this condition satisfies and
		 *         for all of these NEXT is NGN
		 *        - if NEXT IS LESS THEN ELEMENT 
		 *          push back hte ELEMENT
		 *     - push the NEXT to stack 
		 * 
		 *  3. After 2 if stack is not empty for those NGN is -1
		 *       
		 * 
		 */
		
		Stack<Integer> stack = new StackImpl<Integer>(10);
		stack.push(elements[0]);
		int element = -1;
		for( int i=1; i<elements.length; i++){
			int next = elements[i];
			if(!stack.isEmpty()){
				 element = stack.pop();
				
				while(element < next){
					System.out.println(element+"->"+next);
					if(stack.isEmpty()) break;
					element = stack.pop();
				}
				
				// if element is greater push
				if( element > next)
					stack.push(element);
			}
			
			stack.push(next);
		}
		
		while(!stack.isEmpty()){
			System.out.println(stack.pop()+"->"+-1);
		}
		
	}
	
	public static void main(String[] args) {
		Integer[] a= {4,5,2,25};
		findNextGreaterElements(a);
	}
}
