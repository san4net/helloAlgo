package org.tc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SmallestDistance {
    
	private int getSmallestDistance(List<Point> points,int startIndex, int lenght){
		int smallest = Integer.MAX_VALUE;
		for(int i =startIndex; i<lenght;i++){
			for(int j= i+1;j<lenght;j++){
				Point first = points.get(i);
				Point second = points.get(j);
				 Double dis = Math.sqrt((first.x-second.x)*(first.x-second.x) + (first.y-second.y)*(first.y-second.y));
				 if(dis<smallest){
					smallest = dis.intValue();
				 }
			}
		}
		
		return smallest;
	}
	
	private int smallestDistance(List<Point> points,int startIndex, int length){
		
		if(length - startIndex<=3){
		 return getSmallestDistance(points, startIndex, length);
		}
		int mid = length/2;
		int l1 = smallestDistance(points, startIndex, mid);
		int l2 = smallestDistance(points, mid+1, length);
		 if(l1>l2){
			 return l2;
		 }
		return l1;
		
	}
	private class Point{
		public int x,y;
		public Point(int x, int y) {
			this.x =x;
			this.y = y;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "x-"+x+"y-"+y;
		}
	}
	
	public static void main(String[] args) {
		List<Point> pointList = new ArrayList<Point>();
		SmallestDistance sm = new SmallestDistance();
		for(int i=0;i<6;i++){
			int y =  new Random().nextInt(10);
			Point p = sm.new Point(i, y);
			
			pointList.add(p);
		}
		System.out.println(pointList);
		
		int smallest = sm.smallestDistance(pointList, 0, pointList.size()-1);
		
		System.out.println(smallest);
		
	}
}
