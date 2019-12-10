package tree.medium.construct_binary_tree_from_preorder_and_postorder_traversal_889;

import java.util.Arrays;

/**
 889. Construct Binary Tree from Preorder and Postorder Traversal
 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/

 Return any binary tree that matches the given preorder and postorder traversals.
 Values in the traversals pre and post are distinct positive integers.

 Example 1:
 Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 Output: [1,2,3,4,5,6,7]

 Note:
 1 <= pre.length == post.length <= 30
 pre[] and post[] are both permutations of 1, 2, ..., pre.length.
 It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.

 --------

 1. Complexity
    1.1 Time Complexity is O(n^2) where n - number of nodes
    1.2 Space Complexity is O(n^2)
 2. Approach
    2.1 The key thing is to define the left subtree on every step. In pre-ordered array, the root of subtree will be
        next element after the first (first - is the current root). Then we need to find it in the post-ordered tree
        and length from start position will be the length of left subtree. Subtract it from pre and post-ordered trees
        and continue process recursively.
    2.2 Implementation
        2.2.1 First, check if arrays are not empty. Otherwise - return null. The current root element is the first in
            pre-ordered tree. If it consists of one element - return it as a result.
        2.2.2 Otherwise check the second element in pre array and find it in post list. Store index + 1 as a size of
            left subtree (L).
        2.2.3 Call same method recursively for the left and right subtrees (to the left should go everything before L
            element and for the right subtree - after).
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

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || post == null || pre.length == 0 || post.length == 0 || pre.length != post.length) {
            return null;
        }

        TreeNode root = new TreeNode(pre[0]);
        if (pre.length == 1) {
            return root;
        }

        int L = 0;
        for (int i = 0; i < post.length; i++) {
            if (pre[1] == post[i]) {
                L = i + 1;
                break;
            }
        }

        root.left = constructFromPrePost(
                Arrays.copyOfRange(pre, 1, L + 1),
                Arrays.copyOfRange(post, 0, L)
        );
        root.right = constructFromPrePost(
                Arrays.copyOfRange(pre, L + 1, pre.length),
                Arrays.copyOfRange(post, L, post.length - 1)
        );

        return root;
    }
}
