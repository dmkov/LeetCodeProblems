package array.easy.contains_duplicate_217;

import java.util.HashSet;
import java.util.Set;

/**
 217. Contains Duplicate
 https://leetcode.com/problems/contains-duplicate/

 Given an array of integers, find if the array contains any duplicates.
 Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

 Example 1:
 Input: [1,2,3,1]
 Output: true

 Example 2:
 Input: [1,2,3,4]
 Output: false

 Example 3:
 Input: [1,1,1,3,3,4,3,2,4,2]
 Output: true

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The solution is based on storing values in the hashset.
    2.2 Another possible solution is to use sorting.
 2.2 Implementation
    2.2.1 Check if array is null or contains 0 or 1 element, return false if it is the case
    2.2.2 Iterate throw array and check if element is in hashset. If it is - return true, otherwise - store element
        for future
 */

public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }

        Set<Integer> check = new HashSet<>();
        for (int num: nums) {
            if (check.contains(num)) {
                return true;
            }
            check.add(num);
        }

        return false;
    }
}
