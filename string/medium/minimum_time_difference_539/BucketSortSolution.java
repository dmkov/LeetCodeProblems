package string.medium.minimum_time_difference_539;

import java.util.List;

/**
 539. Minimum Time Difference
 https://leetcode.com/problems/minimum-time-difference/

 Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.

 Example 1:
 Input: ["23:59","00:00"]
 Output: 1

 Note:
 The number of time points in the given list is at least 2 and won't exceed 20000.
 The input time is legal and ranges from 00:00 to 23:59.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(24*60)
 2. Approach
    2.1 The idea is to convert all times to int numbers and then use bucket sort since there are
        only 24*60 possible combinations.
 2.2 Implementation
    2.2.1 Check if input list is valid. Check if there are more than 24*60 elements. If so - return 0 since there are
        duplicates and diff for them will be 0.
    2.2.2 Because of 24*60 max values we can use bucket sort (also can be done with the regular sorting but then we will
        have nlogn time complexity). Create a 24*60 boolean array.
    2.2.3 Convert every string to integer number of minutes. Set boolean element with the given index to true.
        If it is already true - return 0 (duplicates).
    2.2.4 Start iterating from 0 to 24*60 and check boolean array with marked minutes. Calculate the difference
        between elements and get the min value.
    2.2.5 Do not forget to calculate corner case - difference between last and first elements.
 */

class BucketSortSolution {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints == null || timePoints.size() == 0 || timePoints.size() > 24*60) {
            return 0;
        }

        boolean[] mins = new boolean[24*60];
        for (int i = 0; i < timePoints.size(); i++) {
            int m = getMinutes(timePoints.get(i));
            if (mins[m]) {
                return 0;
            } else {
                mins[m] = true;
            }
        }

        int start = -1;
        int first = -1;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < 24*60; i++) {
            if (mins[i]) {
                if (first == -1) {
                    first = i;
                    start = i;
                    continue;
                }
                diff = Math.min(diff, i - first);
                first = i;
            }
        }
        diff = Math.min(diff, start + (24*60 - first));
        return diff;
    }

    private int getMinutes(String str) {
        String[] s = str.split(":");
        return Integer.valueOf(s[0]) * 60 + Integer.valueOf(s[1]);
    }
}
