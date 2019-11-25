package tree.easy.balanced_binary_tree_110;

/**
 110. Balanced Binary Tree
 https://leetcode.com/problems/balanced-binary-tree/

 Given a binary tree, determine if it is height-balanced.

 For this problem, a height-balanced binary tree is defined as:
    - a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

 Example 1:
 Given the following tree [3,9,20,null,null,15,7]:

      3
     / \
    9  20
  /  \
 15   7
 Return true.

 Example 2:
 Given the following tree [1,2,2,3,3,null,null,4,4]:

         1
        / \
       2   2
      / \
     3   3
   / \
 4   4
 Return false.

 ---


 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on the idea recursive traverse of the tree (DSF)
    2.2 Implementation
        2.2.1 Check if there are children nodes, otherwise return true
        2.2.2 For each node check if the max depth of the left and right subtree. If diff is more than 1 - return -1
            as flag for a not balanced tree, otherwise - return max of both left and right depths
        2.2.3 At the end, if left or right depth equals -1 it means that tree is not balanced inside. If both values
            are positive and no greater than 1 - the tree is balanced.
 */

class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return false;
        }
        if (maxDepth(root) > 0) {
            return true;
        } else {
            return false;
        }
    }

    private int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int left = maxDepth(node.left); // -1
        int right = maxDepth(node.right); // 2
        if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }
}
