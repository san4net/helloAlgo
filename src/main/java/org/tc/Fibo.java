package org.tc;

public class Fibo {
	
private int getNo(int num){
	if(num <= 1)		return num;
	
	return getNo(num-1)+getNo(num-2);
}

  private int fibo(int num){
      if (num<=1)
          return num;
      else
     return fibo(num-1) + fibo(num-2);
  }

//fibonacci 0 1 1 2 3 5 8
public static void main(String[] args) {
	System.out.println(0);
	System.out.println(1);
	Fibo fibo = new Fibo();
	for(int i = 2;i<7;i++){
	System.out.println(fibo.getNo(i));
	}
}
}
