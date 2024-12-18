package org.algo;


import java.util.*;
import java.util.stream.Stream;

/**
 * Refer 25.28  in Elements of Programming interviews

 */
public class CountInversion {
    // Way 1. Both same idea
    public static int countInversion(int[] nums) {
        return countInversion(0, nums.length-1, nums);
    }

    private static int countInversion(int s, int e, int[] nums) {
        if (s == e) return 0;
        var mid = s + (e - s) / 2;
        int leftCount = countInversion(s, mid, nums);
        int rightCount = countInversion(mid + 1, e, nums);

        int[] left = Arrays.copyOfRange(nums, s, mid + 1);
        int[] right = Arrays.copyOfRange(nums, mid + 1, e + 1);
        Arrays.sort(left);
        Arrays.sort(right);
        int temp = 0;
        //for each element in right we find the count inversion
        for (int i = 0; i < right.length; i++) {
            for (int j = 0; j < left.length; j++) {
                if (left[j] > right[i]) { //if greater than it is inverted
                    temp = temp + (left.length - j);
                    break;
                }
            }
        }
        return leftCount + rightCount + temp;
    }

    public static void main(String[] args) {
        int inversion  = countInversion(new int[]{3,6,4,2,5,1});
        System.out.println(inversion);
    }

    /**
     * Way 2: Both same idea
     *
     * @param A
     * @return
     */
    static int  countInversion(List<Integer> A){
        return countInversion(A, 0, A.size());
    }

    // right is exclusive
    private static int countInversion(List<Integer> a, int start, int end) {
        if(end-start<=1)return 0;
        int mid = start + (end-start)/2;
        return countInversion(a, start, mid) +
                countInversion(a, mid, end) +
                mergeSortAndCountInversionAcrossArray(a, start, mid, end);
    }

    private static int mergeSortAndCountInversionAcrossArray(List<Integer> A, int start, int mid, int end) {
        List<Integer> leftA = A.subList(start, mid);
        List<Integer> rightA = A.subList(mid, end);
        int leftStart = start;
        int righStart = mid;
        int countInv = 0;
        List<Integer> sortedA = new ArrayList<>();

        while(leftStart<mid && righStart<end){
            if(A.get(leftStart) > A.get(righStart)){
                // this is inversion case
                countInv = countInv + (mid - leftStart);
                sortedA.add(A.get(righStart++));
            }else{
                sortedA.add(A.get(leftStart++));
            }
        }
        // copy remaining from left start to mid to sorted
        sortedA.addAll(A.subList(leftStart, mid));
        sortedA.addAll(A.subList(righStart, end));

        for(int i: sortedA){
            A.set(start++, i);
        }

        return countInv;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> r = new HashSet();
        Arrays.sort(nums);

        for(int i=0; i<nums.length-2;i++){
            int s=i+1;
            int e=nums.length-1;
            int target = -nums[i];
            
            while(s<e){
                if(nums[s]+nums[e]== target){
                   r.add(List.of(nums[i],nums[s], nums[e]));
                } else if (nums[s]+nums[e]>target) {
                    e--;
                }else{
                    s++;
                }
            }
        }

        return r.stream().toList();
    }
}
