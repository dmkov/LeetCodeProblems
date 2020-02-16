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
    2.1 Let's say nums looks like this: [4, 5, 6, 7, 0, 1, 2, 3]
        Because it's not fully sorted, we can't do normal binary search. But here comes the trick:
        If target is let's say 4, then we adjust nums to this, where "inf" means infinity: [4, 5, 6, 7, inf, inf, inf, inf]
        If target is let's say 3, then we adjust nums to this: [-inf, -inf, -inf, -inf, 0, 1, 2, 3]

 And then we can simply do ordinary binary search.
 3. Implementation
    3.1 Check if the input array is valid
    3.2 While left pointer is less or equals right:
        3.2.1 Calculate the middle pointer
        3.2.2 If middle equals target - return the index.
        3.2.3 Check if start pointer is less than end. In such case we have sorted array
            without rotation - so do a regular binary search without transformation.
        3.2.4 Otherwise, if target is in the second half but middle element is in the first (bigger) half - assign
            Integer.MIN_VALUE to the middle element.
        3.2.5 If target is in the first half but we are in the second (smaller) half - assign Integer.MAX_VALUE
            to the middle element. In this way we can continue regular binary search.
 */

class SimpleMinMaxSolution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int num = nums[mid];

            if (num == target) {
                return mid;
            }
            if (nums[left] > nums[right]) {
                if (num >= nums[0] && target < nums[0]) {
                    num = Integer.MIN_VALUE;
                } else if (num < nums[0] && target >= nums[0]) {
                    num = Integer.MAX_VALUE;
                }
            }

            if (target > num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
