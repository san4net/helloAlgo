package org.algo;

import java.util.Arrays;
/**
 * Refer 25.28  in Elements of Programming interviews

 */
public class CountInversion {
    public static int countInversion(int[] nums) {
        return countInversion(0, nums.length-1, nums);
    }

    private static int countInversion(int s, int e, int[]nums){
        if(s==e) return 0;
        int mid = s+(e-s)/2;
        int leftCount = countInversion(s, mid, nums);
        int rightCount = countInversion(mid+1, e, nums);

        int[] left = Arrays.copyOfRange(nums,s, mid+1 );
        int[] right = Arrays.copyOfRange(nums,mid+1,e+1);
        Arrays.sort(left);
        Arrays.sort(right);
        int temp=0;
        //for each element in right we find the count inversion
        for(int i=0; i<right.length; i++){
            for(int j=0; j<left.length; j++){
                if(left[j]>right[i]){//if greater then it is inverted
                    temp = temp + (left.length-j);
                    break;
                }
            }
        }
        return leftCount+rightCount+temp;
    }

    public static void main(String[] args) {
        int inversion  = countInversion(new int[]{3,6,4,2,5,1});
        System.out.println(inversion);
    }
}
