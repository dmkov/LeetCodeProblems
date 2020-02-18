package stack.hard.largest_rectangle_in_histogram_84;

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
    1.1 Time Complexity is O(nlogn) or O(n^2) if array is sorted
    1.2 Space Complexity is O(n) because of recursion
 2. Approach
    2.1 The idea is to get the smallest bar on the range, calculate its rectangle and than compare with
        left and right results recursively.
 3. Implementation
    3.1 Check if the input array is valid and call recursive method for the whole range.
    3.2 In the method check left and right pointers. If they are equal - return value as the result, if left is bigger than
        right - return 0.
    3.3 Iterate all elements and get the smallest index (by value). Calculate rectangle as (right - left + 1) * value.
    3.4 Get the result as the max value of current rectangle, left and right ranges sub results.
 */

class DivideAndConquerSolution {
    public int largestRectangleArea(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return calculateArea(arr, 0, arr.length - 1);
    }

    private int calculateArea(int[] arr, int left, int right) {
        if (left > right) {
            return 0;
        } else if (left == right) {
            return arr[left];
        }

        int min = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] < arr[min]) {
                min = i;
            }
        }

        return Math.max((right - left + 1) * arr[min],
                Math.max(calculateArea(arr, left, min - 1),
                        calculateArea(arr, min + 1, right))
        );
    }
}
