package array.hard.longest_consecutive_sequence_128;

import java.util.HashSet;
import java.util.Set;

/**
 128. Longest Consecutive Sequence
 https://leetcode.com/problems/longest-consecutive-sequence/

 Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 Your algorithm should run in O(n) complexity.

 Example:
 Input: [100, 4, 200, 1, 3, 2]
 Output: 4
 Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
    2.1 Use set to get the first number in the subsequence. After that iterate while subsequence
        is presented. Count number of elements
 */

class HashSetSolution {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (Integer num : set) {
            if (!set.contains(num - 1)) {
                int count = 1;
                while (set.contains(num+1)) {
                    num = num + 1;
                    count++;
                }
                max = Math.max(max, count);
            }
        }

        return max;
    }
}

/**
     1. Naive approach: Sort and interate numbers
        O(nlogn)

     2. Use hashmap to track boundaries

         - Use hashmap with arrays where:
             0: leftBoundary
             1: rightBoundary
         - Iterate over the list:
             - for every number get the "-1" value
                 - if it is null - set left boundary to itself
                 - otherwise - set "-1" left boundary to current left boundary
             - get "+1" value
                 - if null -> set left boundary to itself
                 - otherwise - set "+1" right boundary to the current right boundary
             if curr left boundary != number -> set left boundary . right the current right value
             if curr right boundary != number -> set right boundary . left the current left value
             get the max as (right - left + 1)


     [100:[100:100], 4:[3:4], 200:[200:200], 1:[1:1], 3:[3:4], 2:[1:4]]


     [-1,9,-3,-6,7,-8,-6,2,9,2,3,-2,4,-1,0,6,1,-9,6,8,6,5,2]

     3. Use set to get the first number in the subsequence.
        After that iterate while subsequence is presented. Count number of elements

 */
