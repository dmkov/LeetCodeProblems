package tree.easy.minimum_absolute_difference_in_bst_530;

/**
 530. Minimum Absolute Difference in BST
 https://leetcode.com/problems/minimum-absolute-difference-in-bst/

 Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

 Example:

 Input:
    1
     \
      3
     /
    2

 Output:
 1

 Explanation:
 The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).


 Note: There are at least two nodes in this BST.

 ---


 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on inorder traverse of the tree and comparing current and previous values.
        Alternative solution is to check left and right values for every node recursively (~nlogn)
    2.2 Implementation
        2.2.1 Set default diff to Integer.MAX_VALUE and prev item to null
        2.2.2 Traverse through the tree. If prev if not null check min between global and node diffs
        2.2.3 Node diff is calculated between node.val and stored prev value
 */

class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private Integer prev;
    private int diff;

    public int getMinimumDifference(TreeNode root) {
        diff = Integer.MAX_VALUE;
        inorderTraverse(root);
        return diff;
    }

    private void inorderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.left);
        if (prev != null) {
            diff = Math.min(diff, Math.abs(root.val - prev));
        }
        prev = root.val;
        inorderTraverse(root.right);
    }

//    public int getMinimumDifference(TreeNode root) {
//        if (root.left == null && root.right == null) {
//            return Integer.MAX_VALUE;
//        }
//        int diff = Integer.MAX_VALUE;
//        if (root.left != null) {
//            int leftDiff = Math.min(Math.abs(root.val - getLeftValue(root.left)), getMinimumDifference(root.left));
//            diff = Math.min(diff, leftDiff);
//        }
//        if (root.right != null) {
//            int rightDiff = Math.min(Math.abs(getRightValue(root.right)- root.val), getMinimumDifference(root.right));
//            diff = Math.min(diff, rightDiff);
//        }
//
//        return diff;
//    }
//
//    private int getLeftValue(TreeNode node) {
//        if (node.right != null) {
//            return getLeftValue(node.right);
//        } else {
//            return node.val;
//        }
//    }
//
//    private int getRightValue(TreeNode node) {
//        if (node.left != null) {
//            return getRightValue(node.left);
//        } else {
//            return node.val;
//        }
//    }
}
