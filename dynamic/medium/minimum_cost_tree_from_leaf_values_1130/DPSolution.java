package dynamic.medium.minimum_cost_tree_from_leaf_values_1130;

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
     1.1 Time Complexity is O(n^3)
     1.2 Space Complexity is O(n^2)
 2. Approach
     2.1 The idea is that every node K is a product of max values on the right and left (so, K = max(i, k) * max(k+1, j)).
     2.2 Additionally, to K we need to add sum of previous elements in the tree. It should be dp values from right and left subtrees
        so, dp[i,j] = K + dp[i][k] + dp[k+1][j]
 3 Implementation
     3.1 Check if input array is valid.
     3.2 Compute 2D array for max values. Iterate i for 0..length and j for i..length and check the max values on given range.
     3.3 Compute 2D array for dp values. To get previous values we need first iterate the shifting from 0..length.
     3.4 Another inner loop for i from 0..(i + k) < length, j will be i + k there.
     3.5 To compute dp[i, j]:
        3.5.1 If i == j it is 0 (by default)
        3.5.2 If (j - i) == 1, it is arr[i] * arr[j]
        3.5.3 In other cases, do iteration for m from i..j-1 and calculate the min value:
            dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m+1][j] + max[i][m] * max[m+1][j])
     3.6 Return dp[0][length-1] as the result
 */

class DPSolution {

    public int mctFromLeafValues(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int length = arr.length;
        int[][] max = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (i == j) {
                    max[i][j] = arr[j];
                } else {
                    max[i][j] = Math.max(max[i][j - 1], arr[j]);
                }
            }
        }

        int[][] dp = new int[length][length];
        for (int k = 1; k < length; k++) {
            for (int i = 0; i + k < length; i++) {
                int j = i + k;
                if (j - i == 1) {
                    dp[i][j] = arr[i] * arr[j];
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int m = i; m < j; m++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m+1][j] + max[i][m] * max[m+1][j]);
                    }
                }
            }
        }

        return dp[0][length - 1];
    }
}
