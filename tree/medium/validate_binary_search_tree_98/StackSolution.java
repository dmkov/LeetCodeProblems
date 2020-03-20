package tree.medium.validate_binary_search_tree_98;

import java.util.Stack;

/**
 98. Validate Binary Search Tree
 https://leetcode.com/problems/validate-binary-search-tree/

 Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.

 Example 1:

    2
   / \
  1   3

 Input: [2,1,3]
 Output: true

 Example 2:

     5
    / \
   1   4
      / \
     3   6

 Input: [5,1,4,null,null,3,6]
 Output: false
 Explanation: The root node's value is 5 but its right child's value is 4.

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is to use stack and do an inorder traversal where every next element should be bigger than the previous one.
 3 Implementation
     3.1 Check if root node is not null, otherwise return true.
     3.2 Create stack and empty prev node.
     3.3 While stack and root element is not empty do the following:
         3.3.1 While is not null, push it to the stack and assign left node to the root pointer (move to the very left node).
         3.3.2 Pop item from stack to the root pointer (the very left node)
         3.3.3 If prev is not empty, check that it is less than the current node. Otherwise return false
         3.3.4 Assign current element to the prev pointer, assign right node to the root.
     3.4 Return true if all tree is traversed successfully.
 */

class StackSolution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (stack.size() > 0 || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (prev != null && prev.val >= root.val) {
                return false;
            }
            prev = root;
            root = root.right;
        }

        return true;
    }
}
