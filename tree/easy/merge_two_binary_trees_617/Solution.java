package tree.easy.merge_two_binary_trees_617;

/**
 617. Merge Two Binary Trees
 https://leetcode.com/problems/merge-two-binary-trees/

 Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

 You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

 Example 1:
 Input:
        Tree 1                     Tree 2
          1                         2
         / \                       / \
        3   2                     1   3
       /                           \   \
      5                             4   7

 Output:
 Merged tree:
         3
        / \
       4   5
      / \   \
     5   4   7

 ---


 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 This solution is based on the idea recursive traverse of the tree (BSF)
    2.2 Implementation
        2.2.1 If both input nodes are null - return null
        2.2.2 Create a new node and assign sum of both elements to it. In case of nullable element - skip it
        2.2.3 Call method recursively for left and right elements with checking them for null
 */

class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }

        TreeNode t3 = new TreeNode(0);
        if (t1 != null) {
            t3.val += t1.val;
        }
        if (t2 != null) {
            t3.val += t2.val;
        }
        t3.left = mergeTrees((t1 != null ? t1.left : null), (t2 != null ? t2.left : null));
        t3.right = mergeTrees((t1 != null ? t1.right : null), (t2 != null ? t2.right : null));

        return t3;
    }

//    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
//        if (t1 == null)
//            return t2;
//        if (t2 == null)
//            return t1;
//        t1.val += t2.val;
//        t1.left = mergeTrees(t1.left, t2.left);
//        t1.right = mergeTrees(t1.right, t2.right);
//        return t1;
//    }
}
