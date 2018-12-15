package com.ds.template.impls;

import com.ds.impls.StackImpl;
import com.ds.template.Stack;

import java.util.HashMap;
import java.util.Map;

public class ExpressionTree {
	
	static Map<Character,Integer> precedence = new HashMap<>();
	static{
		precedence.put('(', -1);
		precedence.put('*', 2);
		precedence.put('/', 2);
		precedence.put('+', 1);
		precedence.put('-', 1);
		
	}
	
/**
 * algo
 * if ( push it to stack
 * if operand print it
 * if operator 
 *     if stack is empty push operator 
 *     else
 *     	    temp_operator=stack.pop
 *          if(temp_operato > operator)
 *            print temp_opertor
 *            push operator
 *          else
 *          push temp_operator
 *          push operator      
 * if )
 *   pop and print till matching ) and donot print ( and )
 */
	public static void infixToPostFix(String infixExpression){
		StringBuilder postFix = new StringBuilder();
		Stack<Character> stack = new StackImpl<Character>();
		for(char c :infixExpression.toCharArray()){
			if(c == '(') stack.push(c);
			if(isOperand(c)){
				postFix.append(c);
			}
			
			if(isOperator(c)){
				if(stack.isEmpty()) 
					stack.push(c);
				else{
					char temp_operator = stack.pop();
					
					if(precedence.get(temp_operator)> precedence.get(c)){
						postFix.append(c);
						stack.push(c);
					}else {
						stack.push(temp_operator);
						stack.push(c);
					}
				}
			}
			
			if(c==')'){
				Character a=null;
				while((a= stack.pop())!='('){
					postFix.append(a);
				}
			}
			
		}
		
		while(!stack.isEmpty()){
			postFix.append(stack.pop());
		}
		
		System.out.println(postFix.toString());
	}
	
	public static boolean isOperator(char oper){
		return (oper=='+'|| oper=='-'||oper=='*'||oper=='/');
	}
	
	public static boolean isOperand(char operand){
		return !isOperator(operand) && (')'!=operand) && ('('!=operand);
	}
	
	
	public static void main(String[] args) {
		infixToPostFix("(a*b)+c");
	}
}
