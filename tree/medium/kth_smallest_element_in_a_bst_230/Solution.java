package tree.medium.kth_smallest_element_in_a_bst_230;

import java.util.Stack;

/**
 230. Kth Smallest Element in a BST
 https://leetcode.com/problems/kth-smallest-element-in-a-bst/

 Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

 Example 1:
 Input: root = [3,1,4,null,2], k = 1
        3
       / \
      1   4
      \
       2
 Output: 1

 Follow up:
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
     2.1 It is a DFS based on in-order traverse. To do not use additional array and recursion it can be implemented
        with stack and while loop
     2.2 Implementation
        2.2.1 Set root element to cur pointer. Enter main loop while k>0
        2.2.2 Create another loop while cur != null. Push cur to the stack and assign cur to the left node.
        2.2.3 Pop cur from the stack. Assign value of cur to the result and decrement the k.
        2.2.4 Assign cur.right to the cur.
 */
class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int kthSmallest(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return 0;
        }

        int result = root.val;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (k > 0) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            result = cur.val;
            k--;

            cur = cur.right;
        }

        return result;
    }
}
