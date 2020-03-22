package stack.hard.maximal_rectangle_85;

import java.util.Stack;

/**
 85. Maximal Rectangle
 https://leetcode.com/problems/maximal-rectangle/

 Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 Example:
 Input:
     [
         ["1","0","1","0","0"],
         ["1","0","1","1","1"],
         ["1","1","1","1","1"],
         ["1","0","0","1","0"]
     ]
 Output: 6

---
 
 Example:
 https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step

 1. Complexity
    1.1 Time Complexity is O(n*m)
    1.2 Space Complexity is O(n)
 2. Approach
    2.2 The idea is to calculate height of '1's in every row and then try to find maximum rectangle in the histogram
        using two stacks.
    2.1 Use two stacks to find a smaller number before and after the current one. After having this distance we can calculate
        rectangle as a product of height * distance.
 3. Implementation
    3.1 Check if the input array is valid
    3.2 Set empty height array. Then , for every row:
        3.2.1 Iterate all columns
        3.2.2 If element is '1' - increase the height array column (it means we have continuous '1'), otherwise - set it to 0.
    3.2 Calculate max rectangle for the heights in the row:
        3.2.1 Create both stacks and result array to save distance for every index
            3.2.1 Iterate all elements in the given array:
                3.2.1.1 Create monotone stack for smaller items. Pop everything bigger or equal to the current item and
                    then compare current index with the one in the stack. Add left distance to the result array.
                3.2.1.2 Use another monotone stack to count items after the current element. The trick is that we put
                    in stack all items and then if we have smaller item (the next one), we will update all indexes
                    in the stack popping them out. Also stack should be processed at with the last element in the list.
                    Store right distance to the result.
            3.2.2 After the loop process remaining items from the after stack.
        3.2.2 Go through result array, multiple height * distance and get the max number from the list.
    3.3 Return max value as the result of the method.
 */

class StackSolution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int max = 0;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            max = Math.max(max, maxRectangleInHistogram(height));
        }

        return max;
    }

    // 1, 2, 2
    private int maxRectangleInHistogram(int[] arr) {
        int[] sum = new int[arr.length]; //[3, 4, 4]
        int max = 0;
        Stack<Integer> pre = new Stack<>(); // 0,
        Stack<Integer> post = new Stack<>(); // 0, 1

        for (int i = 0; i < arr.length; i++) {
            while (pre.size() > 0 && arr[pre.peek()] >= arr[i]) {
                pre.pop();
            }
            int k = -1;
            if (pre.size() > 0) {
                k = pre.peek();
            }
            sum[i] += arr[i] * (i - k);
            pre.push(i);

            while (post.size() > 0 && arr[post.peek()] > arr[i]) {
                int j = post.pop();
                sum[j] += arr[j] * (i - j - 1);
                max = Math.max(sum[j], max);
            }
            post.push(i);
        }
        while (post.size() > 0) {
            int j = post.pop();
            sum[j] += arr[j] * (arr.length - j - 1);
            max = Math.max(sum[j], max);
        }

        return max;
    }
}
