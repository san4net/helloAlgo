package org.ds.unionfind;

import java.util.Arrays;

class UnionFind {
        // this to store root for every index i
        private int[] root;

        public UnionFind(int size) {
            root = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
            }
        }

        public int find(int x) {
            while (root[x] != x){
                x = root[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                root[rootY] = rootX;
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public static void main(String[] args) throws Exception {
            String s=null;
            int[] set = new int[2];
           char b  =  (char) Arrays.stream(set).filter(a -> a == 1).findFirst().getAsInt();

            UnionFind uf = new UnionFind(10);
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
    }


