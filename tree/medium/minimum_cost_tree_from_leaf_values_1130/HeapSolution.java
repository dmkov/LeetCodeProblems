package tree.medium.minimum_cost_tree_from_leaf_values_1130;

import java.util.PriorityQueue;

/**
 1130. Minimum Cost Tree From Leaf Values
 https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/

 Given an array arr of positive integers, consider all binary trees such that:

 Each node has either 0 or 2 children;
 The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
 The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
 Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.

 Example 1:
 Input: arr = [6,2,4]
 Output: 32
 Explanation:
 There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

        24            24
       /  \          /  \
      12   4        6    8
     /  \               / \
    6    2             2   4


 Constraints:
 2 <= arr.length <= 40
 1 <= arr[i] <= 15
 It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).

---

 1. Complexity
    1.1 Time Complexity is O(nlogn)
    1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is to create a heap to pull the smallest element every time. For this element select the min neighbor
        and multiply both numbers - it will give the smallest sum since all large numbers are postponed to the end.
 3 Implementation
     3.1 Check if input array is valid
     3.2 Convert array to linked list and push all nodes to priority queue.
     3.3 While queue has 2 elements or more, pop the smallest item and check its next and prev nodes.
     3.4 Increase sum for the product of smallest neighbor and the number itself, remove number from the linked list
        updating links in perv and the next nodes.
     3.5 Continue with the next element. Return total sum at the end
 */

class HeapSolution {
    class LinkedNode {
        int val;
        LinkedNode prev;
        LinkedNode next;

        public LinkedNode(int val) {
            this.val = val;
        }
    }

    public int mctFromLeafValues(int[] arr) {
        int sum = 0;
        if (arr == null || arr.length <= 1) {
            return sum;
        }

        PriorityQueue<LinkedNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        LinkedNode prev = null;
        for (int num : arr) {
            LinkedNode node = new LinkedNode(num);
            if (prev != null) {
                node.prev = prev;
                prev.next = node;
            }
            prev = node;

            pq.add(node);
        }

        while (pq.size() > 1) {
            LinkedNode node = pq.poll();

            int min = Integer.MAX_VALUE;
            if (node.prev != null) {
                if (node.prev.val < min) {
                    min = node.prev.val;
                }
                node.prev.next = node.next;
            }
            if (node.next != null) {
                if (node.next.val < min) {
                    min = node.next.val;
                }
                node.next.prev = node.prev;
            }

            sum += node.val * min;
            node = null;
        }

        return sum;
    }

    public static void main(String[] args) {
        HeapSolution solution = new HeapSolution();
        solution.mctFromLeafValues(new int[]{2,11,4,6,4,6,4});
    }
}
