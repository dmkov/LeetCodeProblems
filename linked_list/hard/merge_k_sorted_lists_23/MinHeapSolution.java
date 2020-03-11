package linked_list.hard.merge_k_sorted_lists_23;

import java.util.PriorityQueue;

/**
 23. Merge k Sorted Lists
 https://leetcode.com/problems/merge-k-sorted-lists/

 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

 Example:
 Input:
    [
         1->4->5,
         1->3->4,
         2->6
     ]
 Output: 1->1->2->3->4->4->5->6

 --------

 1. Complexity
    1.1 Time Complexity is O(n * log k), where n is the number of all numbers and k is the number of lists
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Iterate through all lists, store first elements from each list to the heap. While heap is not empty,
        poll item from it and append to the result list.
 3 Implementation
    3.1 Check if given array is not null or empty. Create a temp root node for the result tree.
    3.2 Create a min PriorityQueue, add all root items from lists to the queue.
    3.3 While PriorityQueue is not empty, poll the item, append it to the result and add next item to the queue if it is not empty.
 */

class MinHeapSolution {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(0);
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }

        ListNode node = result;
        while(pq.size() > 0) {
            node.next = pq.poll();
            node = node.next;

            if (node.next != null) {
                pq.add(node.next);
            }
            node.next = null;
        }

        return result.next;
    }
}
