package string.medium.multiply_strings_43;

/**
 43. Multiply Strings
 https://leetcode.com/problems/multiply-strings/

 Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

 Example 1:
 Input: num1 = "2", num2 = "3"
 Output: "6"

 Example 2:
 Input: num1 = "123", num2 = "456"
 Output: "56088"

 Note:
 The length of both num1 and num2 is < 110.
 Both num1 and num2 contain only digits 0-9.
 Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.

 --------

 1. Complexity
    1.1 Time Complexity is O(n1*n2)
    1.2 Space Complexity is O(n1+n2)
 2. Approach
    2.1 The idea is to use temporary array where we can store all products even bigger then 10.
    2.2 At the end of multiplication, traverse the array and calculate the carry from numbers to the next index
    2.3 Finally, construct string from the array
 */

class TempArraySolution {

    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            throw new IllegalArgumentException();
        }

        int n1 = num1.length();
        int n2 = num2.length();

        int[] res = new int[n1 + n2];
        for (int j = n2 -1; j >=0 ; j--) {
            int d2 = num2.charAt(j) - '0';
            for (int i = n1 - 1; i >= 0; i--) {
                int d1 = num1.charAt(i) - '0';
                res[i + j + 1] += d1 * d2;
            }
        }

        int carry = 0;
        for (int i = res.length - 1; i>= 0; i--) {
            int val = res[i] + carry;
            res[i] = val % 10;
            carry = val / 10;
        }

        int i = 0;
        while (res[i] == 0 && i < res.length - 1) {
            i++;
        }

        StringBuilder sb = new StringBuilder();
        for (int c = i; c < res.length; c++) {
            sb.append(res[c]);
        }

        return sb.toString();

    }

}
