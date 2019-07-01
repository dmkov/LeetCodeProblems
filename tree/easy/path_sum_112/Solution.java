package tree.easy.path_sum_112;

import java.util.HashMap;
import java.util.Map;

/**
 112. Path Sum
 https://leetcode.com/problems/path-sum/

 Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

 Note: A leaf is a node with no children.

 Example:
 Given the below binary tree and sum = 22,

        5
       / \
      4   8
     /   / \
    11  13  4
   /  \      \
  7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.


 ---


 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on the idea of recursive passing through all nodes and passing remaining value of sum deducted by previous values.
    2.2 Implementation
        2.2.1 Deduct current amount from passed value
        2.2.2 Check if there are more leaves for the current node.
            If yes - recursively call current method for left and right leaves.
            If not - check if remaining amount is '0'. In this case, return true. Otherwise, false.
 */

class Solution {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        sum -= root.val;
        if (root.left == null && root.right == null) {
            return sum == 0;
        } else {
            return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
        }
    }
}
