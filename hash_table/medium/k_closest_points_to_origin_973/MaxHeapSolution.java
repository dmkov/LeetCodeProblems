package hash_table.medium.k_closest_points_to_origin_973;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 973. K Closest Points to Origin
 https://leetcode.com/problems/k-closest-points-to-origin/

 We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 (Here, the distance between two points on a plane is the Euclidean distance.)

 You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 Example 1:
 Input: points = [[1,3],[-2,2]], K = 1
 Output: [[-2,2]]
 Explanation:
 The distance between (1, 3) and the origin is sqrt(10).
 The distance between (-2, 2) and the origin is sqrt(8).
 Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

 Example 2:
 Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 Output: [[3,3],[-2,4]]
 (The answer [[-2,4],[3,3]] would also be accepted.)

 Note:
 1 <= K <= points.length <= 10000
 -10000 < points[i][0] < 10000
 -10000 < points[i][1] < 10000

 --------

 1. Complexity
    1.1 Time Complexity is O(nlogm) where is n - number of elements and m is the depth of max heap
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Calculate distance for every element then use PriorityQueue to poll required number of elements to the result.
 2.2 Implementation
    2.2.1 Check if given array is not null or empty. Calculate distance for every element as sqrt(a^2 + b^2). Store distance
        in the HashMap or separate structure (own Point class).
    2.2.2 Create PriorityQueue with minimal comparator. Populate it with custom points objects created
        from hash map numbers and distances.
    2.2.3 While k is not 0, poll next item from PriorityQueue and populate it to the result. Decrement k.
 */

class MaxHeapSolution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] result = new int[K][];
        if (K == 0 || points == null || points.length == 0
                || points[0] == null || points[0].length == 0) {
            return result;
        }

        Map<int[], Double> map = getDistances(points);
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(
                (a, b) -> {
                    double ans = map.get(a) - map.get(b);
                    return (ans > 0) ? 1 : (ans < 0 ? -1 : 0);
                }
        );
        for (int[] point: points) {
            queue.add(point);
        }

        for (int i = 0; i < K; i++) {
            result[i] = queue.poll();
        }

        return result;
    }

    private Map<int[], Double> getDistances(int[][] points) {
        Map<int[], Double> result = new HashMap<>();
        for (int[] point: points) {
            Double distance = Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
            result.put(point, distance);
        }

        return result;
    }
}
