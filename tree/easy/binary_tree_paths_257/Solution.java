package tree.easy.binary_tree_paths_257;

import java.util.LinkedList;
import java.util.List;

/**
 108. Convert Sorted Array to Binary Search Tree
 https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

 Given a binary tree, return all root-to-leaf paths.
 Note: A leaf is a node with no children.

 Input:
     1
   /   \
  2     3
   \
    5

 Output: ["1->2->5", "1->3"]

 Explanation: All root-to-leaf paths are: 1->2->5, 1->3

 ---


 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on the idea recursive traverse of the tree (DSF)
    2.2 Implementation
        2.2.1 Put current element to the string
        2.2.2 Check if the current element is leaf, then put string to the list
        2.2.3 Otherwise, traverse child nodes and pass string to the next level
 */

class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new LinkedList<>();
        traverseTree(root, "", result);
        return result;
    }

    private void traverseTree(TreeNode node, String current, List<String> list) {
        if (node == null) return;
        if (current.length() > 0) {
            current += String.format("->%d", node.val);
        } else {
            current += String.format("%d", node.val);
        }

        if (node.left == null && node.right == null) {
            list.add(current);
        } else {
            traverseTree(node.left, current, list);
            traverseTree(node.right, current, list);
        }
    }
}
