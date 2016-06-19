package com.ds.qs;

public class CircularLinkedList{
	char[] array;
	int capacity;
	int  start=-1, end=-1;
	public  CircularLinkedList(int size){
		this.array = new char[size];
	 	this.capacity = size;
	}
	
	public void append(char data){
		if(start != -1 && (end-start)+1 == capacity){
			// full 
			return ;
		} 
		else {
			if(start == -1)
			start++;
			this.array[++end] =  data;
		}
	}
	
	public void appendAfter(char data, char after){
		if(start != -1 && (end-start)+1 == capacity){
			// full 
			return ;
		} 
		else {
			int index = indexOf(after);
			for(int i = end ; i > index ; i--){
				array[i+1]=array[i];
			}
			//
			array[index+1] =  data;
		}
		incrementEnd();
	}
	
	public int size(){
	 return -1;	
	}
	
	public void display(char data) {
		int index = indexOf(data);
		if (index == -1)
			return;

		int next = index;
		do {
			System.out.print(array[next]+" ");
			next = nextIndex(next);
		} while (next != index);
		System.out.println("");
	}
	
	int nextIndex( int index){
		if(start == -1 || end ==-1) return -1;
		if(index == end ) return start;
		if(index == (capacity-1)) return 0;
		return ++index;
	}
	
	int indexOf(char c){
		for(int i =start; i< end; i++){
			if(array[i] == c) 
				return i;
		}
		return -1;
	}

	public void delete(char data) {
		int index = indexOf(data);
		if (index == -1)
			return;
		// shift 
		for(int i = index ; i<end; i++){
			array[i]=array[i+1];
		}
		decrementEnd();
	}
	
	public void incrementEnd(){
		end++;
	}
	
	public void decrementEnd(){
		if(start == end){
			start = end =-1;
		}
		end--;
	}
	
}

