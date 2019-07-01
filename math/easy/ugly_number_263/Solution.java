package math.easy.ugly_number_263;

/**
 263. Ugly Number
 https://leetcode.com/problems/ugly-number/

 Write a program to check whether a given number is an ugly number.
 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

 Example 1:
 Input: 6
 Output: true
 Explanation: 6 = 2 × 3

 Example 2:
 Input: 8
 Output: true
 Explanation: 8 = 2 × 2 × 2

 Example 3:
 Input: 14
 Output: false
 Explanation: 14 is not ugly since it includes another prime factor 7.

 Note:
 1 is typically treated as an ugly number.
 Input is within the 32-bit signed integer range: [−231,  231 − 1].

---

 1. Complexity
    1.1 Time Complexity is O(n) where n is number of factors
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is to check 5, 3 and 2 as possible factors until the number gets to 1 (or -1).
    2.2 If any of listed factors are not possible to use, then it is not an ugly number

 */
public class Solution {
    public boolean isUgly(int num) {
        if (num == 0) return false;

        while (num != 1) {
            if (num % 5 == 0) {
                num = num / 5;
                continue;
            }
            if (num % 3 == 0) {
                num = num / 3;
                continue;
            }
            if (num % 2 == 0) {
                num = num / 2;
                continue;
            }
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.isUgly(-2147483648);
    }
}
