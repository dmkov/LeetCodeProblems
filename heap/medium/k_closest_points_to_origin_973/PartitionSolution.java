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
        int[][] result = new int[k][];
        if (k == 0 || points == null || points.length == 0
                || points[0] == null || points[0].length == 0) {
            return result;
        }

        partition(points, k);

        return Arrays.copyOfRange(points, 0, k);
    }

    private void partition(int[][] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = partition(arr, left, right);
            if (mid == k - 1) {
                break;
            } else if (mid < k - 1) {
                left = mid + 1;
            } else if (mid > k - 1) {
                right = mid - 1;
            }
        }
    }

    private int partition(int[][] arr, int start, int end) {
        int i = start - 1;
        int j = i + 1;
        int[] p = arr[end];

        while (j < end) {
            if (getDistance(arr[j]) < getDistance(p)) {
                i++;
                int[] temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
            j++;
        }
        i++;
        arr[end] = arr[i];
        arr[i] = p;

        return i;
    }

    private double getDistance(int[] point) {
        return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
    }
}
