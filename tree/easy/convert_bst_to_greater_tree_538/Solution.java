package tree.easy.convert_bst_to_greater_tree_538;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import tree.easy.convert_bst_to_greater_tree_538.Solution.TreeNode;

/**
 538. Convert BST to Greater Tree
 https://leetcode.com/problems/convert-bst-to-greater-tree/

 Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

 Example:
 Input: The root of a Binary Search Tree like this:
     5
   /   \
 2     13

 Output: The root of a Greater Tree like this:
    18
   /   \
 20     13

 ---


 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 This solution is based on the in-order traversal of the tree. Starting with max number we store it to the
        sub-sum and add its value to all elements lesser than current
    2.2 Implementation
        2.2.1 Recursively call right element, add current value to the sub-sum (default value is 0)
        2.2.2 Update element value with the sub-sum
        2.2.2 Recursively traverse left sub node
 */

class Solution {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
