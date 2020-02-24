package dynamic.medium.cheapest_flights_within_k_stops_787;

import java.util.Arrays;

/**
 787. Cheapest Flights Within K Stops
 https://leetcode.com/problems/cheapest-flights-within-k-stops/

 There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

 Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

 Example 1:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 1
 Output: 200

 Example 2:
 Input:
 n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 src = 0, dst = 2, k = 0
 Output: 500

 Note:
 The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 The size of flights will be in range [0, n * (n - 1) / 2].
 The format of each flight will be (src, dst, price).
 The price of each flight will be in the range [1, 10000].
 k is in the range of [0, n - 1].
 There will not be any duplicated flights or self cycles.

 --------

 1. Complexity
     1.1 Time Complexity is O(K * n)
     1.2 Space Complexity is O(K * n)
 2. Approach
     2.1 The idea is to use 2D array to store cost to get to every node in number of steps. By default all nodes have
        infinitive cost and only scr has 0 value. For every step we iterate over all flights and check, if 'from' is not
        infinitive we can update it with 'previous from + cost' value or leave the current one.
 3 Implementation
     3.1 Check if input array valid and src != dst and n is greater than 0.
     3.2 Create 2D dp array with K+2 (K+1 steps for transmissions and 1 extra for initial step) and n length.
     3.3 Populate initial level with Integer.MAX_VALUE and set src index to 0.
     3.4 Iterate dp array from 1 to K + 1 steps:
        3.4.1 Copy previous level to the current one.
        3.4.2 Iterate all flights and for every flight check if previous 'from' value is different from Integer.MAX_VALUE.
            If it does, set to the dst index min value of itself (previous step) or 'previous from + cost' value.
     3.5 Return dp[K+1][dst] or -1 if it is not filled.
 */

class DPSolution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst || n == 0 || flights == null || flights.length == 0) {
            return 0;
        }

        int[][] dp = new int[K+2][n];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][src] = 0;
        for (int i = 1; i <= K + 1; i++) {
            System.arraycopy(dp[i - 1], 0, dp[i], 0, n);

            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int cost = flight[2];
                if (dp[i - 1][from] != Integer.MAX_VALUE) {
                    dp[i][to] = Math.min(dp[i][to], dp[i - 1][from] + cost);
                }
            }
        }

        return (dp[K+1][dst] != Integer.MAX_VALUE) ? dp[K+1][dst] : -1;
    }
}
