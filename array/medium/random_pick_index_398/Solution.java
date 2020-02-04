package array.medium.random_pick_index_398;

/**
 398. Random Pick Index
 https://leetcode.com/problems/random-pick-index/

 Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

 Note:
 The array size can be very large. Solution that uses too much extra space will not pass the judge.

 Example:
 int[] nums = new int[] {1,2,3,3,3};
 Solution solution = new Solution(nums);
 // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 solution.pick(3);
 // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 solution.pick(1);

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    https://www.youtube.com/watch?v=A1iwzSew5QY
    2.1 To get elements we iterate array from the start to end. The question is how to guarantee probability.
    2.2 For the every next occurrence we will add range for random() function. So starting from 1 for the first element,
        1/2 for the second, 1/3 for the third and so on.
 3 Implementation
    3.1 Assign nums[] array to the object property in the constructor
    3.2 With every iteration check all numbers and use occur variable to increment counter of every new occurrence.
        If target element is found check that (Math.random() * occur) == 0 to reassign the value
 */

public class Solution {
    int[] nums;

    public Solution(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        int result = 0;
        int occur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                occur++;
                result = ((int)(Math.random() * occur) == 0) ? i : result;
            }
        }

        return result;
    }
}
