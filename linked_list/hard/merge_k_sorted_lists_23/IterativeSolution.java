package linked_list.hard.merge_k_sorted_lists_23;

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
    1.1 Time Complexity is O(n * m), where n is a total number of elements and m is the number of lists
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Iterate through all lists, get the min element per iteration and join it to the result. Repeat until
        there are no items left.
 3 Implementation
    3.1 Check if given array is not null or empty. Create a temp root node for the result tree.
    3.2 Add boolean flag displaying that item was updated in the loop. Iterate while this flag is true:
        3.2.1 Set flag to false, min number to Integer.MAX_VALUE and indx to -1
        3.2.2 Iterate through all lists, get the min element and update min/indx values
        3.2.3 Append selected item to the result list.
    3.3 Return result.next as the first appended element.
 */

class IterativeSolution {

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

        ListNode node = result;
        boolean check = true;
        while(check) {
            check = false;
            int min = Integer.MAX_VALUE;
            int indx = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    indx = i;
                    min = lists[i].val;
                }
            }
            if (indx != -1) {
                node.next = lists[indx];
                lists[indx] = lists[indx].next;

                node = node.next;
                node.next = null;
                check = true;
            }
        }

        return result.next;
    }
}
