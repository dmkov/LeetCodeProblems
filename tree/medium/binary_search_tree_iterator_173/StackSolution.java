package tree.medium.binary_search_tree_iterator_173;

import java.util.Stack;

/**
 173. Binary Search Tree Iterator
 https://leetcode.com/problems/binary-search-tree-iterator/

 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 Calling next() will return the next smallest number in the BST.

 Note:
 next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Use stack to record the current position in the tree. With every next() call pop element and check if there
        is any items on the right. If yes - push it and its left nodes to the stack for further usage.
 */

class StackSolution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    Stack<TreeNode> stack;
    public StackSolution(TreeNode root) {
        stack = new Stack<>();
        add(root);
    }

    public int next() {
        int result = -1;
        if (hasNext()) {
            TreeNode node = stack.pop();
            add(node.right);
            result = node.val;
        }

        return result;
    }

    public boolean hasNext() {
        return (stack.size() > 0);
    }

    private void add(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

}
