package array.easy.find_all_numbers_disappeared_in_an_array_448;

import java.util.LinkedList;
import java.util.List;

/**
 448. Find All Numbers Disappeared in an Array
 https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

 Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 Find all the elements of [1, n] inclusive that do not appear in this array.

 Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

 Example:
 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [5,6]

 ------------------------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(1)
 2. Approach
     2.1 This solution is based on the idea to use indexes of the array as markers of existing numbers
     2.2 Implementation
         2.2.1 Check if input is null or empty
         2.2.2 Iterate the list, invert items with indexes (num-1)
         2.2.3 Check the list one more time. If number is greater than 0, it means it was not inverted and
            its index is missing in the list
 */

public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        for (int num: nums) {
            int i = Math.abs(num) - 1;
            if (nums[i] > 0) {
                nums[i] = -nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
