package stack.meduim.sum_of_subarray_minimums_907;

import java.util.Stack;

/**
 907. Sum of Subarray Minimums
 https://leetcode.com/problems/sum-of-subarray-minimums/

 Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.

 Since the answer may be large, return the answer modulo 10^9 + 7.

 Example 1:
 Input: [3,1,2,4]
 Output: 17
 Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.

 Note:
 1 <= A.length <= 30000
 1 <= A[i] <= 30000

---
 https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Use two stacks to find a smaller number before and after the current one. The answer is product of numbers of elements
        between the current index and previous/next smaller items.
 3. Implementation
    3.1 Check if the input array is valid
    3.2 Create both stacks and result array to save sums for every index
    3.3 Iterate all elements in the given array:
        3.3.1 Create monotone stack for smaller items. Pop everything bigger than the current item and then compare current index
            with the one in the stack.
        3.3.2 Use another monotone stack to count items after the current element. The trick is that we put in stack all items
            and then if we have smaller item (the next one), we will update all indexes in the stack popping them out. Also
            stack should be processed at with the last element in the list.
    3.4 Do not forget about modulo and summing up the result.
 */

class OnePassSolution {
    public int sumSubarrayMins(int[] arr) {
        int res = 0;
        if (arr == null || arr.length == 0) {
            return res;
        }

        int mod = 1000000000 + 7;

        Stack<Integer> before = new Stack<>();
        Stack<Integer> after = new Stack<>();
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (before.size() > 0 && arr[before.peek()] > arr[i]) {
                before.pop();
            }
            if (before.size() > 0) {
                result[i] = arr[i] * (i - before.peek()) % mod;
            } else {
                result[i] = arr[i] * (i + 1) % mod;
            }
            before.push(i);

            while (after.size() > 0 && (arr[after.peek()] > arr[i] || i == arr.length - 1)) {
                if (arr[after.peek()] > arr[i]) {
                    result[after.peek()] *= (i - after.pop()) % mod;
                } else {
                    result[after.peek()] *= (i - after.pop() + 1) % mod;
                }
            }
            after.push(i);
        }

        for (int val : result) {
            res = (res + val) % mod;
        }

        return res;
    }
}
