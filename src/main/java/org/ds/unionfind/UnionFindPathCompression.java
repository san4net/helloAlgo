package org.ds.unionfind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class UnionFindPathCompression {
        private int[] root;
        private int[] rank;

        public UnionFindPathCompression(int size) {
            root = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i]=1;
            }
        }

        //
        public int find(int x) {
            if (root[x] == x){
                return x;
            }
             return root[x] = find(root[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
               if(rank[rootX]>rank[rootY]){
                 // make this
                   root[rootY] = rootX;
               }else if(rank[rootX] < rank[rootY]){
                 root[rootX] = rootY;
               }else {
                 root[rootY] = rootX;
                 rank[rootX] = rank[rootX]+1;
               }

            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public static void main(String[] args) throws Exception {
            UnionFindPathCompression uf = new UnionFindPathCompression(10);
            // 1-2-5-6-7 3-8-9 4
            uf.union(1, 2);
            uf.union(2, 5);
            uf.union(5, 6);
            uf.union(6, 7);
            uf.union(3, 8);
            uf.union(8, 9);
            System.out.println(uf.connected(1, 5)); // true
            System.out.println(uf.connected(5, 7)); // true
            System.out.println(uf.connected(4, 9)); // false
            // 1-2-5-6-7 3-8-9-4
            uf.union(9, 4);
            System.out.println(uf.connected(4, 9)); // true
        }

        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            // nums[i] + nums[j] + nums[k] + nums[l] == 0 given
            // 0<=j,k,l<n
            Map<Integer, Integer> pairSumCount = new HashMap();

            Arrays.stream(nums1).forEach(i->
                     Arrays.stream(nums2).forEach
                             (j-> pairSumCount.compute(i+j,(k,v)->v==null?1:v+1))
                     );

           return Arrays.stream(nums3).map(
                   i-> Arrays.stream(nums4)
                           .map(j-> pairSumCount.getOrDefault(-(i+j),0)).sum()
           ).sum();

        }
    }
