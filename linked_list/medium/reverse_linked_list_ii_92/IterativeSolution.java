package linked_list.medium.reverse_linked_list_ii_92;

/**
 92. Reverse Linked List II
 https://leetcode.com/problems/reverse-linked-list-ii/

 Reverse a linked list from position m to n. Do it in one-pass.
 Note: 1 ≤ m ≤ n ≤ length of list.

 Example:
 Input: 1->2->3->4->5->NULL, m = 2, n = 4
 Output: 1->4->3->2->5->NULL

 ------------------------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is to split all process into 2 phases: 1) add start point for reversing and 2) reverse the
        list using prev, cur and next pointers
 */

class IterativeSolution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || n-m <= 0) {
            return head;
        }

        ListNode prev = null;
        ListNode cur = head;
        ListNode next = head.next;

        int cnt = 1;
        while (cnt < m && next != null) {
            prev = cur;
            cur = cur.next;
            next = next.next;
            cnt++;
        }

        ListNode rev_prev = prev; // null
        ListNode pre = cur; // 3
        prev = null;

        while (cnt < n && next != null) {
            ListNode node = next.next;
            next.next = cur;
            cur.next = prev;

            prev = cur;
            cur = next;
            next = node;
            cnt++;
        }

        if (rev_prev != null) {
            rev_prev.next = cur;
        } else {
            head = cur;
        }

        pre.next = next;

        return head;
    }
}

/**
 1. Is it single or double-linked linked list?
 2. The m is always smaller or equal to the number of elements?
 3. What should be returned if input values not valid?
 4. What are values in the linked list?
 5. The structure? is it value and next node?
 6. Input data? root and n,m integers?
 7. Start position - 1 or 0?
 8. If m ==n - no changes?

     1->2 , 1,1 -> 1->2
     1->2->3->4->5->6 , 2,4 -- 1->4->3->2->5->6


 iterate until m, track prev, cur and next:
     at m: get counter (n-m), store prev to rev_pre and cur rev to  and prev = null
     at (n-m):   node = next.next
                 next.next = cur
                 cur.next = prev

                 prev = cur
                 cur = next
                 next = node
 */

