package tree.medium.delete_nodes_and_return_forest_1110;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 1110. Delete Nodes And Return Forest
 https://leetcode.com/problems/delete-nodes-and-return-forest/

 Given the root of a binary tree, each node in the tree has a distinct value.

 After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

 Return the roots of the trees in the remaining forest.  You may return the result in any order.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n + h) where h is the depth of the tree
 2. Approach
     2.1 For every node recursively check children nodes and then return null as the result of the function
        if current node should be deleted.

 */

class TraverseSolution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null || toDelete == null || toDelete.length == 0) {
            res.add(root);
            return res;
        }

        Set<Integer> set = new HashSet<>();
        for (int i : toDelete) {
            set.add(i);
        }

        root = traverse(root, set, res);
        if (root != null) {
            res.add(root);
        }

        return res;
    }

    private TreeNode traverse(TreeNode node, Set<Integer> set, List<TreeNode> res) {
        if (node == null) {
            return null;
        }

        node.left = traverse(node.left, set, res);
        node.right = traverse(node.right, set, res);
        if (set.contains(node.val)) {
            if (node.left != null) {
                res.add(node.left);
            }
            if (node.right != null) {
                res.add(node.right);
            }
            node = null;
        }

        return node;
    }
}


/**
     1. I have a binary tree and a list of nodes too delete? Is this list am array of integers?
     2. Are there duplicated values? In other words can the value be presented twice in the tree?
     3. What is the possible values in the tree? Is it integers?
     4. This is a binary tree, not binary search tree?


     1. Traversing the tree and if value of node exists in the list
         - put all toDelete items too the set
         - for every node, call the recursive function
             call recursive function for the left and right nodes
                check the value in the set:
                 - if it exists:
                     add right and left non-null nodes to the result
                     return null
                 - else:
                     return node

         O(n) - time complexity
         O(n) - space complexity
 */
