package array.easy.plus_one_66;

import java.util.Arrays;

/**
 66. Plus One
 https://leetcode.com/problems/plus-one/

 Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

 You may assume the integer does not contain any leading zero, except the number 0 itself.

 Example 1:
 Input: [1,2,3]
 Output: [1,2,4]
 Explanation: The array represents the integer 123.

 Example 2:
 Input: [4,3,2,1]
 Output: [4,3,2,2]
 Explanation: The array represents the integer 4321.

 ------------------------

 1. Complexity
     1.1 Time Complexity is O(n) - where n is the number of elements in the list
     1.2 Space Complexity is O(1)
 2. Approach
    2.1. Iterate the array. If number is less than 9, we can return the array. Otherwise increment the number and go
        to the ext one.
    2.2. At the end return copy of the array with leading '1' (if array was different from 99..9, it will be returned
        sooner)
 */

public class Solution {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) return null;

        int period = 1;
        int size = digits.length;
        int[] result = new int[size + 1];

        for (int i = size - 1; i >= 0; i--) {
            int val = digits[i] + period;
            if (val == 10) {
                val = 0;
                period = 1;
            } else {
                period = 0;
            }

            result[i + 1] = val;
        }

        if (period == 0) {
            result = Arrays.copyOfRange(result, 1, result.length);
        } else {
            result[0] = period;
        }

        return result;
    }

//    public int[] betterAlternative(int[] digits) {
//        int n = digits.length;
//        for(int i=n-1; i>=0; i--) {
//            if(digits[i] < 9) {
//                digits[i]++;
//                return digits;
//            }
//
//            digits[i] = 0;
//        }
//
//        int[] newNumber = new int [n+1];
//        newNumber[0] = 1;
//
//        return newNumber;
//    }
}
