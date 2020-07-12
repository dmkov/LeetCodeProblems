package tree.easy.closest_binary_search_tree_value_270;

/**
 270. Closest Binary Search Tree Value
 https://leetcode.com/problems/closest-binary-search-tree-value/

 Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

 Note:
 Given target value is a floating point.
 You are guaranteed to have only one unique value in the BST that is closest to the target.
 Example:

 Input: root = [4,2,5,1,3], target = 3.714286

        4
       / \
      2   5
     / \
    1   3

 Output: 4

 --------

 1. Complexity
    1.1 Time Complexity is O(h)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is based on BFS or Dijkstra path searching. We store number of steps and then visit this node again
        only if active counter is smaller than the stored value.
    2.2 The main question is how to build a graph. Here we check all pairs and if there is difference
        only in one character - add the link. It is not the most efficient way. Please check approach with '*' masking
 */

class BinarySearchSolution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int closestValue(TreeNode root, double target) {
        int val = root.val, result = root.val;
        while (root != null) {
            val = root.val;
            result = Math.abs(val - target) <  Math.abs(result - target) ? val : result;
            root = target < val ? root.left : root.right;
        }

        return result;
    }

}
