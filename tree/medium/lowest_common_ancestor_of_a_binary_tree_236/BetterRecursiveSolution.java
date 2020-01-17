package tree.medium.lowest_common_ancestor_of_a_binary_tree_236;

import java.util.Stack;

/**
 236. Lowest Common Ancestor of a Binary Tree
 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

 Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
 the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

 Example 1:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 Output: 3
 Explanation: The LCA of nodes 5 and 1 is 3.

 Example 2:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 Output: 5
 Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


 Note:
 All of the nodes' values will be unique.
 p and q are different and both values will exist in the binary tree.

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is to return the first node that has match with given nodes on the left and right.
 2.2 Implementation
     2.2.1 Check if input nodes are valid
     2.2.2 Recursively call the method to find matching with given elements.
        2.2.2.1 If element is null, return null
        2.2.2.2 If element equals any of the given nodes, return current element
        2.2.2.3 Check right and left nodes recursively. If both of them are not null, return the current element
            as matched to the goal. If any one of them is not null, return it to push the result to top.
 */

class BetterRecursiveSolution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null || a == null || b == null) {
            return null;
        }

        return getLCA(root, a, b);
    }

    private TreeNode getLCA(TreeNode node, TreeNode a, TreeNode b) {
        if (node == null) {
            return null;
        }
        if (node.equals(a) || node.equals(b)) {
            return node;
        }

        TreeNode left = getLCA(node.left, a, b);
        TreeNode right = getLCA(node.right, a, b);
        if (left != null && right != null) {
            return node;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }

        return null;
    }
}
