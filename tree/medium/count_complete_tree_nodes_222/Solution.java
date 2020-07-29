package tree.medium.count_complete_tree_nodes_222;

/**
 222. Count Complete Tree Nodes
 https://leetcode.com/problems/count-complete-tree-nodes/

 Given a complete binary tree, count the number of nodes.

 Note:
 Definition of a complete binary tree from Wikipedia:
 In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

 Example:

 Input:
     1
    / \
   2   3
  / \  /
 4  5 6

 Output: 6

 --------

 1. Complexity
    1.1 Time Complexity is O(logn * logn). There are two binary searches.
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The approach is based on the idea of binary searching the first missing element on the last level
    2.2 Implementation
        2.2.1 Return 0 if the tree is empty.
        2.2.2 Compute the tree depth d. Return 1 if d == 1.
        2.2.3. The number of nodes in all levels but the last one is 2^(depth - 1) - 1. The number of nodes in the last level
            could vary from 1 to 2^(depth - 1). Enumerate potential nodes from 0 to 2^d - 1 and perform the binary search
            by the node index to check how many nodes are in the last level. Use the function exists(idx, d, root)
            to check if the node with index idx exists.
        2.2.4. Use binary search to implement exists(idx, d, root) as well.
        2.2.5. Return 2^(depth - 1) - 1 + the number of nodes in the last level..
*/

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

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 1;
        TreeNode temp = root;
        while (temp.left != null) {
            temp = temp.left;
            depth++; // 3
        }

        // the total number of nodes is (2^0 + 2^1 + 2^2 ... + 2^(depth-1)) or 2^depth - 1
        // we need to get number of items on the last level

        int left = 1;
        int right = (int)Math.pow(2, depth - 1);
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nodeExists(mid, depth, root)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return (left - 1) + (int)Math.pow(2, depth - 1) - 1;
    }

    private boolean nodeExists(int index, int depth, TreeNode node) {
        int left = 1;
        int right = (int)Math.pow(2, depth - 1);
        int mid;
        while (depth != 1) {
            mid = left + (right - left) / 2;
            if (index > mid) {
                node = node.right;
                left = mid + 1;
            } else {
                node = node.left;
                right = mid;
            }
            depth--;
        }
        return node != null;
    }
}
