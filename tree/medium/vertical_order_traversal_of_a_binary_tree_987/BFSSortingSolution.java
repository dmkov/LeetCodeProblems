package tree.medium.vertical_order_traversal_of_a_binary_tree_987;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 987. Vertical Order Traversal of a Binary Tree
 https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

 Given a binary tree, return the vertical order traversal of its nodes values.
 For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

 If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

 Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

 Example 1:
 Input: [3,9,20,null,null,15,7]
 Output: [[9],[3,15],[20],[7]]
 Explanation:
 Without loss of generality, we can assume the root node is at position (0, 0):
 Then, the node with value 9 occurs at position (-1, -1);
 The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 The node with value 20 occurs at position (1, -1);
 The node with value 7 occurs at position (2, -2).

 --------

 1. Complexity
    1.1 Time Complexity is O(nlogn)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The problem is tricky because of three level sorting. Without comparing values it can be
        solved as #314 task, however, with sorting by values we need to create a separate triple
        and sort it after traversing.
    2.2 For more details, check https://leetcode.com/articles/vertical-order-traversal-of-a-binary-tree/
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
        int shift;

        public QueueItem (TreeNode node, int shift) {
            this.node = node;
            this.shift = shift;
        }
    }

    class ListItem {
        int val;
        int shift;
        int level;

        public ListItem (int val, int shift, int level) {
            this.val = val;
            this.shift = shift;
            this.level = level;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<ListItem> list = new ArrayList<>();
        Deque<QueueItem> q = new ArrayDeque<>();
        q.addLast(new QueueItem(root, 0));
        int level = 0;

        while (q.size() > 0) {
            for (int i = q.size(); i > 0; i--) {
                QueueItem item = q.removeFirst();

                list.add(new ListItem(item.node.val, item.shift, level));
                if (item.node.left != null) {
                    q.addLast(new QueueItem(item.node.left, item.shift - 1));
                }
                if (item.node.right != null) {
                    q.addLast(new QueueItem(item.node.right, item.shift + 1));
                }
            }
            level++;
        }

        Collections.sort(list, (a, b) -> {
            if (a.shift == b.shift && a.level == b.level) {
                return a.val - b.val;
            }
            if (a.shift == b.shift) {
                return a.level - b.level;
            }
            return a.shift - b.shift;
        });

        int prev = Integer.MIN_VALUE;
        List<Integer> levels = new ArrayList<>();
        for (ListItem item : list) {
            if (item.shift != prev) {
                if (levels.size() > 0) {
                    result.add(levels);
                    levels = new ArrayList<>();
                }
                prev = item.shift;
            }
            levels.add(item.val);
        }
        result.add(levels);

        return result;
    }

}
