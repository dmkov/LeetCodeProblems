package stack.hard.trapping_rain_water_42;

import java.util.Stack;

/**
 42. Trapping Rain Water
 https://leetcode.com/problems/trapping-rain-water/

 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

 The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 In this case, 6 units of rain water (blue section) are being trapped.

 Example:
 Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Moving from left to right we increase level of earth or leave the previous value. The same we repeat from right to the left.
        As the result in every point we can get level of right or left bank. The min of them is the water level. Subtract
        actual level of the earth in this point and you will get the amount of water.
 3. Implementation
    3.1 Check if the input array is valid
    3.2 Moving from left to right get the max point comparing the current level of earth and the previous value.
    3.3 Repeat the same moving from right to the left
    3.4 Do another iteration, for every point find the min value from left and right and subtract value from the given array.
        Sum up it to the result value.
 */

class DPSolution {
    public int trap(int[] arr) {
        int result = 0;
        if (arr == null || arr.length == 0) {
            return result;
        }

        int[] left = new int[arr.length];
        int[] right = new int[arr.length];

        int prev = 0;
        for (int i = 0; i < arr.length; i++) {
            prev = Math.max(prev, arr[i]);
            left[i] = prev;
        }
        prev = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            prev = Math.max(prev, arr[i]);
            right[i] = prev;
        }

        for (int i = 0; i < arr.length; i++) {
            result += Math.min(right[i], left[i]) - arr[i];
        }

        return result;
    }
}
