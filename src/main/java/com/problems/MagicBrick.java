package com.problems;
/**We want to make a row of bricks that is goal inches long. 
 * We have a number of small bricks (1 inch each) and big bricks (5 inches each). Return true if it is possible to make the goal by choosing from the given bricks. This is a little harder than it looks and can be done without any loops.
 *  See also: Introduction to MakeBricks 
 * 
 * @author kumar
 *
 */
public class MagicBrick {
	public boolean makeBricks(int small, int big, int goal) {

		  if(goal>5){
		  int i=1;
		  while(i<=big && goal > 0 && goal >=5){
		   goal = goal - 5;
		   i++;
		  }
		  if(goal ==0) return true;
		  }
		  
		  int i =1;
		  while(i<=small&& goal > 0 && goal >=1){
		   goal = goal - 1;
		   i++;
		  }
		  if(goal ==0) return true;
		  
		  return false;
		}

}
