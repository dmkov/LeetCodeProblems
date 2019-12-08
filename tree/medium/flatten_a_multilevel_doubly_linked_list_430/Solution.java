package tree.medium.flatten_a_multilevel_doubly_linked_list_430;

import java.util.Stack;

/**
 430. Flatten a Multilevel Doubly Linked List
 https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/

 You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer,
 which may or may not point to a separate doubly linked list.
 These child lists may have one or more children of their own, and so on, to produce a multilevel data structure,
 as shown in the example below.

 Flatten the list so that all the nodes appear in a single-level, doubly linked list.
 You are given the head of the first level of the list.

 Example:
 Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

 Output:
 1-2-3-7-8-11-12-9-10-4-5-6-NULL

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
     2.1 There are recursive DFS and iterative stack solutions. The second one is more elegant
     2.2 Implementation
        2.2.1 Create a preudo `prev` element and assign current root to its next item. Push root to the stack.
        2.2.2 While stack is not empty, pop an element from it. Add to stack its next and child items
            if any of them are not null. Remove child pointers after pushing to the stack.
        2.2.3 Add current element to the end of global `prev` variable (or pseudo created item if this is a first round),
            move to the next element in the stack
        2.2.4 At the end unlink pseudo element and return its next (previously root item).
 */
class Solution {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    public Node flatten(Node head) {
        if (head == null) return head;

        Node pseudoHead = new Node(0, null, head, null);
        Node curr, prev = pseudoHead;

        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            curr = stack.pop();
            prev.next = curr;
            curr.prev = prev;

            if (curr.next != null) stack.push(curr.next);
            if (curr.child != null) {
                stack.push(curr.child);
                // don't forget to remove all child pointers.
                curr.child = null;
            }
            prev = curr;
        }

        // detach the pseudo node from the result
        pseudoHead.next.prev = null;
        return pseudoHead.next;
    }

//    public Node flatten(Node head) {
//        if (head == null) {
//            return null;
//        }
//        flatternChildren(head);
//
//        return head;
//    }
//
//    private Node flatternChildren(Node node) {
//        Node end = node;
//        while (node != null) {
//            if (node.child != null) {
//                Node next = node.next; // 4
//                link(node, node.child);
//                node.child = null;
//
//                end = flatternChildren(node.next);
//                link(end, next);
//                node = next;
//            } else {
//                end = node;
//                node = node.next;
//            }
//        }
//        return end;
//    }
//
//    private void link(Node parent, Node child) {
//        parent.next = child;
//        if (child != null) {
//            child.prev = parent;
//        }
//    }
}
