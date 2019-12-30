package greedy.medium.meeting_rooms_ii_253;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 253. Meeting Rooms II
 https://leetcode.com/problems/meeting-rooms-ii/

 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

 Example 1:
 Input: [[0, 30],[5, 10],[15, 20]]
 Output: 2

 Example 2:
 Input: [[7,10],[2,4]]
 Output: 1

 --------

 1. Complexity
    1.1 Time Complexity is O(nlog) because of sorting
    1.2 Space Complexity is O(n) - sorting and separate list with ends
 2. Approach
    2.1 The idea is to sort intervals by start time and then find the max number of intersections.
 2.2 Implementation
    2.2.1 Check if intervals array is not empty, otherwise return 0
    2.2.2 Sort array by interval start time. Use comparator or implement own merge sorting algorithm.
    2.2.3 Create a linked list with end times (it can be priority queue to simplify removing). For every
        interval in the list remove all end times before the start, put current end to the linked list and
        check the size of the linked list. If it is larger than max rooms variable - update the last.
 */

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int minRooms = 0;
        if (intervals == null || intervals.length == 0) {
            return minRooms;
        }

        sort(intervals, 0, intervals.length - 1);

        // Arrays.sort(intervals, new Comparator<int[]>() {
        //     public int compare(int[] a, int[] b) {
        //         return a[0] - b[0];
        //     }
        // });

        // Arrays.sort(intervals, (a, b) -> a[0] < b[0] ? -1 : a[0] == b[0] ? 0 : 1);

        List<Integer> endTimes = new LinkedList<>();
        for (int[] interval: intervals) {
            removeEndTimes(endTimes, interval[0]);
            endTimes.add(interval[1]);
            minRooms = Math.max(minRooms, endTimes.size());
        }

        return minRooms;
    }

    private void removeEndTimes(List<Integer> endTimes, int target) {
        ListIterator<Integer> i = endTimes.listIterator();
        while (i.hasNext()) {
            Integer item = i.next();
            if (item <= target) {
                i.remove();
            }
        }
    }

    private void sort(int[][] intervals, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end-start)/2;
        sort(intervals, start, mid);
        sort(intervals, mid + 1, end);

        merge(intervals, start, mid, end);
    }

    private void merge(int[][] intervals, int start, int mid, int end) {
        int leftSize = mid - start + 1;
        int rightSize = end - mid;

        if (leftSize == 0 || rightSize == 0) {
            return;
        }

        int[][] left = new int[leftSize][];
        int[][] right = new int[rightSize][];
        System.arraycopy(intervals, start, left, 0, leftSize);
        System.arraycopy(intervals, mid + 1, right, 0, rightSize);

        int i = 0;
        int j = 0;

        while (i < leftSize && j < rightSize) {
            if (left[i][0] <= right[j][0]) {
                intervals[start] = left[i];
                i++;
            } else {
                intervals[start] = right[j];
                j++;
            }
            start++;
        }
        if (i < leftSize) {
            System.arraycopy(left, i, intervals, start, leftSize - i);
        }
        if (j < rightSize) {
            System.arraycopy(right, j, intervals, start, rightSize - j);
        }
    }
}
