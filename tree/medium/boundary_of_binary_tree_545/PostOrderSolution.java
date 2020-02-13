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
     2.1 Use preorder traverse with some additional conditions to define a left bound. For the right bound - return result
        list as the response of the internal function.
 3. Implementation
     3.1 Check if given node is not null. Add it to the result as initial point.
     3.1 Start preorder traverse for the left node if it exists. Pass additional flag to define that it is a left boundary.
        It flag is true, save the node value to the result immediately in the inner method.
     3.2 Repeat the same execution for the right boundary. Set flag to false this time and write down results of the traverse
        to the list that should be added to the result at the end.
     3.3 For leaves, add a node value if right and left children nodes are empty.
 */

class PostOrderSolution {
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
        if (root.left != null) {
            traverse(root.left, result, true);
        }
        if (root.right != null) {
            result.addAll(traverse(root.right, result, false));
        }

        return result;
    }

    private List<Integer> traverse(TreeNode node, List<Integer> result, boolean saveLeft) {
        if (node.left == null && node.right == null) {
            result.add(node.val);
            return new LinkedList<Integer>();
        } else if (saveLeft) {
            result.add(node.val);
        }

        List<Integer> res = null;
        if (node.left != null) {
            res = traverse(node.left, result, saveLeft);
        }
        if (node.right != null) {
            res = traverse(node.right, result, (node.left != null ? false : saveLeft));
        }
        res.add(node.val);

        return res;
    }
}
