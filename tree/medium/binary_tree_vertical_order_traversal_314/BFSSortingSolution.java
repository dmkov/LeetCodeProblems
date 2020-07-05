package tree.medium.binary_tree_vertical_order_traversal_314;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 314. Binary Tree Vertical Order Traversal
 https://leetcode.com/problems/binary-tree-vertical-order-traversal/

 Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 If two nodes are in the same row and column, the order should be from left to right.

 Examples 1:
 Input: [3,9,20,null,null,15,7]
        3
       /\
     9  20
        /\
      15   7

 Output:
 [
     [9],
     [3,15],
     [20],
     [7]
 ]

 --------

 1. Complexity
    1.1 Time Complexity is O(n*nlogn)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to combine level by level traversing with grouping nodes by shift (to left or to right).
    2.2 Here we use TreeMap to sort elements but it can be simplified with the range iterator
 */

class BFSSortingSolution {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class QueueItem {
        TreeNode node;
        int counter;

        public QueueItem (TreeNode node, int counter) {
            this.node = node;
            this.counter = counter;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<Integer, List<Integer>> map = new TreeMap<>();
        Deque<QueueItem> q = new ArrayDeque<>();
        q.addLast(new QueueItem(root, 0));

        while (q.size() > 0) {
            for (int i = q.size(); i > 0; i--) {
                QueueItem item = q.removeFirst();

                List<Integer> list = map.getOrDefault(item.counter, new ArrayList<>());
                list.add(item.node.val);
                map.put(item.counter, list);

                if (item.node.left != null) {
                    q.addLast(new QueueItem(item.node.left, item.counter - 1));
                }
                if (item.node.right != null) {
                    q.addLast(new QueueItem(item.node.right, item.counter + 1));
                }
            }
        }

        result.addAll(map.values());
        return result;
    }

}
