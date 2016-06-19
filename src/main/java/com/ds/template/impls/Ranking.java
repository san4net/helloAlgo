package com.ds.template.impls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Ranking {

    /*
     * You can add more methods here if needed
     */
/*	class test<? extends Number>{
		T num;
		
		
	}*/

	public static void print(Long aa){
		System.out.println("long");
	}
    public static void main(String[] args) {
    	print(10L);
    	print((long)10);
    	HashMap<String, String> ab = new HashMap<>();
    	ab.put(null, "bala");
    	for(String a : ab.keySet()){
    		System.out.println(ab.get(a));
    	}
    	
//    	System.out.println(ab);
    	List<String> a = new ArrayList<>();
    	a.add("A");
    	a.set(0, "B");
    	System.out.println(a);
     /*   List<Student> lst=new ArrayList<Student>();
        Student a=new Student("A1", "John", 87);
        lst.add(a);
        lst.set(index, element)
        Student b=new Student("A2", "Peter", 93);
        lst.add(b);
        Student c=new Student("A3", "David", 81);
        lst.add(c);
        Student d=new Student("A4", "Ferdinand", 89);
        lst.add(d);
        Student top=findTopRanker(lst);
         top.print()*/;
    }

	private static Student findTopRanker(List<Student> lst) {
		 Collections.sort(lst);
		return lst.get(0);
	}

        /*
         * You can add more methods here if needed
         */

    
}

class Student implements Comparable<Student>{
    private String Id;
    private String Name;
    private int Marks;
    
    Student(String id, String name, int marks){
        Id=id;
        Name=name;
        Marks=marks;
    }
    void print(){
        System.out.println("Id="+Id+" Name="+Name+" Marks="+Marks);
    }
    
	public int getMarks() {
		return Marks;
		
	}
	@Override
	public int compareTo(Student other) {
		return (this.getMarks() < other.getMarks()) ? 1 : ((this.getMarks() == other.getMarks()) ? 0 : -1);
	}

}
