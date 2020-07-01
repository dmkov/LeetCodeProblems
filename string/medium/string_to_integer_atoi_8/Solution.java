package string.medium.string_to_integer_atoi_8;

/**
 8. String to Integer (atoi)
 https://leetcode.com/problems/string-to-integer-atoi/

 Implement atoi which converts a string to an integer.

 The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

 The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

 If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

 If no valid conversion could be performed, a zero value is returned.

 Note:
 Only the space character ' ' is considered as whitespace character.
 Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.

 Example 1:
 Input: "42"
 Output: 42

 Example 2:
 Input: "   -42"
 Output: -42
 Explanation: The first non-whitespace character is '-', which is the minus sign.
 Then take as many numerical digits as possible, which gets 42.

 Example 3:
 Input: "4193 with words"
 Output: 4193
 Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.

 Example 4:
 Input: "words and 987"
 Output: 0
 Explanation: The first non-whitespace character is 'w', which is not a numerical
 digit or a +/- sign. Therefore no valid conversion could be performed.

 Example 5:
 Input: "-91283472332"
 Output: -2147483648
 Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 Thefore INT_MIN (−231) is returned.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    dp[i][j] = s2[0..i] in s1[0..j]
    increasing window in both string put into dp table start index when the substring is matched
 */

class Solution {

    public int myAtoi(String str) {
        boolean positive = true;
        int result = 0;

        // Basic validation
        if (str == null || str.length() == 0) {
            return 0;
        }

        // Trim leading spaces
        int pos = 0;
        while (pos < str.length() && str.charAt(pos) == ' ') {
            pos++;
        }

        // Check the first sign
        if (pos < str.length() && (str.charAt(pos) == '+' || str.charAt(pos) == '-')) {
            positive = (str.charAt(pos) == '+') ? true : false;
            pos++;
        }

        // Consider only followed digits
        while (pos < str.length() && str.charAt(pos) >= '0' && str.charAt(pos) <= '9') {
            int digit = str.charAt(pos) - '0';
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10
                && digit > Integer.MAX_VALUE % 10)) {
                return (positive) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            pos++;
        }

        if (!positive) {
            result = -result;
        }

        return result;
    }

    /**
     "123" -> 123
     "abc123" -> 0
     "123abc" -> 123
     "  -123" -> -123
     "  +123" -> 123
     "  1a2b3c" -> 1
     "99999999999" -> INT_MAX


     1. Validate and trim string
     2. Parse it from right to left multipling by 10 on every digit.
     Q: integer overflow
     use long and cast it to int. also check number of digits and return min/max constants

     - iterate over characters from left to right, counter 10
     - if counter >= 0
     - if it is " ", skip if number is empty, otherwise, break
     - if it is "-", set sign flag if it is empty, otherwise, break
     - if it is digit
     multiply number by 10, add current digit
     counter--
     - at the end apply "-" if it and convert to integer

     */
}
