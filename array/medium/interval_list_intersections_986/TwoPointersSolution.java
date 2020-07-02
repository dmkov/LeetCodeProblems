package array.medium.interval_list_intersections_986;

import java.util.ArrayList;
import java.util.List;

/**
 986. Interval List Intersections
 https://leetcode.com/problems/interval-list-intersections/

 Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

 Return the intersection of these two interval lists.

 (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Using two pointers consider the smallest interval from both lists and add intersections
 */

class TwoPointersSolution {
    public int[][] intervalIntersection(int[][] a, int[][] b) {
        List<int[]> result = new ArrayList<>();
        if (a == null || b == null) {
            return result.toArray(new int[0][0]);
        }

        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            int[] first = a[i];
            int[] second = b[j];
            if (first[1] < second[0]) {
                i++;
            } else if (second[1] < first[0]) {
                j++;
            } else {
                result.add(new int[]{
                    Math.max(first[0], second[0]),
                    Math.min(first[1], second[1])
                });
                if (first[1] >= second[1]) {
                    j++;
                } else {
                    i++;
                }
            }

        }

        return result.toArray(new int[result.size()][2]);
    }

    /**
     1. What is the input / output format?
     2. Could intervals be empty? If one starts when another ends?
     3. Is it array of integers?
     4. Both lists are sorted?
     5. Duplicates?


     [0,0][1,2][5,7]
     |
     [0,1][4,6][9,10]
     |

     [0,0][1,1],[5,6]

     - if first ends before the second starts - move first pointer
     - if second ends before the first starts - move second pointer
     - otherwise - add interval from max(first or second start) to the min(first or second end)
     - move pointer for min(first or second end)

     */

}
