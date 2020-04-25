package array.hard.the_skyline_problem_218;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

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
    1.1 Time Complexity is O(nlogk).
    1.2 Space Complexity is O(n)
 2. Approach
    Much better but more complex approach to use heap with linear traverse without removal.
    But need to cover all edge cases manually
 */

public class EfficientHeapSolution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        int height = 0;

        // 0 - x, 1 - height
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> {
            if (b[1] == a[1]) {
                return b[0] - a[0];
            } else {
                return b[1] - a[1];
            }
        });
        for (int[] building : buildings) {
            // 0 - left, 1 - right, 2 - height
            while (heap.size() > 0 && heap.peek()[0] <= building[0]) {
                int[] end = heap.poll();
                while(heap.size() > 0 && heap.peek()[0] <= end[0]) {
                    heap.poll();
                }
                if (end[1] == height) {
                    int newLine = 0;
                    if (end[0] == building[0]) {
                        if (end[1] == building[2]) {
                            continue;
                        }
                        newLine = building[2];
                    }
                    if (heap.size() > 0) {
                        if (heap.peek()[1] == end[1]) {
                            continue;
                        }
                        newLine = Math.max(newLine, heap.peek()[1]);
                    }
                    height = newLine;
                    addResult(result, end[0], height);
                }
            }
            if (building[2] > height) {
                height = building[2];
                addResult(result, building[0], height);
            }
            heap.add(new int[]{building[1], building[2]});
        }

        while (heap.size() > 0) {
            int[] end = heap.poll();
            while(heap.size() > 0 && heap.peek()[0] <= end[0]) {
                heap.poll();
            }
            if (end[1] == height) {
                height = 0;
                if (heap.size() > 0 && heap.peek()[0] > end[0]) {
                    if (heap.peek()[1] == end[1]) {
                        continue;
                    }
                    height = Math.max(height, heap.peek()[1]);
                }
                addResult(result, end[0], height);
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


    /**
     1. Are there 0 or negative heigths allowed?
     2. Should I output any changes in height?
     3. What is the output format?

         Start with 0 height
             iterate over buildings:
                 while heap.peek().x <= building.x
                     poll item
                     if item.heigth the same as height (need drop the line)
                         define height to drop:
                             height = 0
                             if (item.x == building.x) {
                                 if item.height == building.height -- continue
                                 height = max of old and building.height
                             }
                             if (heap > 0 && heap.peek.x > item.x) {
                                 if item.height == heap.peek.height -- continue
                                 height = max of old and heap.peek.height
                             }
                             add point with item.x and height
                         }
                     }
                 if building.height is bigger than height
                     -> add point and update height
            add end to the heap

     */
}
