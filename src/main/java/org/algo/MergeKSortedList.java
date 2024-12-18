package org.algo;

import org.ds.ListNode;

import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * Example 1.
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * <p>
 * Example 2.
 * Input: lists = []
 * Output: []
 */
public class MergeKSortedList {
    /**
     * Pseudo
     * - we can traverse and pick smallest from linked list
     * - we will advance list and keep choosing smallest
     * till we have exhausted all
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0, null);
        ListNode temp = dummy;
        boolean done = false;

        while (!done) {
            done = true;
            ListNode smallest = null;
            int idx = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    continue;
                }
                done = false;
                if (smallest == null) {
                    smallest = lists[i];
                    idx = i;
                } else {
                    if (smallest.val > lists[i].val) {
                        smallest = lists[i];
                        idx = i;
                    }
                }
            }

            if (!done) {
                temp.next = new ListNode(smallest.val, null);
                temp = temp.next;
                lists[idx] = smallest.next;
            }

        }

        return dummy.next;
    }

    /**
     * @param lists
     * @return
     */
    public ListNode mergeKListsWithHeap(ListNode[] lists) {
        PriorityQueue<ListNode> heap =
                new PriorityQueue<>((ListNode a, ListNode b) ->
                        Integer.compare(a.val, b.val));

        for (ListNode listNode : lists) {
            if (listNode != null) {
                heap.add(listNode);
            }
        }

        ListNode dummy = new ListNode(0, null);

        ListNode temp = dummy;

        while (!heap.isEmpty()) {
            ListNode min = heap.remove();
            temp.next = new ListNode(min.val, null);
            temp = temp.next;

            min = min.next;
            if (min != null) {
                heap.add(min);
            }

        }

        return dummy.next;
    }
}
