package stack.meduim.next_greater_element_ii_503;

import java.util.Arrays;
import java.util.Stack;

/**
 503. Next Greater Element II
 https://leetcode.com/problems/next-greater-element-ii/

 Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

 Example 1:
 Input: [1,2,1]
 Output: [2,-1,2]
 Explanation: The first 1's next greater number is 2;
 The number 2 can't find next greater number;
 The second 1's next greater number needs to search circularly, which is also 2.
 Note: The length of given array won't exceed 10000.

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is to use stack to push indexes of items less than the next one. After finding the bigger element,
        we also check everything from the stack.
 3. Implementation
     3.1 Check if input array is valid
     3.2 Put first element to the stack and when stack is not empty, compare it is peek() with the current (second) element.
     3.3 If previous element is smaller than the current one - pop() index and update the result cell with the value.
     3.4 Push current index to the stack.
     3.5 We need iterate list twice, to do it use n * 2 in the condition and i % n in the indexes.
 */

class BetterStackSolution {

    public int[] nextGreaterElements(int[] A) {
        if (A == null || A.length == 0) {
            return new int[0];
        }

        int n = A.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            while (!stack.isEmpty() && A[stack.peek()] < A[i % n])
                res[stack.pop()] = A[i % n];
            stack.push(i % n);
        }

        return res;
    }
}
