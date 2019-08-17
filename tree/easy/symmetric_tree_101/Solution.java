package tree.easy.symmetric_tree_101;

import java.util.Stack;

/**
 101. Symmetric Tree
 https://leetcode.com/problems/symmetric-tree/

 Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
        1
       / \
      2   2
     / \ / \
    3  4 4  3

 But the following [1,2,2,null,3,null,3] is not:
        1
       / \
      2   2
      \   \
      3    3

 Note:
 Bonus points if you could solve it both recursively and iteratively.

 ---


 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1) for recursive and O(n) for iterative solution
 2. Approach
    2.1 A tree is symmetric if the left subtree is a mirror reflection of the right subtree. So we need to compare
        left leaf of every node with right leaf of symmetric node. For iterative approach we can use stack to push
        both items and for recursive - separate method to compare them.
    2.2 Implementation
        2.2.1 Check that root node is not null. Put both left and right nodes to the stack.
        2.2.2 Until stack is empty - pop two nodes at a time. If both of them are null - skip current iteration, if one
            of them is null or values do not match - return false (tree is not symmetric). Otherwise add next pairs to
            the stack: first.left and second.right and first.right and second.left. Repeat.
 */

class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);

        while (!stack.empty()) {
            TreeNode n1 = stack.pop();
            TreeNode n2 = stack.pop();

            if (n1 == null && n2 == null) {
                continue;
            }
            if (n1 == null || n2 == null || n1.val != n2.val) {
                return false;
            }
            stack.push(n1.left);
            stack.push(n2.right);
            stack.push(n1.right);
            stack.push(n2.left);
        }

        return true;
    }

//    public boolean isSymmetric(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//
//        return isSymmetric(root.left, root.right);
//    }
//
//    private boolean isSymmetric(TreeNode n1, TreeNode n2) {
//        if (n1 == null && n2 == null) {
//            return true;
//        }
//        if (n1 == null || n2 == null || n1.val != n2.val) {
//            return false;
//        }
//
//        return isSymmetric(n1.left, n2.right) && isSymmetric(n1.right, n2.left);
//    }

}
