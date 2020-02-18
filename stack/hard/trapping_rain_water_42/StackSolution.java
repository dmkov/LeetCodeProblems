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
    2.1 Use stack to store values. If peek() element is smaller than the current one (increasing trend), pop() item and store it is
        as a bottom line. If there is anything in the stack (meaning we have upper bound) take the min value between the current one and
        peak() element and count difference with the bottom line. In such way we slice all holes.
 3. Implementation
    3.1 Check if the input array is valid
    3.2 Define stack object and start iterating elements.
    3.3 If previous item in the stack is smaller than the current element:
        3.3.1 Pop the last item from stack and save it to the bottom variable.
        3.3.2 It there is any other element in the stack (it will be bigger than our bottom line automatically),
            peak it and compare with the current one.
        3.3.3 Take the smaller element and subtract bottom line from it. It will be our depth
        3.3.4 Calculate distance as difference between current index index in the stack (reduce it for 1, since
            we store the high bound but actual water starts after it).
        3.3.5 Add to the result product of the current depth and distance.
 */

class StackSolution {
    public int trap(int[] arr) {
        int result = 0;
        if (arr == null || arr.length == 0) {
            return result;
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (stack.size() > 0 && arr[stack.peek()] < arr[i]){
                int bottom = arr[stack.pop()];
                if (stack.size() > 0) {
                    int distance = i - stack.peek() - 1;
                    int depth = Math.min(arr[stack.peek()], arr[i]) - bottom;
                    result += distance * depth;
                }
            }

            stack.push(i);
        }

        return result;
    }
}
