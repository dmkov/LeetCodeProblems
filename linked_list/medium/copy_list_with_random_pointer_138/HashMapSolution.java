package linked_list.medium.copy_list_with_random_pointer_138;

import java.util.HashMap;
import java.util.Map;

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
    2.1 The idea of the approach is in using hash map to store all created nodes against original ones.
 2.2 Implementation
    2.2.1 Check if given list is valid.
    2.2.2 Create a HashMap to store elements. Add new pointers for the result and given list nodes.
    2.2.3 While the given list node is not empty, try to get linked value from map. If it does not exist - create and put
        to the map.
    2.2.4 Check random element for the original node. If it is not null, get object from map. Again, if it does not exist
        create a new one and put it to the map.
    2.2.5 Update links to the random and next elements in the copy. Iterate further.
 */

class HashMapSolution {
    public class Node {
        int val;
        Node next;
        Node random;
        Node(int x) { val = x; }
    }

    public Node copyRandomList(Node head) {
        Node result = new Node(0);
        if (head == null) {
            return result.next;
        }
        Map<Node, Node> map = new HashMap<>();

        Node copied = result;
        Node node = head;
        while (node != null) {
            Node n = map.get(node);
            if (n == null) {
                n = new Node(node.val);
                map.put(node, n);
            }
            if (node.random != null) {
                Node rand = map.get(node.random);
                if (rand == null) {
                    rand = new Node(node.random.val);
                    map.put(node.random, rand);
                }
                n.random = rand;
            }
            copied.next = n;
            copied = copied.next;
            node = node.next;
        }

        return result.next;
    }
}
