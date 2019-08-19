package math.easy.power_of_three_326;

/**
 326. Power of Three
 https://leetcode.com/problems/power-of-three/

 Given an integer, write a function to determine if it is a power of three.

 Example 1:
 Input: 27
 Output: true

 Example 2:
 Input: 0
 Output: false

 Example 3:
 Input: 9
 Output: true

 Example 4:
 Input: 45
 Output: false

 Follow up:
 Could you do it without using any loop / recursion?

---

 1. Complexity
    1.1 Time Complexity is depend on Math.log10 implementation
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is to convert n = 3^x to x = log3(n) or = lg(n)/lg(3) so we can calculate it and check if its natural
        number.
    2.2 Another approach is to get all pounds of 3 and store them in hashset.
    2.3 One more approach is for primary numbers. We can calculate the max available 3^x in integer range and then
        check if we can divide this number by n. So, 3^19 = 1162261467 and answer is 1162261467 % n == 0

 */
public class Solution {
    public boolean isPowerOfThree(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

//    public boolean isPowerOfThree(int n) {
//        if (n < 1) return false;
//        while (n % 3 == 0) {
//            n = n / 3;
//        }
//
//        return n == 1;
//    }
}
