package tree.easy.subtree_of_another_tree_572;

/**
 572. Subtree of Another Tree
 https://leetcode.com/problems/subtree-of-another-tree/

 Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

 Example 1:
 Given tree s:
      3
     / \
    4   5
   / \
  1   2

 Given tree t:
    4
   / \
  1   2

 Return true, because t has the same structure and node values with a subtree of s.

 Example 2:
 Given tree s:
        3
       / \
      4   5
     / \
    1   2
   /
  0

 Given tree t:
    4
   / \
  1   2

 Return false.

 ---


 1. Complexity
    1.1 Time Complexity is O(n) - where n is the number of nodes in given map
                           O(n*t) because od String.indexOf(), where n and t - lengths of strings
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on converting a tree to string and compare subsets.
        Alternative solution is to iterate trough tree and compare every node with given t recursively
    2.2 Convert both trees to strings and check subsets with indexOf() or contains() methods
        2.2.1 If node is null, return "lnull" or "rnull" depending of its position. This is a precaution from duplicates.
        2.2.2 Based on order recursively call converting for the left node, append value and right node.
        2.2.3 Add " " (spaces) between values as precaution of "12" and "1" + "2" values.

 */
class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        String s1 = convertToString(s, true);
        String t1 = convertToString(t, true);
        return s1.indexOf(t1) >= 0;
    }

    private String convertToString(TreeNode s, boolean left) {
        if (s == null) {
            if (left) {
                return "lnull";
            } else {
                return "rnull";
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(convertToString(s.left, true)).append(" ").append(s.val).append(" ")
                .append(convertToString(s.right, false));
        return result.toString();
    }


//    public boolean isSubtree(TreeNode s, TreeNode t) {
//        boolean result = false;
//        if (s.left != null) {
//            result = isSubtree(s.left, t);
//        }
//        if (!result) {
//            result = checkSubtree(s, t);
//        }
//        if (!result && s.right != null) {
//            result = isSubtree(s.right, t);
//        }
//        return result;
//    }
//
//    private boolean checkSubtree(TreeNode s, TreeNode t) {
//        if (s != null && t != null) {
//            return s.val == t.val && checkSubtree(s.left, t.left) && checkSubtree(s.right, t.right);
//        } else {
//            return s == t;
//        }
//    }
}
