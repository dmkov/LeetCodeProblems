package linked_list.easy.remove_linked_list_elements_203;

/**
 203. Remove Linked List Elements
 https://leetcode.com/problems/remove-linked-list-elements/

 Remove all elements from a linked list of integers that have value val.

 Example:
 Input:  1->2->6->3->4->5->6, val = 6
 Output: 1->2->3->4->5

 ------------------------

 1. Complexity
    1.1 Time Complexity is O(n) - where n is the number of elements in the list
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on iterating the list and checking elements value
    2.2 Implementation
        2.2.1 Set previous=null and pointer for current elements
        2.2.2 Iterate the list (while pointer != null), check if the value equals given number
        2.2.3 If yes, set next for previous to the next of current item (if previous is null, update head variable)
        2.2.4 If no, just update previous element to the previous.next or pointer depending on values
        2.2.5 At the end set pointer to the next element
 */

class Solution {
    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static ListNode removeElements(ListNode head, int val) {
        ListNode prev = null;
        ListNode pointer = head;
        while (pointer != null) {
            ListNode next = pointer.next;
            if (pointer.val == val) {
                if (prev != null) {
                    prev.next = next;
                } else {
                    head = next;
                }
            } else {
                if (prev != null) {
                    prev = prev.next;
                } else {
                    prev = pointer;
                }
            }

            pointer = next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode f = new ListNode(1);
        ListNode s = new ListNode(2);
        f.next = s;
        ListNode t = new ListNode(6);
        s.next = t;
        ListNode d = new ListNode(3);
        t.next = d;
        ListNode e = new ListNode(6);
        d.next = e;
        Solution.removeElements(f, 6);
    }
}
