package tree.medium.binary_tree_inorder_traversal_94;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 94. Binary Tree Inorder Traversal
 https://leetcode.com/problems/binary-tree-inorder-traversal/

 Given a binary tree, return the inorder traversal of its nodes' values.
 Example:

 Input: [1,null,2,3]
    1
     \
      2
     /
    3

 Output: [1,3,2]
 Follow up: Recursive solution is trivial, could you do it iteratively?

 --------

 1. Complexity
    1.1 Time Complexity is O(n).
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Starting from root, use stack to iterate through all nodes.
    2.2 Implementation
        2.2.1 First, check if given node is not null, return empty list if it is. Add first node to the stack.
        2.2.2 Iterate while stack is not empty. For every element do an internal loop to push left item to the stack.
        2.2.3 If left item is empty - pop() from stack and add item to the result list. Take right node if it is
            not null or continue poping items from stack.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> inorderTraversal(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }

        Stack<TreeNode> s = new Stack<>();
        s.push(node);
        while (node != null || s.size() > 0) {
            while (node != null) {
                s.push(node);
                node = node.left;
            }
            node = s.pop();
            list.add(node.val);
            node = node.right;
        }

        return list;
    }
}
