package tree.medium.validate_binary_search_tree_98;

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
     3.1 Define min and max limits (Integer.MIN_VALUE - 1) and Integer.MAX_VALUE + 1 as long variables
     3.2 For every node call validate() method recursively with max / min restriction:
         3.2.1 The node value should be between min and max
         3.2.2 The next left node should have the current value as the max limit (and previous min)
         3.2.3 The next right node should have the current value as the min limit (and previous max)
         3.2.4 Return true to the top, if any of conditions did not work
 */

class RecursiveSolution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isValidBST(TreeNode root) {
        long min = (long) Integer.MIN_VALUE - 1;
        long max = (long) Integer.MAX_VALUE + 1;

        return validate(root, min, max);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        long val = node.val;

        return (val > min && val < max
                && validate(node.left, min, val)
                && validate(node.right, val, max));
    }
}
