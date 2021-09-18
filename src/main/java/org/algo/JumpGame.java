package org.algo;

/**
 * 1. https://leetcode.com/problems/jump-game/
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int furthest_reach_so_far=0;
        for(int i=0; i<=furthest_reach_so_far && i < nums.length; i++ ){
            furthest_reach_so_far = Math.max(furthest_reach_so_far, i+nums[i]);

            if(furthest_reach_so_far >=  nums.length-1){
                return true;
            }
        }

        return furthest_reach_so_far>=nums.length-1;

    }
}
