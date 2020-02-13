package tree.medium.boundary_of_binary_tree_545;

import java.util.LinkedList;
import java.util.List;

/**
 545. Boundary of Binary Tree
 https://leetcode.com/problems/boundary-of-binary-tree/

 Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)

 Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

 The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

 The right-most node is also defined by the same way with left and right exchanged.

 Example 1
 Input:
        1
         \
          2
         / \
        3   4

 Ouput:
 [1, 3, 4, 2]

 Explanation:
 The root doesn't have left subtree, so the root itself is left boundary.
 The leaves are node 3 and 4.
 The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 So order them in anti-clockwise without duplicates and we have [1,3,4,2].


 Example 2
 Input:
        ____1_____
       /          \
      2            3
     / \          /
    4   5        6
       / \      / \
      7   8    9  10

 Ouput:
 [1,2,4,7,8,9,10,6,3]

 Explanation:
 The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 The leaves are node 4,7,8,9,10.
 The right boundary are node 1,3,6,10. (10 is the right-most node).
 So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 Idea is to use three different execution/methods - for the left bounder, right bounder and storing leaves.
        In such way we do not need a condition inside the method but there is extra steps and double work.
 */

class SimplerButWithExtraStepsSolution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        result.add(root.val);
        leftBoundary(root.left, result);
        leaves(root.left, result);
        leaves(root.right, result);
        rightBoundary(root.right, result);

        return result;
    }

    private void leftBoundary(TreeNode root, List<Integer> result) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        result.add(root.val);

        if (root.left == null) {
            leftBoundary(root.right, result);
        } else {
            leftBoundary(root.left, result);
        }
    }

    private void rightBoundary(TreeNode root, List<Integer> result) {
        if (root == null || (root.right == null && root.left == null)) {
            return;
        }
        if (root.right == null) {
            rightBoundary(root.left, result);
        } else {
            rightBoundary(root.right, result);
        }
        result.add(root.val); // add after child visit(reverse)
    }

    private void leaves(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result.add(root.val);
            return;
        }
        leaves(root.left, result);
        leaves(root.right, result);
    }
}
