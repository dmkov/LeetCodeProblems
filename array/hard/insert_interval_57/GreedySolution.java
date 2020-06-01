package array.hard.insert_interval_57;

import java.util.ArrayList;
import java.util.List;

/**
 57. Insert Interval
 https://leetcode.com/problems/insert-interval/

 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:
 Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 Output: [[1,5],[6,9]]

 Example 2:
 Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 Output: [[1,2],[3,10],[12,16]]
 Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
    2.1 Add all elements before the interval
    2.2 Merge all overlapped intervals into given one
    2.2 At the end, add a new (or merged) interval to the result and append all following intervals
 */

class GreedySolution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0 || newInterval == null || newInterval.length == 0) {
            return new int[][]{newInterval};
        }

        List<int[]> res = new ArrayList<>();

        for (int[] i : intervals) {
            if (newInterval == null || newInterval[1] < i[0]) {
                if (newInterval != null) {
                    res.add(newInterval);
                    newInterval = null;
                }
                res.add(i);
            } else if (i[1] < newInterval[0]) {
                res.add(i);
            } else {
                newInterval[0] = Math.min(newInterval[0], i[0]);
                newInterval[1] = Math.max(newInterval[1], i[1]);
            }

        }
        if (newInterval != null) {
            res.add(newInterval);
        }

        return res.toArray(new int[res.size()][]);
    }
}

/**
     1. What is the input format of intervals? Is it list of arrays or arrays of arrays?
     2. What is the output format?
     3. And all intervals are sorted by start time? If start times are the same what is the order of end times?
     4. We are given a array of two numbers for the new interval?
     5. What is the possible numbers for intervals? is it integers greater than zero? what is the max value?
     6. Could we have not-merged intervals in the input list? [1,3],[2,4],[2,5]

     [-1,0],[1,3],[3,4],[5,7],[9,10]
     [2,6]
     [-1,0],[1,7],[9,10]

     --  --- -- --   --
          -------

 */
