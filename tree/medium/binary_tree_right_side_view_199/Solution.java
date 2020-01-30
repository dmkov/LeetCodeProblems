package tree.medium.binary_tree_right_side_view_199;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 199. Binary Tree Right Side View
 https://leetcode.com/problems/binary-tree-right-side-view/

 Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

 Example:
 Input: [1,2,3,null,5,null,4]
 Output: [1, 3, 4]
 Explanation:

        1            <---
      /   \
     2     3         <---
      \     \
       5     4       <---

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 First populate list with right elements then traverse to the left node
 3. Implementation
     3.1 Create an empty result list and traverse the root element with the 0 level.
     3.1 If result list has the same size as passed level (level was not processed before), set the value to the list
        otherwise, traverse right and left nodes.
 */

class Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result, 0);

        return result;
    }

    private void traverse(TreeNode node, List<Integer> list, int level) {
        if (node == null) {
            return;
        }
        if (list.size() <= level || list.get(level) == null) {
            list.add(level, node.val);
        }
        traverse(node.right, list, level + 1);
        traverse(node.left, list, level + 1);
    }
}
