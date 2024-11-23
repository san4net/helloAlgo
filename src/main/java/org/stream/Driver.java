package org.stream;

import java.util.*;

public class Driver {

    static Map<String, Integer> m = new HashMap<>();

    public static List<Integer> matchingStrings(List<String> stringList, List<String> queries) {
        // Write your code here

        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(1);
        q.removeFirst();

        while (q.isEmpty()){
             while(q.isEmpty()){
                 q.removeFirst();
             }
        }


        for(String s: stringList){
           if( m.get(s) != null){
               m.put(s, m.get(s)+1);
           }else {
               m.put(s, 1);
           }
        }
        List<Integer> r = new ArrayList<>();
        for(String qq: queries){
            r.add(m.get(qq));
        }
        return r;


    }

    public static void main(String[] args) {
//        System.out.println("trying eager worker with true and true");
//        Workers.fullWork(true, true);
//        System.out.println("trying eager worker with true and false");
//        Workers.fullWork(true, false);
        System.out.println("trying lazy worker with true and true");
        Workers.fullWorkLazy(Workers.trueSupplier, Workers.trueSupplier);
        System.out.println("trying lazy worker with false and false");
        Workers.fullWorkLazy(Workers.falseSupplier, Workers.trueSupplier);


    }
}
