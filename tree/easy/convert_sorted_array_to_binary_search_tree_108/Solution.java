package tree.easy.convert_sorted_array_to_binary_search_tree_108;

/**
 108. Convert Sorted Array to Binary Search Tree
 https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

 Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 Example:
 Given the sorted array: [-10,-3,0,5,9],

 One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
        0
       / \
     -3   9
     /   /
   -10  5

 ---


 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on the idea that the vertex of BST is the median value in sorted array range
    2.2 Implementation
        2.2.1 Set left and right limits to 0 and array.length-1
        2.2.2 Define middle element in the specified range. Check left:index-1 and index+1:right intervals recursively
 */

class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        return getNode(nums, left, right);
    }

    private TreeNode getNode(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int index = left + (right - left + 1) / 2;

        TreeNode node = new TreeNode(nums[index]);
        node.left = getNode(nums, left, index - 1);
        node.right = getNode(nums, index + 1, right);

        return node;
    }
}
