package array.medium.next_permutation_31;

/**
 31. Next Permutation
 https://leetcode.com/problems/next-permutation/

 Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 The replacement must be in-place and use only constant extra memory.

 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Think about array with duplicates as of linked list with inner cycle. Use approach with slow and faster pointers
        to find the cycle and enter point to it
 2.2 Implementation
    2.2.1 Check if input array is valid (should have at least two different elements)
    2.2.2 First, we need to find an element smaller than it's right neighbor (if all elements are sorted from bigger to smaller
        items the permutation is not possible). It will be 'i' pointer.
    2.2.3 If 'i' was found the next search is the element bigger than 'i' from the right. Find it, swap it with 'i'.
    2.2.3 The remaining part is reverse everything on the right of 'i' to sort it in ascending order (it is in desc order now
        and the next permutation is the asc order of numbers).
 */

public class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
