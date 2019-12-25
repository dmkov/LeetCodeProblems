package tree.medium.populating_next_right_pointers_in_each_node_116;

/**
 116. Populating Next Right Pointers in Each Node
 https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

 You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 struct Node {
     int val;
     Node *left;
     Node *right;
     Node *next;
 }

 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

 Initially, all next pointers are set to NULL.

 Follow up:
 You may only use constant extra space.
 Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.

 Example 1:
 Input: root = [1,2,3,4,5,6,7]
 Output: [1,#,2,3,#,4,5,6,7,#]
 Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(1)
 2. Approach
     2.1 The idea of the solution is going level by level and assign to every 1) left node - right pair and 2) for the
        right - it can be null or left node from parent next element.
     2.2 Implementation
        2.2.1 We need two pointers - root for level and cur - for active element.
        2.2.2 Start first loop until root is null or it does not have left element (one level).
        2.2.3 Assign root to cur pointer and start another loop for the current level.
        2.2.4 For every cur element assign right to left.next and right.next to null if there is no next for the cur
            (meaning it is already the last element in the level) or cur.next.left otherwise.
        2.2.5 Assign root = root.left (first element) and continue loop to another level.
 */

class Solution {
    public class Node {
        int val;
        Node left;
        Node right;
        Node next;
    }

    public void connect(Node root) {
        while(root != null && root.left != null) {
            Node cur = root;
            while(cur != null) {
                cur.left.next = cur.right;
                cur.right.next = cur.next == null ? null : cur.next.left;
                cur = cur.next;
            }
            root = root.left;
        }
    }

//    public Node connect(Node root) {
//        if (root == null) {
//            return root;
//        }
//        Node check = null;
//        while (check != root) {
//            check = connect(root, null);
//        }
//        return root;
//    }
//
//    private Node connect(Node node, Node next) {
//        Node child = null;
//        if (node.left != null && node.left.next == null) {
//            child = connect(node.right, next);
//            child = connect(node.left, child);
//        } else {
//            node.next = next;
//            child = node;
//        }
//        return child;
//    }
}
