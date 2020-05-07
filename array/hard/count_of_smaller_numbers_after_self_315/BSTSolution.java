package array.hard.count_of_smaller_numbers_after_self_315;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 315. Count of Smaller Numbers After Self
 https://leetcode.com/problems/count-of-smaller-numbers-after-self/

 You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 Example:
 Input: [5,2,6,1]
 Output: [2,1,1,0]
 Explanation:
 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.

 --------

 1. Complexity
    1.1 Time Complexity is O(n^2).
    1.2 Space Complexity is O(n)
 2. Approach
    https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76587/Easiest-Java-solution
    2.1 Not the most efficient approach. Check idea with merge sort
    2.2 The idea is to build BST based on values from right to left. With every turn left (the number is smaller than the
        current one) we increase the counter for the current node. For every turn right (number is bigger) we add current
        counter to the result
        The issue is that the tree is not optimised and the time complexity can be O(n^2) in the worst case
 */

public class BSTSolution {
    class TreeNode {
        TreeNode left, right;
        int val;
        int count;

        public TreeNode(int val) {
            this.count = 1;
            this.val = val;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        TreeNode root = new TreeNode(nums[nums.length-1]);
        List<Integer> res = new ArrayList<>(Arrays.asList(new Integer[nums.length]));
        res.set(nums.length-1, 0);
        for (int i = nums.length - 2; i >= 0; i--) {
            res.set(i, addTreeNode(root, nums[i]));
        }
        return res;
    }

    // res = 0,0,0,0
    // indx = 0,1,2,3
    // nums = 5,2,6,1
    // 2,5(1)

    private int addTreeNode(TreeNode root, int val) {
        int res = 0;

        if (val <= root.val) {
            root.count += 1;
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                res = addTreeNode(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                res = addTreeNode(root.right, val);
            }
            res += root.count;
        }

        return res;
    }
}

