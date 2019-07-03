package tree.medium.add_one_row_to_tree_623;

import java.util.ArrayList;
import java.util.List;

/**
 623. Add One Row to Tree
 https://leetcode.com/problems/add-one-row-to-tree/

 Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.

 The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.

 Example 1:

 Input:
 A binary tree as following:
        4
      /   \
     2     6
    / \   /
   3   1 5

 v = 1
 d = 2

 Output:
       4
      / \
     1   1
    /     \
   2       6
  / \     /
 3   1   5

 Note:
 The given d is in range [1, maximum depth of the given tree + 1].
 The given binary tree has at least one tree node.

 --------

 1. Complexity
    1.1 Time Complexity is O(n). A total of nn nodes of the given tree will be considered in the worst case.
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 There are couple possible ways to iterate through the tree but the main idea is to find all nodes on n-1 level,
        and then create a new elements for them with migrating existing left and right leafs
    2.2 Implementation
        2.2.1 First, check if given level (d) is less than 2 (or exact 1). If yes, then create a new node and append
            all current tree to it.
        2.2.2 Using a list (it also can be done with stack) check all nodes row by row until you get to the needed level.
        2.2.3 Call a method to create new elements and re-append children for nodes in the selected row.
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

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d < 2) {
            TreeNode n = new TreeNode(v);
            n.left = root;

            return n;
        }

        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        for (int i = 2; i < d; i++) {
            ArrayList<TreeNode> temp = new ArrayList<>();
            for (TreeNode node: list) {
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }
            list = temp;
        }

        for (TreeNode node: list) {
            insertNewRow(node, v);
        }
        return root;
    }

    private void insertNewRow(TreeNode node, int v) {
        TreeNode l = new TreeNode(v);
        l.left = node.left;
        node.left = l;

        TreeNode r = new TreeNode(v);
        r.right = node.right;
        node.right = r;
    }
}
