package linked_list.medium.copy_list_with_random_pointer_138;

/**
 138. Copy List with Random Pointer
 https://leetcode.com/problems/copy-list-with-random-pointer/

 A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 Return a deep copy of the list.

 The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 val: an integer representing Node.val
 random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.

 Example 1:
 Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

 Example 2:
 Input: head = [[1,1],[2,1]]
 Output: [[1,1],[2,1]]

 Example 3:
 Input: head = [[3,null],[3,0],[3,null]]
 Output: [[3,null],[3,0],[3,null]]

 Example 4:
 Input: head = []
 Output: []

 Explanation: Given linked list is empty (null pointer), so return null.
 Constraints:
 -10000 <= Node.val <= 10000
 Node.random is null or pointing to a node in the linked list.
 Number of Nodes will not exceed 1000.

 ------------------------

 1. Complexity
    1.1 Time Complexity is O(n) - where n is the number of elements
    1.2 Space Complexity is O(n) because of hash map
 2. Approach
    2.1 The idea is to create copy of every node and place them in between the current node to do not create a temporary storage.
 2.2 Implementation
    2.2.1 Check if given list is valid.
    2.2.2 Iterate all elements by next pointers and create a new element with the same value. Put it as `next` of the current
        and assign next of the current to its next pointer.
    2.2.3 Iterate one more time and update random pointers. The random of the next element will be the next of the current random.
    2.2.4 Split list into two different and restore previous next elements. Return only created ones.
 */

class IntermediatePointersSolution {
    public class Node {
        int val;
        Node next;
        Node random;
        Node(int x) { val = x; }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node node = head;
        while (node != null) {
            Node copied = new Node(node.val);
            copied.next = node.next;
            node.next = copied;
            node = node.next.next;
        }

        node = head;
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }

        Node result = head.next;
        Node prev = head;
        Node cur = head.next;
        while (prev != null && cur != null) {
            prev.next = prev.next.next;
            if (cur.next != null) {
                cur.next = cur.next.next;
            }
            prev = prev.next;
            cur = cur.next;
        }

        return result;
    }
}
