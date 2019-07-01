package tree.easy.path_sum_iii_437;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 437. Path Sum III
 https://leetcode.com/problems/path-sum-iii/

 You are given a binary tree in which each node contains an integer value.
 Find the number of paths that sum to a given value.
 The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

 The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

 Example:
 root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

        10
       /  \
      5   -3
     / \    \
    3   2   11
   / \   \
  3  -2   1

 Return 3. The paths that sum to 8 are:

 1.  5 -> 3
 2.  5 -> 2 -> 1
 3. -3 -> 11


 ---


 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on the idea of storing preSum for every node in the tree (check implementation)
        Alternative solution is to store sum in array and increment it recursively for the left and right nodes
    2.2 Implementation
        2.2.1 The prefix stores the sum from the root to the current node in the recursion
        2.2.2 The map stores <prefix sum, frequency> pairs before getting to the current node. We can imagine a path from the root to the current node. The sum from any node in the middle of the path to the current node = the difference between the sum from the root to the current node and the prefix sum of the node in the middle.
        2.2.3 We are looking for some consecutive nodes that sum up to the given target value, which means the difference discussed in 2. should equal to the target value. In addition, we need to know how many differences are equal to the target value.
        2.2.4 Here comes the map. The map stores the frequency of all possible sum in the path to the current node. If the difference between the current sum and the target value exists in the map, there must exist a node in the middle of the path, such that from this node to the current node, the sum is equal to the target value.
        2.2.5 Note that there might be multiple nodes in the middle that satisfy what is discussed in 4. The frequency in the map is used to help with this.
        2.2.6 Therefore, in each recursion, the map stores all information we need to calculate the number of ranges that sum up to target. Note that each range starts from a middle node, ended by the current node.
        2.2.7 To get the total number of path count, we add up the number of valid paths ended by EACH node in the tree.
        2.2.8 Each recursion returns the total count of valid paths in the subtree rooted at the current node. And this sum can be divided into three parts:
            - the total number of valid paths in the subtree rooted at the current node's left child
            - the total number of valid paths in the subtree rooted at the current node's right child
            - the number of valid paths ended by the current node

        The interesting part of this solution is that the prefix is counted from the top(root) to the bottom(leaves), and the result of total count is calculated from the bottom to the top :D That is why the map is restored at the end of recursive method.
 */

class Solution {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        return traverseNode(root, preSum, 0, sum);
    }

    private int traverseNode(TreeNode node, Map<Integer, Integer> preSum, int current, int sum) {
        if (node == null) {
            return 0;
        }
        current += node.val;

        int result = preSum.getOrDefault(current - sum, 0);
        preSum.put(current, preSum.getOrDefault(current, 0) + 1);

        result += traverseNode(node.left, preSum, current, sum) + traverseNode(node.right, preSum, current, sum);
        preSum.put(current, preSum.get(current) - 1);

        return result;
    }

//    private int total = 0;
//
//    public int pathSum(TreeNode root, int sum) {
//        List<Integer> list = new ArrayList<>();
//        traverseNode(root, list, sum);
//
//        return total;
//    }
//
//    private void traverseNode(TreeNode node, List<Integer> list, int sum) {
//        if (node == null) {
//            return;
//        }
//        list.add(sum);
//
//        for (int i = 0; i < list.size(); i++) {
//            list.set(i, list.get(i) - node.val);
//            if (list.get(i) == 0) {
//                total++;
//            }
//        }
//        List<Integer> copy = new ArrayList<>(list);
//        traverseNode(node.left, list, sum);
//        traverseNode(node.right, copy, sum);
//    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(1);

        node4.right = node5;
        node2.left = node3;
        node2.right = node4;
        node1.left = node2;

        System.out.println(solution.pathSum(node1, 8));
    }
}
