package org.algo.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * you have give three steps that you can climb 1, 2 and 4 and you need to climb
 * n steps on a ladder. Find all different ways like for climbing 3 steps you
 * can Solution: consider f(n) is the number of ways to get to the nth step .
 * There are only two steps that can get you there n-1 and n-2 and n-4. Hence
 * all the way that get you to the step n-1 + number of way that get you to n-2
 * + number of way that get you to n-4
 * 
 * 
 * @author santosh kumar
 *
 */
public class NoOfWayToClimbLadder {
	private static Map<Integer, Integer> memorizationMap = new HashMap<>();
	/**
	 * we know that there are 1 and 2 ways to step 1 and 2 respectively base
	 * case
	 * 
	 */
	static {
		memorizationMap.put(0, 0);
		memorizationMap.put(1, 1);
		memorizationMap.put(2, 2);
	}

	public NoOfWayToClimbLadder() {

	}

	private int getTotalNoOfWaysToClimbLadder(int step) {
		// this is base case
		if (step <= 2) {
			return memorizationMap.get(step).intValue();
		}

		else if (step == 3) {
			if (memorizationMap.get(step) == null) {
				memorizationMap.put(step, getTotalNoOfWaysToClimbLadder(step - 1)
						+ getTotalNoOfWaysToClimbLadder(step - 2));
			}
			return memorizationMap.get(step);
		}

		else {
			int totalWays = getTotalNoOfWaysToClimbLadder(step - 1)
					+ getTotalNoOfWaysToClimbLadder(step - 2) + getTotalNoOfWaysToClimbLadder(step - 4);
			
			memorizationMap.put(step, totalWays);
			
			return memorizationMap.get(step);
		}
	}

	public static void main(String[] args) {
		int i = new NoOfWayToClimbLadder().getTotalNoOfWaysToClimbLadder(4);
		System.out.println(i);
	}
}
