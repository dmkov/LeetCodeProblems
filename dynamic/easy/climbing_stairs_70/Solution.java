package dynamic.easy.climbing_stairs_70;

/**
 70. Climbing Stairs
 https://leetcode.com/problems/climbing-stairs/

 You are climbing a stair case. It takes n steps to reach to the top.
 Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 Note: Given n will be a positive integer.

 Example 1:
 Input: 2
 Output: 2
 Explanation: There are two ways to climb to the top.
 1. 1 step + 1 step
 2. 2 steps

 Example 2:
 Input: 3
 Output: 3
 Explanation: There are three ways to climb to the top.
 1. 1 step + 1 step + 1 step
 2. 1 step + 2 steps
 3. 2 steps + 1 step

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n) because of additional hash array
 2. Approach
    2.1 If we check pattern then will find that steps(n) = steps(n-1) + steps(n-2). So we can use recursion or optimize
        process by memoization and predefined table
 2.2 Implementation
    2.2.1 Create array n+1 to store computed values. Populate it with first two values
    2.2.2 Iterate to n and populate array with required values
    2.2.3 Return last element from the array
    2.2.4 Space complexity can be optimized by using three integer variables instead of array (fibonacci number)
 */

class Solution {
    public int climbStairs(int n) {
        if (n <= 1) return 1;

        int[] hash = new int[n + 1];
        hash[0] = 1;
        hash[1] = 1;
        for (int i = 2; i <= n; i++) {
            hash[i] = hash[i - 1] + hash[i - 2];
        }
        return hash[n];
    }

//    public int climbStairs(int n) {
//        if (n <= 1) return 1;
//
//        int first = 1;
//        int second = 1;
//        int third = 2;
//        for (int i = 2; i <= n; i++) {
//            third = first + second;
//            first = second;
//            second = third;
//        }
//        return third;
//    }
}
