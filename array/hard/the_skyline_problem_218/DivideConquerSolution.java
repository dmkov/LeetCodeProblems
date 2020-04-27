package array.hard.the_skyline_problem_218;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    1.1 Time Complexity is O(n logn).
    1.2 Space Complexity is O(n)
 2. Approach
        The idea is to divide problem into sub problems and then merge as a conquer step.
 */

public class DivideConquerSolution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return result;
        }

        int left = 0;
        int right = buildings.length - 1;
        return merge(
                extremePoints(buildings, left, left + (right-left)/2),
                extremePoints(buildings, left + (right-left)/2 + 1, right)
        );
    }

    private List<List<Integer>> extremePoints(int[][] buildings, int left, int right) {
        List<List<Integer>> result = new ArrayList<>();
        if (left == right) {
            result.add(Arrays.asList(buildings[left][0], buildings[left][2]));
            result.add(Arrays.asList(buildings[left][1], 0));
        } else if (left < right) {
            result = merge(
                    extremePoints(buildings, left, left + (right-left)/2),
                    extremePoints(buildings, left + (right-left)/2 + 1, right)
            );
        }

        return result;
    }

    private List<List<Integer>> merge(List<List<Integer>> first, List<List<Integer>> second) {
        List<List<Integer>> result = new ArrayList<>();

        int yMax = 0, curY = 0, leftY = 0, rightY = 0;
        int left = 0, right = 0;
        while (left < first.size() && right < second.size()) {
            List<Integer> leftPoint = first.get(left);
            List<Integer> rightPoint = second.get(right);
            int x = 0;
            if (leftPoint.get(0) < rightPoint.get(0)) {
                x = leftPoint.get(0); // consider left list
                leftY = leftPoint.get(1);
                left++;
            } else {
                x = rightPoint.get(0); // consider right list
                rightY = rightPoint.get(1);
                right++;
            }

            // max height (i.e. y) between both skylines
            yMax = Math.max(leftY, rightY);

            // append only if y was changed from previous record
            if (curY != yMax && (result.size() == 0 || !result.get(result.size() - 1).get(1).equals(yMax))) {
                if (result.size() > 0 && result.get(result.size() - 1).get(0).equals(x)) {
                    result.remove(result.size() - 1);
                }
                result.add(Arrays.asList(x, yMax));
                curY = yMax;
            }
        }

        while (left < first.size()) {
            if (result.size() > 0 && result.get(result.size() - 1).get(0).equals(first.get(left).get(0))) {
                result.remove(result.size() - 1);
            }
            result.add(first.get(left));
            left++;
        }
        while (right < second.size()) {
            if (result.size() > 0 && result.get(result.size() - 1).get(0).equals(second.get(right).get(0))) {
                result.remove(result.size() - 1);
            }
            result.add(second.get(right));
            right++;
        }

        return result;
    }

    /**


     1. return merge(
         extremePoints(first half)
         extremePoints(second half)
     )

     2. extremePoints:
         if 0 -> return empty list
         if 1 -> return left top and right bottom
         else -> merge(
             extremePoints(first half)
             extremePoints(second half)
         )

     3. merge left and right lists:
         if any of lists empty -> return another one
         two pointers left and right, Y = 0
         while both of them < size:
             if left.x == right.x -> select greater Y and add to result, update Y
             if left.x < right.x -> add point if Y < left.y, increment left
             if right.x < left.x -> add point if Y < right.y, increment right
         add rest of left or right to the result

     */
}
