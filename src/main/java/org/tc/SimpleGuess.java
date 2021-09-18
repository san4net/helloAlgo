package org.tc;

/**
 * 
 * @author ivy4467
 *
 */
public class SimpleGuess {
	
public long getMaximum(int[] inputs){
	int max = -1;
	for(int i=0;i<inputs.length;i++){
		
		for(int j=0;j<inputs.length;j++){
			if(i!=j){
				// take pair sum and sub
				int sum = inputs[i];
				int sub = inputs[j];
				
				//all are integer so sum must be  integer as sum is 2x 
				if((sum+sub)%2 == 0){
					int x = (sum+sub)/2;
					int y = sum-x;
					if(x>0 && y>0){
						max = (max > x*y)?max:x*y;
					}
				}
			}
			
		}
	}
	return max;
 }


public static void main(String[] args) {
	int arguments[] = {1,4,5};
	SimpleGuess sg = new SimpleGuess();
	System.out.println(sg.getMaximum(arguments));
}
}
