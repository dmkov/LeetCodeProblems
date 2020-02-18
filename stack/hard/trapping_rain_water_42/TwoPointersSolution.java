package stack.hard.trapping_rain_water_42;

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
    2.1 Using two pointers for right and left bounds, move to the smaller value in the array. Calculate the max value so far,
        and subtract the current value from the local maximum. The difference is the amount of water since the it is bounded
        by max values on sides.
 3. Implementation
    3.1 Check if the input array is valid
    3.2 Define left and right pointers. Calculate local max variable as min of them.
    3.3 While left is less or equals right pointer:
        3.3.1 If value pointed by left index is greater than right, check if right number is bigger than previous max.
        3.3.2 Add to the result difference between max and right number (it will be 0 on increasing level but has water on decreases)
        3.3.3 Move right pointer to the left.
        3.3.4 Check both numbers again and repeat everything for the left number if right amount is bigger
 */

class TwoPointersSolution {
    public int trap(int[] arr) {
        int result = 0;
        if (arr == null || arr.length <= 2) {
            return result;
        }

        int left = 0;
        int right = arr.length - 1;

        int max = Math.min(arr[left], arr[right]);
        while (left <= right) {
            if (arr[left] > arr[right]) {
                max = Math.max(max, arr[right]);
                result += max - arr[right];
                right--;
            } else {
                max = Math.max(max, arr[left]);
                result += max - arr[left];
                left++;
            }
        }

        return result;
    }
}
