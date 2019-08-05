package tree.easy.diameter_of_binary_tree_543;

import java.util.LinkedList;
import java.util.List;

/**
 543. Diameter of Binary Tree
 https://leetcode.com/problems/diameter-of-binary-tree/

 Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

 Example:
 Given a binary tree
        1
       / \
      2   3
     / \
    4   5
 Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

 Note: The length of path between two nodes is represented by the number of edges between them.

 ---


 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on the idea DFS and checking max child depths for every node.
        Diameter is actually max(node.leftDepth + node.rightDepth).
    2.2 Implementation
        2.2.1 Recursively get depth for every children in the tree (left and right)
        2.2.2 Diameter will be sum of left and right depths. Compare it with the max stored value in property
        2.3.2 Return max of left and right incremented by 1 for every child to the upper level.
 */

class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        getMaxDepth(root);

        return maxDiameter;
    }

    private int getMaxDepth(TreeNode root) {
        int left = 0;
        int right = 0;

        if (root.left != null) {
            left = getMaxDepth(root.left);
        }
        if (root.right != null) {
            right = getMaxDepth(root.right);
        }
        maxDiameter = Math.max(left + right, maxDiameter);

        return Math.max(left, right) + 1;
    }
}
