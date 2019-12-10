package tree.medium.binary_tree_zigzag_level_order_traversal_103;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 103. Binary Tree Zigzag Level Order Traversal
 https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

 Given a binary tree, return the zigzag level order traversal of its nodes' values.
 (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
 return its zigzag level order traversal as:
 [
 [3],
 [20,9],
 [15,7]
 ]

 --------

 1. Complexity
    1.1 Time Complexity is O(n).
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to use two stacks so we always start at the previous end position.
    2.2 Implementation
        2.2.1 First, check if given root node is not null, otherwise return an empty list.
        2.2.2 Create two stacks and leftToRight boolean variable. Push first item to the stack.
        2.2.3 Until stack is not empty, process tree level by level. Get size of the current stack and pop items to the
            list. After popping the item, check left and right node (or right and then left if you go from right to left)
            and push them to the second stack.
        2.2.4 At the end of level processing, save the row result and re-assign second stack to first.
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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        boolean leftToRight = true;
        s1.push(root);

        while (s1.size() > 0) {
            List<Integer> row = new ArrayList<>();
            int level = s1.size();

            for (int i = 0; i < level; i++) {
                TreeNode node = s1.pop();
                if (leftToRight) {
                    addNode(s2, node.left);
                    addNode(s2, node.right);
                } else {
                    addNode(s2, node.right);
                    addNode(s2, node.left);
                }
                row.add(node.val);
            }

            s1 = s2;
            s2 = new Stack<>();

            result.add(row);
            leftToRight = !leftToRight;
        }

        return result;
    }

    private void addNode(Stack<TreeNode> s, TreeNode node) {
        if (node != null) {
            s.push(node);
        }
    }
}
