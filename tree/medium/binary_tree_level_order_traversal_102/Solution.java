package tree.medium.binary_tree_level_order_traversal_102;

import apple.laf.JRSUIUtils.Tree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 102. Binary Tree Level Order Traversal
 https://leetcode.com/problems/binary-tree-level-order-traversal/

 Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
      15   7
 return its level order traversal as:
 [
     [3],
     [9,20],
     [15,7]
 ]

 --------

 1. Complexity
    1.1 Time Complexity is O(n).
    1.2 Space Complexity is O(n) because of queue and linkedlist
 2. Approach
    2.1 We can use queue to store tree nodes from one level, then remove them on the next iteration, store result
        to list and check child nodes to put in queue again
    2.2 Implementation
        2.2.1 First, check if given root node is not null. If it is not, put it to the queue and start iterating.
        2.2.2 For current elements in the queue (determine level by temp variable queue.size()) put values to the list
            and add child nodes to the same queue again.
        2.2.3 Use while(!queue.isEmpty()) for the main iteration and queue.size() to get the active level.
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        while(!queue.isEmpty()) {
            result.add(new ArrayList<Integer>());

            int levelLength = queue.size();
            for (int i = 0; i < levelLength; i++) {
                TreeNode node = queue.remove();

                result.get(level).add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            level++;
        }

        return result;
    }
}
