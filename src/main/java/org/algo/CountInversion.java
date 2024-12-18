package org.algo;


import java.util.*;

/**
 * Refer 25.28  in Elements of Programming interviews

 */
public class CountInversion {
    // Way 1. Both same idea
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
                if(left[j]>right[i]){ //if greater than it is inverted
                    temp = temp + (left.length-j);
                    break;
                }
            }
        }
        return leftCount+rightCount+temp;
    }

    static class IntIdx {
        int val;
        int idx;
        IntIdx(int val, int idx){
            this.val= val;
            this.idx =idx;
        }


    }
    public static int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<IntIdx> heap = new PriorityQueue<>((IntIdx a, IntIdx b)-> Integer.compare(a.val, b.val));

        for(int i=0 ; i< nums.length; i++){
            if(heap.size()<k){
                heap.add(new IntIdx(nums[i],i));
            }else{
                if(nums[i] > heap.peek().val){
                    heap.remove();
                    heap.add(new IntIdx(nums[i],i));
                }
            }

        }
        int[] r =  heap.stream().sorted((IntIdx a, IntIdx b)-> Integer.compare(a.idx, b.idx)).mapToInt(c->c.val).toArray();
        for(int x:r){
            System.out.println(x);
        }
        return r;
    }

    public static void main(String[] args) {
    	Map<Integer, Map<Integer,Integer>> map = new HashMap();
    	map.get(1).entrySet();
    	PriorityQueue<Integer[]> aa = new PriorityQueue<Integer[]>(4, (Integer[] a, Integer[] b)->a[1]-b[1]);
    	
    	
        StringBuilder sb = new StringBuilder("");
        sb.append('a');
        int inversion  = countInversion(new int[]{3,6,4,2,5,1});
       maxSubsequence(new int[]{2,1,3,3}, 2);
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
}
