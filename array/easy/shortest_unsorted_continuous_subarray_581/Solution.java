package array.easy.shortest_unsorted_continuous_subarray_581;

/**
 581. Shortest Unsorted Continuous Subarray
 https://leetcode.com/problems/shortest-unsorted-continuous-subarray/

 Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

 You need to find the shortest such subarray and output its length.

 Example 1:
 Input: [2, 6, 4, 8, 10, 9, 15]
 Output: 5

 Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

 Note:
 Then length of the input array is in range [1, 10,000].
 The input array may contain duplicates, so ascending order here means <=.

 --------

 1. Complexity
    1.1 Time Complexity is O(n) for two pointers and O(logn) for cloned array
    1.2 Space Complexity is O(1) for two pointers and O(n) for cloned array
 2. Approach
    2.1 The solution with two pointers is based on the idea of max/min elements in the unsorted interval. First we iterate
        array to get the first number sloping down (it will be left boundary). Then we iterate it in reverse order to get
        the right boundary. In found interval we need to define max and min elements and find there positions in the whole
        array. It will be final left and right borders.
    2.2 Another idea is based on cloning array, sorting it and then compare with the original one. The first and last not
        matching elements will be required right and left borders.
 2.2 Implementation
    2.2.1 If passed array is null or has one element - return 0
    2.2.2 Iterate array from 0 to end to find the first element lesser than previous. Repeat for the right bounder.
    2.2.3 In found interval iterate all elements and find max and min values
    2.2.4 Iterate array from the beginning and check where found max and min elements should be in the list.
 */

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 1) {
            return 0;
        }

        int left = 0;
        int right = -1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                left = i - 1;
                break;
            }
        }
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] < nums[i - 1]) {
                right = i;
                break;
            }
        }

        if (right >= left) {
            int min = nums[left];
            int max = nums[right];

            for (int i = left; i <= right; i++) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            }

            boolean leftFlag = false;
            for (int i = 0; i < nums.length; i++) {
                if (min < nums[i] && !leftFlag) {
                    left = i;
                    leftFlag = true;
                }
                if (max > nums[i]) {
                    right = i;
                }
            }
        }

        return right - left + 1;
    }


//    public int findUnsortedSubarray(int[] nums) {
//        if (nums == null || nums.length == 1) {
//            return 0;
//        }
//
//        int left = 0;
//        int right = -1;
//
//        int[] copy = nums.clone();
//        Arrays.sort(copy);
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != copy[i]) {
//                left = i;
//                break;
//            }
//        }
//        for (int i = left + 1; i < nums.length; i++) {
//            if (nums[i] != copy[i]) {
//                right = i;
//            }
//        }
//        return right - left + 1;
//    }
}
