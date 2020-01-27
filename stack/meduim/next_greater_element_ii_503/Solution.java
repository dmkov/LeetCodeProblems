package stack.meduim.next_greater_element_ii_503;

import java.util.Arrays;
import java.util.List;
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
     3.2 Iterate from first element to the end of the list and compare current item with the next one (for the last element -
        the next will be with the '0' index).
     3.3 If current element is bigger or equal to the next one - store it's index to the stack and update the result cell to '-1'
     3.4 If current element is less than the next one, then update current result cell with the value from the next one and
        start popping all smaller elements from stack and update them with the bigger value.
     3.5 At the end - iterate one more time and check if there are bigger elements than in stack.
 */

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int length = nums.length;
        int[] result = new int[length];

        Stack<Integer> stack = new Stack<>(); //   3
        for (int i = 0; i < length; i++) {
            int next = (i + 1 < length) ? i + 1 : 0;
            if (nums[i] < nums[next]) {
                result[i] = nums[next];
                while (stack.size() > 0 && nums[stack.peek()] < nums[next]) {
                    result[stack.pop()] = nums[next];
                }
            } else {
                result[i] = -1;
                stack.push(i);
            }
        }

        for (int i = 0; i < length; i++) {
            while (stack.size() > 0 && nums[stack.peek()] < nums[i]) {
                result[stack.pop()] = nums[i];
            }
        }

        return result;
    }

//    public int[] nextGreaterElements(int[] A) {
//        int n = A.length, res[] = new int[n];
//        Arrays.fill(res, -1);
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < n * 2; i++) {
//            while (!stack.isEmpty() && A[stack.peek()] < A[i % n])
//                res[stack.pop()] = A[i % n];
//            stack.push(i % n);
//        }
//        return res;
//    }
}
