package tree.easy.maximum_depth_of_binary_tree_104;

import java.util.LinkedList;
import java.util.List;

/**
 104. Maximum Depth of Binary Tree

 Given a binary tree, find its maximum depth.
 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 Note: A leaf is a node with no children.

 Example:

 Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7

 return its depth = 3.

 ---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on the idea recursive traverse of the tree (DFS)
    2.2 Implementation
        2.2.1 Check node for null and return 0 if it is the case
        2.2.2 If it is the leaf (left and right nodes are null) - return 1
        2.2.3 Otherwise call same function for left and right nodes and return result + 1;
 */

class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int maxDepth(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null)
            return 1;
        else
            return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }
}
