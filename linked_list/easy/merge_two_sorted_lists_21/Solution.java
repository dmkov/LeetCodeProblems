package linked_list.easy.merge_two_sorted_lists_21;

/**
 21. Merge Two Sorted Lists
 https://leetcode.com/problems/merge-two-sorted-lists/

 Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

 Example:
 Input: 1->2->4, 1->3->4
 Output: 1->1->2->3->4->4

 ------------------------

 1. Complexity
    1.1 Time Complexity is O(n+m)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on two pointers iterating both lists and comparing values
    2.2 Implementation
        2.2.1 Check if any of provided lists is null - return the second list as an answer
        2.2.2 Create result ListNode to avoid checking for null later
        2.2.3 Add temp=result ListNode to append new nodes to it (result should point to the first node)
        3.2.4 Iterate till both lists have elements. Append smaller element to the temp.next.
        3.2.5 Append end of the second list at the end. Return result.next after it.
 */

class Solution {
    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode result = new ListNode(0);
        ListNode temp = result;
        while (l1 != null && l2 != null) {
            ListNode activeNode;
            if (l1.val <= l2.val) {
                activeNode = l1;
                l1 = l1.next;
            } else {
                activeNode = l2;
                l2 = l2.next;
            }

            temp.next = activeNode;
            temp = temp.next;
        }
        if (l1 == null) temp.next = l2;
        if (l2 == null) temp.next = l1;

        return result.next;
    }
}
