package heap.hard.employee_free_time_759;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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
    1.1 Time Complexity is O(n logk) where is n - number of intervals and k is the number of employees
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Push all intervals to the priority queue to sort and after that pop them one by one to compare start
        and previous end times to get the empty intervals.
 3 Implementation
    3.1 Check if given array is not null or empty.
    3.2 Create PriorityQueue with comparator on start time. Populate it with all interval from all employees.
    3.3 While pq is not empty, pop the element and if the difference between prev end and new start interval > 0 -
        store it as a result interval. Update end time as the max of the current end and previous value.
 */

class MinHeapSolution {
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

        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        for (List<Interval> employee : schedule) {
            for (Interval interval : employee) {
                pq.add(interval);
            }
        }

        int end = Integer.MIN_VALUE;
        while (pq.size() > 0) {
            Interval interval = pq.poll();
            if (end != Integer.MIN_VALUE && (long)(interval.start - end) > 0) { // overflow
                result.add(new Interval(end, interval.start));
            }
            end = Math.max(end, interval.end);
        }

        return result;
    }
}
