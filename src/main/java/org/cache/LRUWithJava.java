package org.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUWithJava {
     LinkedHashMap<Integer, Integer> lhm;

     public LRUWithJava(int capacity) {
             lhm = new LinkedHashMap(capacity, 0.75f, true){

                 @Override
                 protected boolean removeEldestEntry(Map.Entry eldest) {
                     return size() > capacity;
                 }
             };
        }

        public int get(int key) {
            return lhm.get(key);
        }

        public void put(int key, int value) {
            lhm.put(key,value);
        }
}
