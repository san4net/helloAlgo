package org.algo;

import com.google.common.collect.Lists;

import java.util.*;

public class ClosestEntryIn3SortedArray {

     static class ArrayData implements Comparable<ArrayData> {
        public int val;
        public int idx;

        public ArrayData(int idx, int val) {
            this.val = val;
            this.idx = idx;
        }

            @Override
            public int compareTo(ArrayData o) {
                int result = Integer.compare(val, o.val);
                if (result == 0) {
                    result = Integer.compare(idx, o.idx);
                }
                return result;

            }

            @Override
            public boolean equals (Object obj){
                if (obj == null || !(obj instanceof ArrayData)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                ArrayData that = (ArrayData) obj;
                return this.val == that.val && this.idx == that.idx;
            }
            @Override
            public int hashCode () {
                return Objects.hash(val, idx);
            }


     }

    public static int findMinDistanceSortedArrays(
            List<List<Integer>> sortedArrays) {
        // Indices into each of the arrays.
        // each array indexes
        List<Integer> heads = new ArrayList<>(sortedArrays.size());

        for (List<Integer> arr : sortedArrays) {
            heads.add(0);
        }

        int result = Integer.MAX_VALUE;
        // current head containing in sorted way
        NavigableSet<ArrayData> currentHeads = new TreeSet<>();

        // Adds the minimum element of each array in to currentHeads.
        // this contains array index of sorted array  and head index of each sorted array index
        for (int i = 0; i < sortedArrays.size(); ++i) {
            currentHeads.add(new ArrayData(i, sortedArrays
                                            .get(i)
                                            .get(heads.get(i))));
        }

        while (true) {
            // min between result and (max-min) of current heads

            result = Math.min(result,
                    currentHeads.last().val - currentHeads.first().val);
            //
            int idxNextMin = currentHeads.first().idx;
            // Return if some array has no remaining elements.
            heads.set(idxNextMin, heads.get(idxNextMin) + 1);

            if (heads.get(idxNextMin) >= sortedArrays.get(idxNextMin).size()) {
                return result;
            }

            currentHeads.pollFirst();
            currentHeads.add (new ArrayData(
                    idxNextMin, sortedArrays
                                .get(idxNextMin)
                                .get(heads.get(idxNextMin))));
            }

    }

    public static void main(String[] args) {
      List<List<Integer>> input =    Lists.newArrayList();
        input.add(Lists.newArrayList(15,10,15));
        input.add(Lists.newArrayList(3,6,9,12,15));
        input.add(Lists.newArrayList(8,17,24));
        System.out.println(findMinDistanceSortedArrays(input));
    }
}
