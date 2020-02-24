package dynamic.medium.cheapest_flights_within_k_stops_787;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
     1.1 Time Complexity is O(K * logn)
     1.2 Space Complexity is...
 2. Approach
     2.1 The idea is to use Priority Queue to always get nodes with the cheapest cost. For every node we store number
        of steps to reach it and the cost. All flights are pre populated in the map with directions so we can get next
        cities to go from it. When we reach the destination it will be the cheapest way within provided K.
 3 Implementation
     3.1 Check if input array valid and src != dst and n is greater than 0.
     3.2 Populate flights into map with list of next cities from every node with prices.
     3.3 Create a min Priority Queue and push there first item with 0 cost and target to src node. To track steps,
        the first item has K+1 to start.
     3.4 While queue is not empty:
        3.4.1 Poll element. If it is a dst target - return its cost.
        3.4.2 Otherwise, check if there are remaining steps (k > 0) and path exists from this node in the map
        3.4.3 For every node if the map, add a new queue item with sum amount for the cost and reduced k for 1 step.
     3.5 If no path was found and queue is empty, return -1 at the end.
 */

class MinHeapDijkstraSolution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst || n == 0 || flights == null || flights.length == 0) {
            return 0;
        }

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] flight : flights) {
            List<int[]> list = map.getOrDefault(flight[0], new ArrayList<>());
            list.add(new int[]{flight[1], flight[2]});
            map.put(flight[0], list);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        queue.add(new int[]{0, src, K+1});
        while (queue.size() > 0) {
            int[] stop = queue.poll();

            if (stop[1] == dst) {
                return stop[0];
            }

            if (stop[2] > 0 && map.get(stop[1]) != null) {
                for (int[] dir : map.get(stop[1])) {
                    queue.add(new int[]{stop[0] + dir[1], dir[0], stop[2] - 1});
                }
            }
        }

        return -1;
    }
}
