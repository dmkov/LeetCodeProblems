package linked_list.easy.palindrome_linked_list_234;

/**
 234. Palindrome Linked List
 https://leetcode.com/problems/palindrome-linked-list/

 Given a singly linked list, determine if it is a palindrome.

 Example 1:
 Input: 1->2
 Output: false

 Example 2:
 Input: 1->2->2->1
 Output: true

 Follow up:
 Could you do it in O(n) time and O(1) space?

 ------------------------

 1. Complexity
    1.1 Time Complexity is O(n) - where n is the number of elements in the list
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on iterating the list with two pointers. The task can be split into 3 subtasks:
        find the middle of the list, reverse second part and compare first and second parts element by element
        additionally restoring the list.
 */

class Solution {
    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        ListNode fast = head;
        ListNode slow = head;
        ListNode reversed = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;

            ListNode temp = slow.next;
            slow.next = reversed;
            reversed = slow;

            slow = temp;
        }

        ListNode restored = slow;
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (slow.val != reversed.val) {
                return false;
            }

            slow = slow.next;

            ListNode temp = reversed.next;
            reversed.next = restored;
            restored = reversed;
            reversed = temp;
        }

        return true;
    }
}
