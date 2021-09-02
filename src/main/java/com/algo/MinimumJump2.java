package com.ds;

/**
 * https://leetcode.com/problems/jump-game-ii/
 * 1.https://www.youtube.com/watch?v=vBdo7wtwlXs
 */
public class MinimumJump2 {
    public int jump(int[] nums) {
        if( nums== null || nums.length==1){
            return 0;
        }

        int cur_ladder=nums[0];
        int max_ladder = cur_ladder;
        int index=1;
        int steps=0;

        while(cur_ladder < nums.length-1){
            max_ladder = Math.max(max_ladder, index + nums[index]);

            if(index == cur_ladder){
                steps++;
                cur_ladder = max_ladder;
            }
            index++;
        }

        return steps+1;

    }
}
