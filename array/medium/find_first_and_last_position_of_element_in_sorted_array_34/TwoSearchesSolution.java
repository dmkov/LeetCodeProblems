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
    2.2.2 Do two binary searches to find left and right bounds.
    2.2.4 Having two independent searches reduces the code but at same time we need to check entire array every time.
        It can be improved: do not do the second search if value was not found in the first one. Or use first result as
        a bound for the second one.
 */

public class TwoSearchesSolution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private int findFirst(int[] nums, int target){
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(nums[mid] >= target){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
            if(nums[mid] == target) idx = mid;
        }
        return idx;
    }

    private int findLast(int[] nums, int target){
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(nums[mid] <= target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
            if(nums[mid] == target) idx = mid;
        }
        return idx;
    }
}
