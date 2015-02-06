package com.ds.qs;

public class Stack  {
 Node<Integer> tos; 
 int size =0;
 String name;
 Stack(String name){
	 this.name = name;
 }
 public void push(Integer num){
	 size++;
	 System.out.println(name +"pushing:"+num);
	 tos = new Node(num, tos);
	 
 }
 public boolean isEmpty(){
	return tos==null;
 }
 public int pop() throws Exception{
	 if(!isEmpty()){
		 int data = (Integer) tos.getData();
		 tos = tos.getNext();
		 size--;
		 System.out.println(name +"poping:"+data);
		 return data;
	 }
	 System.out.println(name +"is empty");
		throw new Exception("no element");	 
 }
 public int getSize(){
	return size;
 }
 
 public void display(){
 Node t = tos;
 while(t !=null){
	 System.out.print("->"+t.getData());
	 t = t.getNext();
 }
 }
 
 public static void main(String[] args) throws Exception {
	Stack s1 = new Stack("s1");
	for(int i=0;i<5;i++){
		s1.push(i);
	}
	System.out.println("s1");
	s1.display();
	System.out.println("removing first item");
	//when pop
	Stack s2 = new Stack("s2");
	while(!s1.isEmpty()){
		s2.push(s1.pop());
	}
   s2.pop();
   
   while(!s2.isEmpty()){
	   s1.push(s2.pop());
   }
   
   System.out.println("s1");
   s1.display();	
}
}
