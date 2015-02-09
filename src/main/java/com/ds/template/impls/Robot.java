package com.ds.template.impls;

public class Robot {
	private int x=-1;
	private int y = -1;
	private Maze m ;
	public Robot(int x, int y, Maze m) {
		super();
		this.x = x;
		this.y = y;
		this.m = m;
	}
	
	public void move(char[] movement){
		for(char c: movement){
			if(c == 'n'){
				if(m.getY()==y){
					System.out.println("forbidden");
				}else{
					y++;
				}
			}else if(c == 's'){
				if(m.getY()==0){
					System.out.println("forbidden");
				}else{
					y--;
				}
			}else if(c == 'e'){
				if(m.getX()==x){
					System.out.println("forbidden");
				}else{
					x++;
				}
				
			}else if(c == 'w'){
				if(m.getX()==0){
					System.out.println("forbidden");
				}else{
					x--;
				}
			}
			
		}
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
