package array.medium.merge_intervals_56;

import java.util.Arrays;

/**
 56. Merge Intervals
 https://leetcode.com/problems/merge-intervals/

 Given a collection of intervals, merge all overlapping intervals.

 Example 1:
 Input: [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

 Example 2:
 Input: [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considered overlapping.

 --------

 1. Complexity
    1.1 Time Complexity is O(nlogn) because of sorting
    1.2 Space Complexity is O(n) because of copyOfRange()
 2. Approach
    2.1 First we sort all intervals and then increase the end point if the next start if less than current end.
 2.2 Implementation
    2.2.1 Check if input array is valid
    2.2.2 Sort array using start points in a comparator
    2.2.3 Using two pointers 'j' and 'i' iterate the array. 'j' will point to the element that should be updated,
        'i' will be the pointer in the loop. Store the start and end pointers before iterating the array.
    2.2.4 If current start is greater than stored end, we need to create an element at position 'j' with the start and
        end values (no crossing with the current element). Otherwise, update the end to the max! of both points and continue
        iterating. Do not forget to create the last element after the loop.
 */

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> { return a[0] - b[0]; });

        int j = 0;
        int start = intervals[j][0];
        int end = intervals[j][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                intervals[j] = new int[]{start, end};
                start = intervals[i][0];
                j++;
            }
            end = Math.max(end, intervals[i][1]);
        }
        intervals[j] = new int[]{start, end};

        return Arrays.copyOfRange(intervals, 0, j + 1);
    }
}
