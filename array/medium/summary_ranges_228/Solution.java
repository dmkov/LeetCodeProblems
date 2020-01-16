package array.medium.summary_ranges_228;

import java.util.LinkedList;
import java.util.List;

/**
 228. Summary Ranges
 https://leetcode.com/problems/summary-ranges/

 Given a sorted integer array without duplicates, return the summary of its ranges.

 Example 1:
 Input:  [0,1,2,4,5,7]
 Output: ["0->2","4->5","7"]
 Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.

 Example 2:
 Input:  [0,2,3,4,6,8,9]
 Output: ["0","2->4","6","8->9"]
 Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Use two pointers to track the start element in the range and the current one.
 2.2 Implementation
    2.2.1 Check if input array is valid\
    2.2.2 Point start variable to the first item and start iterating from the second one.
    2.2.3 In every cycle compare current element to the previous item. If their difference is 1 - proceed, otherwise
        create a record in the result list and update the start point to the current one.
 */

public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int start = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            if (nums[i - 1] + 1 != nums[i]) {
                result.add(formatStr(start, nums[i - 1]));
                start = nums[i];
            }
        }
        result.add(formatStr(start, nums[length - 1]));

        return result;
    }

    private String formatStr(int start, int prev) {
        String result = String.valueOf(start);
        if (start != prev) {
            result = String.format("%s->%s", start, prev);
        }

        return result;
    }
}
