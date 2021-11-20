package org.algo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * You are given an array of integers nums, there is a sliding
 * window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 */
public class MaxSumInSlidingWindow {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] r = new int[nums.length-(k-1)];

        Deque<Integer> deque = new ArrayDeque<>();
        for(int index=0 ; index< k; index++){
            while(!deque.isEmpty() && nums[index]>= nums[deque.peekLast()]){
                deque.removeLast();
            }
            deque.addLast(index);
        }

        for(int index=k; index< nums.length ;index++){
            r[index-k] = nums[deque.peekFirst()];
            //removing index if it is less then current
            while(!deque.isEmpty() && nums[index]>=nums[deque.peekLast()]){
                deque.removeLast();
            }

            // checking if deque index are within range k
            while(!deque.isEmpty() && deque.peekFirst() <= index-k){
                deque.removeFirst();
            }
            deque.addLast(index);

        }

        r[nums.length-k] =  nums[deque.removeFirst()];

        return r;
    }

    public static void main(String[] args) {
        int[] rr = maxSlidingWindow(new int[]{7,2,4}, 2);
        System.out.println(Arrays.stream(rr).boxed().collect(Collectors.toList()));
    }
}
