package array.medium.find_the_duplicate_number_287;

/**
 287. Find the Duplicate Number
 https://leetcode.com/problems/find-the-duplicate-number/

 Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Example 1:
 Input: [1,3,4,2,2]
 Output: 2

 Example 2:
 Input: [3,1,3,4,2]
 Output: 3

 Note:
 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Think about array with duplicates as of linked list with inner cycle. Use approach with slow and faster pointers
        to find the cycle and enter point to it
 2.2 Implementation
    2.2.1 Assign both pointers to the first element.
    2.2.2 Until elements are equal pass one pointer for 1 step, another one - for two.
    2.2.3 Then assign one pointer two the first element again and start iterating by one step both pointers. When
        elements are equal, it means that we found a start point to the cycle.
 */

public class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int slow = nums[0];
        int fast = nums[0];
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) break;
        }

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findDuplicate(new int[]{1,3,4,2,2});
    }
}
