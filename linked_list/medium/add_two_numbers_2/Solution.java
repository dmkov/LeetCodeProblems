package linked_list.medium.add_two_numbers_2;

/**
 2. Add Two Numbers
 https://leetcode.com/problems/add-two-numbers/

 You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example:
 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.

 --------

 1. Complexity
    1.1 Time Complexity is O(n) where n - max(l1 or l2)
    1.2 Space Complexity is O(n) because of adding a new node for every number
 2. Approach
    2.1 Go from node to node, sum the result and take into account carry from the previous operation.
 2.2 Implementation
    2.2.1 Check if given ListNode are not null. If any of them is null, return empty result.
    2.2.2 Create a new root ListNode and assign temp next ListNode to it. Set carry to initial '0'
    2.2.3 Iterate while both l1 and l2 are not empty. Create a local sum of not-null l1 and l2 values,
        add carry from the previous operation. Check if sum overflows 9, then increase the carry and set sum to sum % 10.
        Create next.next object with the sum value.
    2.2.4 Do not forget about the last carry after the loop. If it is not zero, create another ListNode with its value.
 */

class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        if (l1 == null || l2 == null) {
            return root;
        }

        ListNode next = root;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;

            carry = sum / 10;
            sum = sum % 10;

            next.next = new ListNode(sum);
            next = next.next;
        }

        if (carry > 0) {
            next.next = new ListNode(carry);
        }

        return root.next;
    }
}
