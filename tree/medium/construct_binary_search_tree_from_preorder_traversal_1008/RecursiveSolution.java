package tree.medium.construct_binary_search_tree_from_preorder_traversal_1008;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 1008. Construct Binary Search Tree from Preorder Traversal
 https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

 Return the root node of a binary search tree that matches the given preorder traversal.

 (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 Example 1:
 Input: [8,5,1,7,10,12]
 Output: [8,5,10,1,7,null,12]

 Note:
 1 <= preorder.length <= 100
 The values of preorder are distinct.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 Using upper and lower boundaries - create element in the right or left subtree.
 */

class RecursiveSolution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = preorder.length - 1; i >= 0; i --) {
            stack.addFirst(preorder[i]);
        }

        return traverse(stack, Integer.MAX_VALUE);
    }

    private TreeNode traverse(Deque<Integer> stack, int max) {
        if (stack.size() == 0 || stack.peekFirst() > max) {
            return null;
        }

        TreeNode node = new TreeNode(stack.removeFirst());
        node.left = traverse(stack, node.val);
        node.right = traverse(stack, max);

        return node;
    }

}
