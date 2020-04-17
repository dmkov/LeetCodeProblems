package string.medium.valid_parenthesis_string_678;

/**
 678. Valid Parenthesis String
 https://leetcode.com/problems/valid-parenthesis-string/

 Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

 Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 Any right parenthesis ')' must have a corresponding left parenthesis '('.
 Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 An empty string is also valid.

 Example 1:
 Input: "()"
 Output: True

 Example 2:
 Input: "(*)"
 Output: True

 Example 3:
 Input: "(*))"
 Output: True

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is to split process into 3 branches for every '*' with possible change in counter -1/0/+1. Since
        it is continuous sequence of values we can use lo and hi limits to track results.
    2.2 So we permutate all combinations and increase/decrease boundary counters. If at any state we have 'hi' smaller
        than 0 - string is not valid. If 'lo' smaller than 0 - set it to '0' since we need to consider only valid states
 */

/**
 () -> true
 ) -> false
 *) -> true
 )* -> false
 ())* -> false

 iterate string with backtracking func:
 - counter < 0 -- return false
 - pos == length && counter == 0 -- return true
 - if "(" - increment counter
 - if ")" - decrement counter
 - if "*" - increment/decrement/leave the same
 */

class GreedySolution {

    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int hi = 0, lo = 0;
        for (int p = 0; p < s.length(); p++) {
            char ch = s.charAt(p);
            if (ch == '(') {
                hi++; lo++;
            } else if (ch == ')') {
                hi--; lo--;
                if (lo < 0) {
                    lo = 0;
                }
                if (hi < 0) {
                    return false;
                }
            } else {
                lo--;
                hi++;
            }
        }

        return lo <= 0 && hi >= 0;
    }

}
