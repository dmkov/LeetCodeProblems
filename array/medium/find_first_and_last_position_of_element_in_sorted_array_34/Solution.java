package array.medium.find_first_and_last_position_of_element_in_sorted_array_34;

/**
 34. Find First and Last Position of Element in Sorted Array
 https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

 Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 Your algorithm's runtime complexity must be in the order of O(log n).

 If the target is not found in the array, return [-1, -1].

 Example 1:
 Input: nums = [5,7,7,8,8,10], target = 8
 Output: [3,4]

 Example 2:
 Input: nums = [5,7,7,8,8,10], target = 6
 Output: [-1,-1]

 --------

 1. Complexity
    1.1 Time Complexity is O(log n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 First if find if number exists and then using binary search find left and right bounds
 2.2 Implementation
    2.2.1 Check if input array is valid.
    2.2.2 Create a 'i' pointer for the target in the array. Do a binary search to find the first occurrence.
    2.2.3 If 'i' was found, do a binary search to find the left bound and another search to find right bound.
        Use 'i' as a start/end points in these searches.
    2.2.4 Instead of having 3 searches, it can be done in two iterations (check another solution)
 */

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }

        int left = 0;
        int right = nums.length - 1;
        int i = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                i = mid;
                break;
            }
        }

        if (i != -1) {
            left = 0;
            right = i - 1;
            result[0] = i;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    right = mid - 1;
                    result[0] = mid;
                } else {
                    left = mid + 1;
                }
            }

            left = i + 1;
            right = nums.length - 1;
            result[1] = i;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    left = mid + 1;
                    result[1] = mid;
                } else {
                    right = mid - 1;
                }
            }
        }

        return result;
    }
}
