package string.easy.add_binary_67;

/**
 67. Add Binary
 https://leetcode.com/problems/add-binary/

 Given two binary strings, return their sum (also a binary string).
 The input strings are both non-empty and contains only characters 1 or 0.

 Example 1:
 Input: a = "11", b = "1"
 Output: "100"

 Example 2:
 Input: a = "1010", b = "1011"
 Output: "10101"

 ---

 1. Complexity
    1.1 Time Complexity is O(n) where n is the largest length of both string
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Sum up carry, a and b digits.
    2.2 Then depending on results, set carry and current digit in the string
 */

class Solution {
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return "";
        }

        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                carry++;
            }
            if (j >= 0) {
                carry++;
            }
            if (carry == 0) {
                sb.append('0');
            } else if (carry == 1) {
                sb.append('1');
            } else if (carry == 2) {
                sb.append('0');
                carry = 1;
            } else if (carry == 3) {
                sb.append('1');
                carry = 1;
            }
            i--;
            j--;
        }
        if (carry == 1) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }
}
