package com.tc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mockit.NonStrict;

import org.apache.log4j.Logger;

import com.sun.istack.internal.NotNull;

public class ArrayQuestions {
	private static final Logger log = Logger.getLogger(ArrayQuestions.class);
	/**1.
	 * The problem is similar to our old post Segregate 0s and 1s in an array, and both of these problems are variation of famous Dutch national flag problem.
	 * 
	 */
	
	
	/**
	 * Given an array A[] consisting 0s, 1s and 2s, write a function that sorts A[].
	 * The functions should put all 0s first, then all 1s and all 2s in last.
	 * Example:
	 * Input = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
	 * Output = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2}
	 *
	 * @author 
	 *
	 */
	
	public static void sortArrayOfO1(Integer[] A){
		
		//partition array in three 
		// 1. A[1.low-1]
		//2. A[low,high]
		//3. A[high+1,N]
		int low = 0;
		int high = A.length-1;
		while(low<=high){
			if(A[low]==0){
				low++;
			}else{
				while(A[high]==1){
					high--;
				}
			// swap 
			 int temp = A[low];
			 A[low]= A[high];
			 A[high] = temp;
			 low++;
			 high--;
			}
		}
		
	}


/**2.
  * 	
Merge Overlapping Intervals
March 21, 2013

Given a set of time intervals in any order, merge all overlapping intervals into one and output the result which should have only mutually exclusive intervals. Let the intervals be represented as pairs of integers for simplicity.
For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8} }. The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}. Similarly {5, 7} and {6, 8} should be merged and become {5, 8}
  */
 private static class Point{
	 int startingPoint;
	 int endPoint;
	
	 public Point(int startingPoint, int endPoint) {
		super();
		this.startingPoint = startingPoint;
		this.endPoint = endPoint;
	}
	 
   public int getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(int startingPoint) {
		this.startingPoint = startingPoint;
	}

	public int getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}

@Override
public String toString() {
	return startingPoint+","+endPoint;
}	 
 }
 
 private static class MyStack{
	 Point[] elementArray ;
	 int tos =-1;
	 int size;
	public MyStack(int size) {
		super();
		this.elementArray = new Point[size];
		this.size = size-1;
	}
			 
	public boolean push(Point p){
		if(size == tos){
			return false;
		}
		else{
			elementArray[++tos] = p;
		}
		return true;
	}
	
	public Point pop(){
		if(tos==-1){
			return null;
		}
		else{
			return elementArray[tos--];
		}
	}
	public void display(){
		for(int i=0;i<=tos;i++){
			System.out.println(elementArray[i]);
		}
	}
 }
 public static void mergeOverlappingInterval(Point[] pointList){
  
	 // 1. sort array based on starting point 
	 Point[]  sortedPoint = sort(pointList);
	   System.out.println(Arrays.toString(sortedPoint)); 
	   
	   //2. keep a stack 
	   Point[] stack = new Point[sortedPoint.length];
	   
	   MyStack m = new MyStack(sortedPoint.length);
	   m.push(sortedPoint[0]);
	   
	   for(int i=1; i<pointList.length;i++){
		   Point key = pointList[i];
		   Point stackPoint = m.pop();
		   
		   if(key.startingPoint<=stackPoint.getEndPoint()){
			   // merge can happen
			   int newSP = key.getStartingPoint()<=stackPoint.getStartingPoint()? key.getStartingPoint():stackPoint.getStartingPoint();
			   int newEP = stackPoint.getEndPoint()>key.getEndPoint()? stackPoint.getEndPoint():key.getEndPoint();
			    m.push(new Point(newSP,newEP));  
		   }else{
			   m.push(stackPoint);
			  m.push(key); 
		   }
	   }
	   
	   
	   //
	   m.display();
 }

 
 public static Point[] sort(Point[] inputArr){
	 
	 for(int i =1; i<inputArr.length ; i++){
		 Point key = inputArr[i];
		 // find the index in the sorted sub array inputArr[0..i]
		 int j = i-1;
		 while(j>=0 && (key.getStartingPoint()<(inputArr[j].getStartingPoint()))){
			     Point p = inputArr[j];
				 inputArr[j+1]=  p;
				 j--;
		 }
		 inputArr[j+1]= key;
		 
	 }
	 
	 return inputArr;
 }
 public static void  insertionSort(Integer[] A){
		System.out.println("Before"+Arrays.toString(A));
		for(int j =1 ; j<A.length ; j++){
			int key = A[j];
			int i =j-1;
			 while(i>-1 && A[i]>key){
				 A[i+1] = A[i];
				 i--;
			 }
			 A[i+1] = key;
		}
		System.out.println(Arrays.toString(A));
		
	}
 
/**
 * Move all zeroes to end of array
 * 
 * Given an array of random numbers, Push all the zero’s of a given array to the end of the array. 
 * For example, if the given arrays is {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0}, it should be changed to {1, 9, 8, 4, 2, 7, 6, 0, 0, 0, 0}. 
 * The order of all other elements should be same. Expected time complexity is O(n) and extra space is O(1).
 * @param array
 */
	public static void pushAllZero(@NotNull int[] array) {
		// 1 way traverse the array and keep a track for non zero element,by the
		// end of traversal we have all the
		// non zero pushed in the array and the count will point to the first
		// zero element.
		// - we loop and fill the zero

		int nonZeroCount = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0) {
				array[nonZeroCount++] = array[i];
				
			}
		}
			// Now all non zero are pushed
			for (int j = nonZeroCount + 1; j < array.length; j++) {
				array[j] = 0;
		}
		System.out.println(Arrays.toString(array));
		log.info("done");
	}
 
  @NotNull private String names;
  
	public static void main(String[] args) {
		Integer[] A = { 0, 1, 0, 0, 1 };
		System.out.println(Arrays.toString(A));
		ArrayQuestions.sortArrayOfO1(A);
		System.out.println(Arrays.toString(A));
		List<Point> pointList = new ArrayList<Point>();
		ArrayQuestions aq = new ArrayQuestions();
		Point p = new ArrayQuestions.Point(3, 6);
		pointList.add(p);
		p = new ArrayQuestions.Point(6, 6);
		pointList.add(p);
		p = new ArrayQuestions.Point(2, 6);
		pointList.add(p);
		p = new ArrayQuestions.Point(1, 6);
		pointList.add(p);
		p = new ArrayQuestions.Point(8, 9);
		pointList.add(p);
		mergeOverlappingInterval(pointList.toArray(new Point[0]));
		int[] a = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0};
		ArrayQuestions.pushAllZero(null);
		
	}
 
 
}
