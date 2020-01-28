package string.easy.add_strings_415;

import java.util.Stack;

/**
 415. Add Strings
 https://leetcode.com/problems/add-strings/

 Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 Note:
 The length of both num1 and num2 is < 5100.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.

---

 1. Complexity
    1.1 Time Complexity is O(n) where n is the largest length of both string
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The solution is based on keeping carry for each sum operation additionally
 3. Implementation
    3.1 Check if the input strings are valid
    3.2 Iterate both strings (while any characters left). If string was ended, use 0, otherwise get the digit from the right.
    3.3 Sum first, second and carry digits, store them to the StringBuilder.
    3.4 At the end, if carry is not empty, add it as another '1' to the result
    3.5 Return reversed string from the string builder.
 */

class Solution {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) {
            num1 = "0";
        }
        if (num2 == null || num2.length() == 0) {
            num2 = "0";
        }

        StringBuilder sum = new StringBuilder();
        int carry = 0;

        for (int i = 0, j = 0; i < num1.length() || j < num2.length(); i++, j++) {
            int a = 0;
            if (i < num1.length()) {
                a = num1.charAt(num1.length() - i - 1) - '0';
            }
            int b = 0;
            if (i < num2.length()) {
                b = num2.charAt(num2.length() - i - 1) - '0';
            }

            sum.append(String.valueOf((a + b + carry) % 10));
            carry = (a + b + carry) / 10;
        }
        if (carry == 1) {
            sum.append("1");
        }

        return sum.reverse().toString();
    }
}
