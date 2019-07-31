package tree.easy.invert_binary_tree_226;

import java.util.LinkedList;
import java.util.Queue;

/**
 226. Invert Binary Tree
 https://leetcode.com/problems/invert-binary-tree/

 Invert a binary tree.

 Example:

 Input:

      4
    /   \
   2     7
  / \   / \
 1   3 6   9

 Output:

      4
    /   \
   7     2
  / \   / \
 9   6 3   1

 ---


 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n) in the worst case
 2. Approach
    2.1 This solution is based on the idea of BSF of the tree
    2.2 Implementation
        2.2.1 Put current element to the queue
        2.2.2 Until queue contains elements, flip child for every item and add next not-null elements there
 */

class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> list = new LinkedList<>();
        if (root != null) list.add(root);

        while (!list.isEmpty()) {
            TreeNode node = list.poll();
            flipNodes(node);
            if (node.left != null) list.add(node.left);
            if (node.right != null) list.add(node.right);
        }
        return root;
    }

    private void flipNodes(TreeNode node) {
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }
}
