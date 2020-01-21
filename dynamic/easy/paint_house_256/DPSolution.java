package dynamic.easy.paint_house_256;

import java.util.Arrays;

/**
 256. Paint House
 https://leetcode.com/problems/paint-house/

 There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

 The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

 Note:
 All costs are positive integers.

 Example:
 Input: [[17,2,17],[16,16,5],[14,3,19]]
 Output: 10
 Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 Minimum cost: 2 + 5 + 3 = 10.

 --------

 1. Complexity
    1.1 Time Complexity is O(3n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 For every color calculate the sum with min value from the previous step and the current element.
 2.2 Implementation
    2.2.1 Check if input array is valid.
    2.2.2 Create dp array from 0 to 2 to represent each color. Populate it with the values from the first house.
    2.2.3 Iterate from the second house to the end. On every step get the min value from two different color of
        the previous dp state (you should copy dp before modifying it in the loop).
    2.2.4 At the end get the min value from 3 colors in the dp array.
 */

public class DPSolution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        int length = costs.length;
        int[] dp = new int[3];
        dp[0] = costs[0][0]; // 21
        dp[1] = costs[0][1]; // 10
        dp[2] = costs[0][2]; // 26

        for (int i = 1; i < length; i++) {
            int[] copy = Arrays.copyOfRange(dp, 0, 3);
            for (int j = 0; j < 3; j++) {
                dp[j] = costs[i][j] + getMin(copy, j);
            }
        }

        return getMin(dp, 3);
    }

    private int getMin(int[] costs, int indxToExclude) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (i == indxToExclude) {
                continue;
            }
            result = Math.min(costs[i], result);
        }

        return result;
    }
}
