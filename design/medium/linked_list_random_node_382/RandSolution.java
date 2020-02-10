package design.medium.linked_list_random_node_382;

import java.util.Random;

/**
 382. Linked List Random Node
 https://leetcode.com/problems/linked-list-random-node/

 Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

 Follow up:
 What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

 Example:
 // Init a singly linked list [1,2,3].
 ListNode head = new ListNode(1);
 head.next = new ListNode(2);
 head.next.next = new ListNode(3);
 Solution solution = new Solution(head);

 // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 solution.getRandom();

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(1)
 2. Approach
     2.1 With every next node reduce the probability for selecting this node - from 1 to 1/2, 1/3, 1/4 ... 1/n
     2.2 To get randomized number, use rand.nextInt(N) function, where N is element number + 1.
 */

class Solution {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    ListNode head;
    Random rand = new Random();

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int result = 0;
        ListNode node = head;

        int i = 1;
        while (node != null) {
            if (rand.nextInt(i) == 0) {
                result = node.val;
            }
            node = node.next;
            i++;
        }

        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
