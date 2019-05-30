package tree.easy.same_tree_100;

/**
 100. Same Tree
 https://leetcode.com/problems/same-tree/

 Given two binary trees, write a function to check if they are the same or not.

 Two binary trees are considered the same if they are structurally identical and the nodes have the same value.


 ---

 1. Complexity
    1.1 Time Complexity is O(n) - number of nodes or O(2^n) - depth level
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on recursion check.
    2.2 Compare every node recursively
        2.2.1 Check if both elements are not null
        2.2.2 Check values and recursively left and right nodes
        2.2.3 If at least one of elements is null, compare objects
        2.2.4 It can be at the end of node or in case of missing items
        2.2.5 Repeat until reaches the last element

 */
class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null) {
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return p == q;
        }
    }


//    public boolean isSameTree(TreeNode p, TreeNode q) {
//        Integer[] pArr = convertTreeToArray(p);
//        Integer[] qArr = convertTreeToArray(q);
//        return Arrays.equals(pArr, qArr);
//    }
//
//    private Integer[] convertTreeToArray(TreeNode p) {
//        Integer[] arr = null;
//        if (p == null) {
//            return new Integer[]{null};
//        }
//        if (p.left != null || p.right != null) {
//            arr = convertTreeToArray(p.left);
//        }
//        arr = mergeArrays(arr, new Integer[]{p.val});
//        if (p.left != null || p.right != null) {
//            arr = mergeArrays(arr, convertTreeToArray(p.right));
//        }
//
//        return arr;
//    }
//
//    private Integer[] mergeArrays(Integer[] first, Integer[] second) {
//        if (first == null) {
//            return second;
//        }
//        if (second == null) {
//            return first;
//        }
//        Integer[] both = Arrays.copyOf(first, first.length + second.length);
//        System.arraycopy(second, 0, both, first.length, second.length);
//
//        return both;
//    }
}
