package com.scjp;

public class Hotel {
    
    public static void book(short a) {
        System.out.print("short ");
    }
    
    public static void book(Short a) {
        System.out.print("SHORT ");
    }
    
    public static int book(Long a) {
        try {
			System.out.print("LONG ");
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		 System.out.println("finally");
		}
		return 0;
    }
    
    public static void main(String[] args) {
        System.out.println(Hotel.book(5l));
        
//        book(shortRoom);
//        book(intRoom);
    }
}