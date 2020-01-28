package dynamic.medium.minimum_cost_tree_from_leaf_values_1130;

import java.util.ArrayList;
import java.util.List;

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

 --------

 1. Complexity
     1.1 Time Complexity is O(n^2)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is that every node K is a product of max values on the right and left. To get the min sum we need
        always to select smallest elements that give us a smallest product (bigger values should be popped to the top to reduce the sum).
     2.2 Every time, get the smallest number, multiply it with the smallest neighbor and remove it, leaving bigger element
        for future calculation.
 3 Implementation
     3.1 Check if input array is valid.
     3.2 Create array list to manipulate elements and populate it from the array
     3.3 Iterate while list has 2 or more elements in it.
        3.3.1 Find the smallest element in the list.
        3.3.2 Check if there left or right neighbors, select the smallest one
        3.3.3 Increment result by product of two given numbers and remove the smallest one from the list.
 */

class GreedyMinSolution {

    public int mctFromLeafValues(int[] arr) {
        int result = 0;
        if (arr == null || arr.length == 0) {
            return result;
        }

        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            list.add(num);
        }

        while (list.size() > 1) {
            int first = 0;
            int min = list.get(0);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) < min) {
                    first = i;
                    min = list.get(i);
                }
            }
            int second = 0;
            if (first == 0
                    || (first != list.size() - 1 && list.get(first + 1) < list.get(first - 1))) {
                second = first + 1;
            } else {
                second = first - 1;
            }

            result += list.get(first)*list.get(second);
            list.remove(first);
        }

        return result;
    }
}
