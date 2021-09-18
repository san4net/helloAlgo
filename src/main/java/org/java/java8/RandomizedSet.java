package org.java.java8;

import com.google.common.collect.Lists;

import java.util.*;

public class RandomizedSet {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Integer::compare);
       int[][]arr =  {{1,3},{2,6},{8,10},{15,18}};
        merge(arr);
    }


    public static int[][] merge(int[][] intervals) {
       List<List<Integer>> ll  =  Arrays.stream(intervals)
                .sorted((int[] a, int[] b)-> Integer.compare(a[0],b[0]))
               .collect(ArrayList::new, (List<List<Integer>> l, int[]b)->{
                   l.add(Lists.newArrayList(b[0],b[1]));
                       }
               , List::addAll);


       List<List<Integer>> list = new ArrayList<>();

       list.add(Lists.newArrayList(ll.get(0).get(0), ll.get(0).get(1)));

       for(int a = 1,i=0; a<ll.size(); a++){
           if(ll.get(a-1).get(1) >= ll.get(a).get(0)){
               if(ll.get(a-1).get(1) < ll.get(a).get(1))
                 list.get(i).set(1, ll.get(a).get(1));

           }else{
             list.add(Lists.newArrayList(ll.get(a).get(0), ll.get(a).get(1)));
             i++;
           }
       }

      int[][] r = new int[list.size()][];
       int i=0;

       for (List<Integer> nestedList : list) {
            r[i++] = nestedList.stream().mapToInt(Integer::intValue).toArray();
        }

       return r;

    }


}