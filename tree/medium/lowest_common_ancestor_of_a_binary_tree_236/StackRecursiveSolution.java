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
     2.1 The idea is based on using two stacks to get the path for each node and then get the latest common node from them.
 2.2 Implementation
     2.2.1 Check if input nodes are valid
     2.2.2 For every element populate the stack with nodes. Recursively check the node and its children. If node matches
        required element, push it to the stack and return true, otherwise return false.
     2.2.3 Get two elements from top of the stack. While they are equal, continue popping new items and save pointer to
        temporary ancestor. If elements from the stack do not match or ancestor equals to any given nodes, break the cycle
        and return the last ancestor.
 */

class StackRecursiveSolution {
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

        Stack<TreeNode> stackA = new Stack<>();
        Stack<TreeNode> stackB = new Stack<>();
        getPath(root, a, stackA);
        getPath(root, b, stackB);

        TreeNode tempA = stackA.pop();
        TreeNode tempB = stackB.pop();
        TreeNode ancestor = tempA;
        while (tempA.val == tempB.val && !a.equals(ancestor) && !b.equals(ancestor)) {
            ancestor = tempA;

            if (stackA.isEmpty() || stackB.isEmpty()) {
                break;
            } else {
                tempA = stackA.pop();
                tempB = stackB.pop();
            }
        }

        return ancestor;
    }

    private boolean getPath(TreeNode node, TreeNode searched, Stack<TreeNode> stack) {
        boolean result = false;
        if (node != null && (node.equals(searched)
                || getPath(node.left, searched, stack) || getPath(node.right, searched, stack))) {
            stack.push(node);
            result = true;
        }

        return result;
    }
}
