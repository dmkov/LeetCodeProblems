package heap.hard.employee_free_time_759;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 759. Employee Free Time
 https://leetcode.com/problems/employee-free-time/

 We are given a list schedule of employees, which represents the working time for each employee.
 Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

 (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

 Example 1:
 Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 Output: [[3,4]]
 Explanation: There are a total of three employees, and all common
 free time intervals would be [-inf, 1], [3, 4], [10, inf].
 We discard any intervals that contain inf as they aren't finite.

 Example 2:
 Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 Output: [[5,6],[7,9]]

 --------

 1. Complexity
    1.1 Time Complexity is O(n logn) where is n - number of intervals
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 We could reduce our problem to the following: given a set of intervals, find all places where there are no intervals.
    2.2 For the solution we will put all points (start and end) to the array, sort them and iterate one by one.
        If it is start point - increase the balance counter, otherwise - decrease it. For every start point,
        check if counter is 0 at the moment and it is - get the interval with the previous point.
 3 Implementation
    3.1 Check if given array is not null or empty.
    3.2 Create a list with arrays for every start and end points in the intervals. First number is the value and
        the second is '1' for start point and '-1' for the end.
    3.3 Sort list with pointers.
    3.4 Set balance to '0' and start iterating over points:
        3.4.1 If current balance is '0' and we have a start point and (value - end) is greater than 0 - then add (end, value)
            as the result interval
        3.4.2 Set current value as end variable. Sum up balance with the type coefficient.
 */

class SweepLineSolution {
    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new LinkedList<>();
        if (schedule == null || schedule.size() == 0) {
            return result;
        }

        List<int[]> list = new LinkedList<>();
        for (List<Interval> employee : schedule) {
            for (Interval interval : employee) {
                list.add(new int[]{interval.start, 1});
                list.add(new int[]{interval.end, -1});
            }
        }
        Collections.sort(list, (a, b) -> a[0] - b[0]);

        int balance = 0;
        int end = Integer.MIN_VALUE;
        for (int[] point : list) {
            if (balance == 0 && end != Integer.MIN_VALUE
                    && point[1] == 1 && (long)(point[0] - end) > 0) {
                result.add(new Interval(end, point[0]));
            }
            end = point[0];
            balance += point[1];
        }

        return result;
    }
}
