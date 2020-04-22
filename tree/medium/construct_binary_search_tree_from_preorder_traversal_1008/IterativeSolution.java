package tree.medium.construct_binary_search_tree_from_preorder_traversal_1008;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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
     2.1 Idea is to use ArrayDeque as a stack to contain elements. Iterating over the array we check if the current
        element is smaller than peek() - then go to the left subtree, otherwise - to the right.
 */

class IterativeSolution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode bstFromPreorder(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(arr[0]);
        stack.addFirst(root);

        for (int i = 1; i < arr.length; i++) {
            TreeNode node = new TreeNode(arr[i]);

            if (arr[i] < stack.peekFirst().val) {
                TreeNode parent = stack.peekFirst();
                parent.left = node;
            } else {
                TreeNode parent = stack.removeFirst();
                while(stack.size() > 0 && stack.peekFirst().val < arr[i]) {
                    parent = stack.removeFirst();
                }
                parent.right = node;
            }
            stack.addFirst(node);
        }

        return root;
    }

    /**
     1. If smaller than peek -> left + push
     2. If bigger than peek -> pop,
     while not empty + peek is smaller -> pop
     right + push
     */

}
