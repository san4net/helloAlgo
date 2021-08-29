package com.ds.qs;


import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

class HitCounter {
    static class Pair{
        int left, right;
        Pair(int left, int right){
            this.left = left;
            this.right = right;
        }
    }
    // time will always be in increasing order
    private  final Deque<Pair> queue;
    private int count;
    /** Initialize your data structure here.
     * @param queue*/
    public HitCounter() {
        this.queue = new LinkedBlockingDeque<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        // time is always in increasing order
        if(queue.getFirst().left != timestamp){
            queue.addFirst(new Pair(timestamp,1));
        }else{
            queue.getFirst().right = queue.getFirst().right+1;
        }

    }


    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!this.queue.isEmpty()){
            //
            int timeDiff = timestamp - queue.getLast().left;
            if(timeDiff>=300){
                count = count - queue.getLast().right;
                queue.removeLast();
            }else{
                break;
            }

        }
        return count;
    }


}