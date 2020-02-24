package dynamic.medium.cheapest_flights_within_k_stops_787;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
     1.1 Time Complexity is O(K * l) where L is the avg number of paths between cities
     1.2 Space Complexity is O(K * n)
 2. Approach
     2.1 BFS approach with queue to implement Dijkstra algorithm. For every neighbor node, if it was not visited
        or if cost is not so efficient as the current one - add it to the queue for traverse. At the end, result will be
        stored in the visited hash map (not so efficient, check the min heap version).
 3 Implementation
     3.1 Check if input array valid and src != dst and n is greater than 0.
     3.2 Populate flights into map with list of next cities from every node with prices.
     3.3 Create a queue and put there first element to start with 0 cost.
     3.4 For 1 to K inclusively:
        3.4.1 For all elements in the queue on this level:
            3.4.1.1 Check if element exists in the path map (meaning there is a direction from it)
            3.4.1.2 Get the sum cost of current and new transition. Check if node was not visited or
                price is less than stored there.
            3.4.1.3 If yes, update the visited price and put element to the queue.
     3.5 If no path was found (dst is not in the visited map), return -1 at the end.
 */

class QueueDijkstraSolution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst || n == 0 || flights == null || flights.length == 0) {
            return 0;
        }

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] dir : flights) {
            List<int[]> srcs = map.getOrDefault(dir[0], new LinkedList<>());
            srcs.add(new int[]{dir[1], dir[2]});
            map.put(dir[0], srcs);
        }

        LinkedList<int[]> queue = new LinkedList<>();
        Map<Integer, Integer> visited = new HashMap<>();
        queue.add(new int[]{src, 0});
        visited.put(src, 0);
        for (int i = 0; i <= K; i++) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] node = queue.remove();
                if (map.containsKey(node[0])) {
                    for (int[] dir : map.get(node[0])) {
                        int cost = node[1] + dir[1];
                        if (!visited.containsKey(dir[0]) || visited.get(dir[0]) > cost) {
                            queue.add(new int[]{dir[0], cost});
                            visited.put(dir[0], cost);
                        }
                    }
                }
            }
        }

        if (visited.containsKey(dst)) {
            return visited.get(dst);
        }

        return -1;
    }
}
