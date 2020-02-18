package stack.hard.largest_rectangle_in_histogram_84;

import java.util.Stack;

/**
 84. Largest Rectangle in Histogram
 https://leetcode.com/problems/largest-rectangle-in-histogram/

 Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

 Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 The largest rectangle is shown in the shaded area, which has area = 10 unit.

 Example:
 Input: [2,1,5,6,2,3]
 Output: 10

---
 Example:
 https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Use two stacks to find a smaller number before and after the current one. After having this distance we can calculate
        rectangle as a product of height * distance.
 3. Implementation
    3.1 Check if the input array is valid
    3.2 Create both stacks and result array to save distance for every index
    3.3 Iterate all elements in the given array:
        3.3.1 Create monotone stack for smaller items. Pop everything bigger or equal to the current item and then compare current index
            with the one in the stack. Add left distance to the result array.
        3.3.2 Use another monotone stack to count items after the current element. The trick is that we put in stack all items
            and then if we have smaller item (the next one), we will update all indexes in the stack popping them out. Also
            stack should be processed at with the last element in the list. Store right distance to the result.
    3.4 After the loop process remaining items from the after stack.
    3.4 Go through result array, multiple height * distance and get the max number from the list.
 */

class StackSolution {
    public int largestRectangleArea(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] result = new int[arr.length];
        Stack<Integer> before = new Stack<>();
        Stack<Integer> after = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (before.size() > 0 && arr[before.peek()] >= arr[i]) {
                before.pop();
            }
            result[i] = (before.size() > 0) ? (i - before.peek()) : (i + 1);
            before.push(i);

            while (after.size() > 0 && arr[after.peek()] > arr[i]) {
                int index = after.pop();
                result[index] += i - index - 1;
            }
            after.push(i);
        }
        while (after.size() > 0) {
            int index = after.pop();
            result[index] += result.length - index - 1;
        }

        int max = 0;
        for (int i = 0; i < result.length; i++) {
            max = Math.max(max, result[i] * arr[i]);
        }

        return max;
    }
}
