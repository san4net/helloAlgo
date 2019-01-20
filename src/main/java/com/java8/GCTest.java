package com.java8;

import java.util.HashSet;

public class GCTest {

    protected static void createFewLongLivedAndManyShortLivedObjects() {
        HashSet<Double> set = new HashSet<Double>();

        long l = 0;
        for (int i=0; i < 100; i++) {
            Double longLivedDouble = new Double(l++);
            set.add(longLivedDouble);  // add to Set so the objects continue living outside the scope
        }

        while(true) { // Keep creating short-lived objects. Extreme but illustrates the point
            Double shortLivedDouble = new Double(l++);
        }
    }

    /**
     * -Xmx100m                     // Allow JVM 100 MB of heap memory
     * XX:+PrintGCDetails -Xloggc:/Users/santoshkumar/projects/helloAlgo/
     * -XX:-PrintGC                 // Enable GC Logs
     * -XX:+PrintHeapAtGC           // Enable GC logs
     * -XX:MaxTenuringThreshold=15  // Allow objects to live in the young space longer
     * -XX:+UseConcMarkSweepGC      // Ignore for now; covered later
     * -XX:+UseParNewGC             // Ignore for now; covered later
     *
     * @param args
     */
    public static void main(String[] args) {
    createFewLongLivedAndManyShortLivedObjects();
    }
}
