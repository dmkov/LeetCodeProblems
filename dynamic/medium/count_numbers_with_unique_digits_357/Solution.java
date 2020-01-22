package dynamic.medium.count_numbers_with_unique_digits_357;

/**
 357. Count Numbers with Unique Digits
 https://leetcode.com/problems/count-numbers-with-unique-digits/

 Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

 Example:
 Input: 2
 Output: 91
 Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
 excluding 11,22,33,44,55,66,77,88,99

 --------

 1. Complexity
    1.1 Time Complexity is O(1)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The max number of digits is 10, so if we get n > 10 it is impossible to make a number. Same with n < 0.
        Lets try to consider edge cases, for X - unique combinations is 10 (including 0), for XX - 81 (9*9), for XXX - 9*9*8,
        for XXXX - 9*9*8*7. So that's the pattern. We also need to add all previous combinations, since 9999 is XXXX + XXX + XX + X.
 2.2 Implementation
    2.2.1 Check if input n is valid.
    2.2.2 Create first edge cases for 0 and 1 in the result array. Iterate from 2 to n and on every step calculate the product and
        sum previous results.
    2.2.3 Return the n element in the result array.
 */

class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n < 0 || n > 10) {
            return 0;
        }

        int[] result = new int[11];
        result[0] = 1;
        result[1] = 10;

        int product = 9;
        int availableNumbers = 9;
        for (int i = 2; i <= n; i++) {
            product *= availableNumbers;
            result[i] = product + result[i - 1];

            availableNumbers--;
        }

        return result[n];
    }
}
