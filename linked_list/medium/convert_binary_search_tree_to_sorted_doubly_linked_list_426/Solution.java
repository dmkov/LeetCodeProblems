package linked_list.medium.convert_binary_search_tree_to_sorted_doubly_linked_list_426;

import java.util.HashMap;
import java.util.Map;

/**
 426. Convert Binary Search Tree to Sorted Doubly Linked List
 https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

 Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

 You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

 We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.

 Example 1:
 Input: root = [4,2,5,1,3]
 Output: [1,2,3,4,5]

 Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

 Example 2:
 Input: root = [2,1,3]
 Output: [1,2,3]

 Example 3:
 Input: root = []
 Output: []
 Explanation: Input is an empty tree. Output is also an empty Linked List.

 Example 4:
 Input: root = [1]
 Output: [1]

 Constraints:
 -1000 <= Node.val <= 1000
 Node.left.val < Node.val < Node.right.val
 All values of Node.val are unique.
 0 <= Number of Nodes <= 2000

 ------------------------

 1. Complexity
    1.1 Time Complexity is O(n) - where n is the number of elements
    1.2 Space Complexity is O(1) or O(logn) if we count function stack
 2. Approach
    2.1 The idea of the approach is to do a inorder traverse for all elements and update link for the previous item.
 2.2 Implementation
    2.2.1 Check if given node is valid.
    2.2.2 Create a result node to hold pointer for the method response.
    2.2.3 Call inorder traverse for the root node. Inside traverse check if left node exists and update link with the previous item
        for it. Then update link to previous item (and left link for the previous element itself) for the current node. Then
        check right subnode.
    2.2.4 At the end update links for the last and first nodes.
 */

class Solution {
    public class Node {
        int val;
        Node left;
        Node right;
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node result = new Node();
        Node last = inorder(root, result);

        last.right = result.right;
        result.right.left = last;

        return last.right;
    }

    private Node inorder(Node node, Node prev) {
        if (node.left != null) {
            prev = inorder(node.left, prev);
        }
        node.left = prev;
        prev.right = node;
        if (node.right != null) {
            node = inorder(node.right, node);
        }

        return node;
    }
}
