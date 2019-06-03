package tree.medium.path_sum_ii_113;

/**
 113. Path Sum II
 https://leetcode.com/problems/path-sum-ii/

 Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 Note: A leaf is a node with no children.

 Example:
 Given the below binary tree and sum = 22,

        5
       / \
      4   8
     /   / \
    11  13  4
   /  \    / \
  7    2  5   1

 Return:
 [
    [5,4,11,2],
    [5,8,4,5]
 ]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        return arrayReverse(checkSum(root, sum));
    }

    private List<List<Integer>> checkSum(TreeNode node, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (node == null) {
            return result;
        }
        sum -= node.val;

        if (node.left != null || node.right != null) {
            List<List<Integer>> left = checkSum(node.left, sum);
            List<List<Integer>> right = checkSum(node.right, sum);

            if (!left.isEmpty()) {
                result.addAll(left);
            }
            if (!right.isEmpty()) {
                result.addAll(right);
            }

            if (result.size() != 0) {
                for (List<Integer> list: result) {
                    list.add(node.val);
                }
            }
        } else if (sum == 0) {
            result.add(new ArrayList<>(Arrays.asList(node.val)));
        }

        return result;
    }

    private List<List<Integer>> arrayReverse(List<List<Integer>> result) {
        for (List<Integer> list: result) {
            Collections.reverse(list);
        }
        return result;
    }
}
