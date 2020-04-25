package array.hard.the_skyline_problem_218;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 218. The Skyline Problem
 https://leetcode.com/problems/the-skyline-problem/

 A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings Skyline Contour
 The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

 For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

 The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

 For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

 Notes:
 The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 The input list is already sorted in ascending order by the left x position Li.
 The output list must be sorted by the x position.
 There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

 --------

 1. Complexity
    1.1 Time Complexity is O(n^2). It is not the best approach, check divide and conquer solution
    1.2 Space Complexity is O(n)
 2. Approach
    https://www.youtube.com/watch?v=GSBLe8cKu0s
    2.1 We put all start and end points separately and sort them in the array
    2.2 Iterating all points, we put height to the heap, it is start point and remove from the heap if it is end point
        In case if heap.peek() is different after update from previous value - we need to store the change.
    2.3 Heap remove takes O(n), use TreeMap we number of occurrences for every value (decrease/increase it with update)
 */

public class HeapSolution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();

        int[][] heights = new int[buildings.length*2][3];
        int i=0;
        for (int[] building : buildings) {
            // 0 - x, 1 - height, 2 - type (0 for start, 1 for end)
            heights[i] = new int[]{building[0], building[2], 0};
            heights[i+1] = new int[]{building[1], building[2], 1};
            i += 2;
        }

        Arrays.sort(heights, (a, b) -> {
            if (b[0] == a[0]) {
                return a[2] - b[2];
            } else {
                return a[0] - b[0];
            }
        });

        Queue<Integer> heap = new PriorityQueue<>((a,b) -> b - a);
        int prev = 0;
        heap.offer(0);

        for (int[] point : heights) {
            if (point[2] == 0) {
                heap.offer(point[1]);
            } else {
                heap.remove(point[1]);
            }

            int cur = heap.peek();
            if (prev != cur) {
                addResult(result, point[0], cur);
                prev = cur;
            }
        }

        return result;
    }

    private void addResult(List<List<Integer>> result, int x, int height) {
        if (result.size() > 0 && result.get(result.size()-1).get(0) == x) {
            result.set(result.size()-1, Arrays.asList(x, height));
        } else {
            result.add(Arrays.asList(x, height));
        }
    }
}
