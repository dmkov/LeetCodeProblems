package array.easy.missing_number_268;

/**
 268. Missing Number
 https://leetcode.com/problems/missing-number/

 Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

 Example 1:
 Input: [3,0,1]
 Output: 2

 Example 2:
 Input: [9,6,4,2,3,5,7,0,1]
 Output: 8

 Note:
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The solution is based on the idea of additional array to use its indexes to track numbers. Because we know that
        a given list of numbers is distinct anf in a range from 0 to n, we can iterate it and mark found numbers by
        indexes in the second list
    2.2 Another interesting approach is based on the Gauss formula or Arithmetic progression or manual sum calculation.
        We define the sum of all elements from 0 to N, then subtract from it sum of given numbers and get a missing one.
 2.2 Implementation
    2.2.1 Add a new array with length (nums.length + 1). Iterate through nums and update numbers in the new array.
        Then do another loop and check what index has a default value. It will be the answer.
    2.2.2
 */

public class Solution {
    public int missingNumber(int[] nums) {
        int[] checklist = new int[nums.length + 1];
        int result = -1;
        for (int num: nums) {
            checklist[num] = 1;
        }
        for (int i = 0; i < checklist.length; i++) {
            if (checklist[i] != 1) {
                result = i;
                break;
            }
        }
        return result;
    }

//    public int missingNumber(int[] nums) {
//        int expectedSum = nums.length*(nums.length + 1)/2; // possible overflow here if N is large
//        int actualSum = 0;
//        for (int num : nums) actualSum += num;
//        return expectedSum - actualSum;
//    }
}
