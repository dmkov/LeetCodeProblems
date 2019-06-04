package search.easy.search_insert_position_35;

/**
 35. Search Insert Position
 https://leetcode.com/problems/search-insert-position/

 Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

 You may assume no duplicates in the array.

 Example 1:
 Input: [1,3,5,6], 5
 Output: 2

 Example 2:
 Input: [1,3,5,6], 2
 Output: 1

 Example 3:
 Input: [1,3,5,6], 7
 Output: 4

 Example 4:
 Input: [1,3,5,6], 0
 Output: 0

---

 1. Complexity
    1.1 Time Complexity is O(log n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on binary search
    2.2 Define two pointer left and right where range is in 0 and nums.length-1
    2.3 Get the middle left + (right - left) / 2
    2.4 Check the mid value and shift bounds depending on response:
        2.4.1 nums[mid] > target: right = mid - 1
        2.4.2 nums[mid] < target: left = mid + 1, result = mid + 1
    2.5 Repeat from 2.3 (while left <= right && nums[result] != target loop)

 */

class Solution {
    public int searchInsert(int[] nums, int target) {
        int result = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right && nums[result] != target) {
            int mid = left + (right - left) / 2;
            result = mid;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
                result = mid + 1;
            }
        }
        return result;
    }
}
