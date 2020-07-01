package heap.medium.k_closest_points_to_origin_973;

import java.util.Arrays;

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
    1.1 Time Complexity is in average O(n) but in worst case is O(n^2)
    1.2 Space Complexity is O(k) because of array copy at the end
 2. Approach
    2.1 Use partitioning from quick sort (quick select) to find the pivot element equals to k.
 2.2 Implementation
    2.2.1 Check if given array is not null or empty.
    2.2.2 Do partitioning. Define left and right bounders and repeat inner operation while left <= right. If pivot is
        less than k, move left boundary to pivot + 1, if it is larger than k, move right to pivot - 1.
    2.2.3 Inside inner method define i as (start - 1) and j = i + 1 pointers. Pivot element will be the last one.
        Until j reaches (end - 1) index compare j element with the pivot. We need to sort from less to bigger items, so if
        j element is less than pivot, increment i and switch i and j element.
    2.2.4 At the end, after the while loop, increment i one more time and switch i element with the pivot. Return i as the
        result of partitioning.
 */

class PartitionSolution {
    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0 || points.length < k) {
            return null;
        }

        int left = 0, right = points.length - 1, pos = -1;
        while (pos + 1 != k) {
            pos = partition(points, left, right);
            if (pos + 1 > k) {
                right = pos - 1;
            } else if (pos + 1 < k) {
                left = pos + 1;
            }
        }

        return Arrays.copyOfRange(points, 0, pos + 1);
    }

    private int partition(int[][] arr, int left, int right) {
        int i = left;
        int j = left;
        while (i < right) {
            if (Double.compare(getDistance(arr[i]), getDistance(arr[right])) <= 0) {
                int[] temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
            i++;
        }
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return j;
    }

    private double getDistance(int[] a) {
        return Math.sqrt(a[0] * a[0] + a[1] * a[1]);
    }
}
