package tree.hard.binary_tree_maximum_path_sum_124;

/**
 297. Serialize and Deserialize Binary Tree
 https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

 Example:
 You may serialize the following tree:
        1
       / \
      2   3
         / \
        4   5
 as "[1,2,3,null,null,4,5]"

 Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(logn) for memory stack
 2. Approach
     2.1 The idea is to traverse all nodes recursively and then get the max possible value if we combine left+right+val
        (means we put horde cross this node) and return the max of only one - val + left or right since they can form the
        next results
 */

class TraverseSolution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] res = getMax(root);
        return res[1];
    }

    private int[] getMax(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        }
        int[] left = getMax(node.left);
        int[] right = getMax(node.right);

        int leftSum = Math.max(left[0], 0);
        int rightSum = Math.max(right[0], 0);

        int max = node.val + leftSum + rightSum;
        max = Math.max(max, Math.max(left[1], right[1]));
        int val = node.val + Math.max(leftSum, rightSum);

        return new int[]{val, max};
    }
}

/**
 1. Do we have a BST or just Binary tree?
 2. Are negatives and zeros allowed?
 3. What is the output format? For input do we have TreeNode?
 4. What is the min/max values?

    1   -> 6?
   / \
  2   3

    1  -> 2?
   / \
 -1  -2
      \
      2

    -5   -> -1?
    / \
  -2   -3

     1. Traverse all nodes from root to leaves:
         - set max result to val
         - if left != null and its traverse > 0 -> add it to max result
         - if right != null and  its traverse > 0 -> add it to max result

     to save global max, use value object or class property
     or return array with two indexes

 O(n) - time and space complexity

 */
