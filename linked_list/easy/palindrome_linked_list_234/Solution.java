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
        find the middle of the list, reverse second part and compare first and second parts element by element.
    2.2 Implementation
        2.2.1 Check if head is null or if it consists of one element - return true
        2.2.2 Define two pointer slow/fast, iterate slow by 1 and fast by 2 items per turn.
            When fast is out of the list, slow is in the middle
        2.2.3 Reverse all elements from slow to the end. Use two temporary variables - previous and next
        2.2.4 Assign fast back to the head and compare item by item fast and slow lists.
            If items do not equal - return false, otherwise - true at the end of the method.
 */

class Solution {
    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode prev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        slow = prev;
        fast = head;
        while (slow != null && fast != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }
}
