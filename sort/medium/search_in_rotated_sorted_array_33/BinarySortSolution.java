package sort.medium.search_in_rotated_sorted_array_33;

/**
 33. Search in Rotated Sorted Array
 https://leetcode.com/problems/search-in-rotated-sorted-array/

 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

 You are given a target value to search. If found in the array return its index, otherwise return -1.
 You may assume no duplicate exists in the array.
 Your algorithm's runtime complexity must be in the order of O(log n).

 Example 1:
 Input: nums = [4,5,6,7,0,1,2], target = 0
 Output: 4

 Example 2:
 Input: nums = [4,5,6,7,0,1,2], target = 3
 Output: -1

---

 1. Complexity
    1.1 Time Complexity is O(nlogn)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is to use binary search with additional conditions if rotation exists in the search range.
 3. Implementation
    3.1 Check if the input array is valid
    3.2 While left pointer is less or equals right:
        3.2.1 Calculate the middle pointer
        3.2.2 If middle equals target - return the index.
        3.2.3 Otherwise, check if start pointer is less than end. In such case we have sorted array
            without rotation  - so do a regular binary search.
        3.2.4 If middle item is bigger than the first element - we are in the first, bigger half of the list.
            Check if target item is bigger than first element and then go left or right accordingly.
        3.2.5 If middle item is less than the first item - we are in the smaller, second half.
            Check that the target item is the bigger than the first element and go left or right accordingly.
 */

class BinarySortSolution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[left] < nums[right]) {
                if (target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] >= nums[0]) {
                if (target > nums[mid] || target < nums[0]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target > nums[mid] && target < nums[0]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
