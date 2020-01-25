package tree.easy.maximum_level_sum_of_a_binary_tree_1161;

import java.util.LinkedList;
import java.util.List;

/**
 1161. Maximum Level Sum of a Binary Tree
 https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/

 Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

 Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

 Example 1:
 Input: [1,7,0,7,-8,null,null]
 Output: 2
 Explanation:
 Level 1 sum = 1.
 Level 2 sum = 7 + 0 = 7.
 Level 3 sum = 7 + -8 = -1.
 So we return the level with the maximum sum which is level 2.

 Note:
 The number of nodes in the given tree is between 1 and 10^4.
 -10^5 <= node.val <= 10^5

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 Use LinkedList (queue) to do a level traverse.
 3. Implementation
     3.1 Check if given root element is not null.
     3.1 Create a queue object and iterate tree level by level.
     3.2 Calculate level sum and compare it with stored max, in case if local value is bigger, update the stored level.
 */

class BFSSolution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int maxLevelSum(TreeNode root) {
        int minLevel = 0; // 2
        if (root == null) {
            return minLevel;
        }

        int max = Integer.MIN_VALUE; // 7
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 1;
        while (queue.size() > 0) {
            int size = queue.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (sum > max) {
                max = sum;
                minLevel = level;
            }
            level++;
        }

        return minLevel;
    }
}
