package stack.meduim.minimum_remove_to_make_valid_parentheses_1249;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 1249. Minimum Remove to Make Valid Parentheses
 https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/

 Given a string s of '(' , ')' and lowercase English characters.

 Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

 Formally, a parentheses string is valid if and only if:

 It is the empty string, contains only lowercase characters, or
 It can be written as AB (A concatenated with B), where A and B are valid strings, or
 It can be written as (A), where A is a valid string.

 Example 1:
 Input: s = "lee(t(c)o)de)"
 Output: "lee(t(c)o)de"
 Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

 Example 2:
 Input: s = "a)b(c)d"
 Output: "ab(c)d"

 Example 3:
 Input: s = "))(("
 Output: ""
 Explanation: An empty string is also valid.

 Example 4:
 Input: s = "(a(b(c)d)"
 Output: "a(b(c)d)"

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 There are two cases when string is not valid: 1) leading ')' and 2) '(' with no pair.
    2.2 Moving from left to right we will find all leading ')' with no pairs
    2.3 Then, we can repeat the same thing from right to left for '('
 */

class TwoDirectionsSolution {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        Set<Integer> toRemove = new HashSet<>();
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ')') {
                if (counter == 0) {
                    toRemove.add(i);
                } else {
                    counter--;
                }
            } else if (ch == '(') {
                counter++;
            }
        }

        counter = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '(') {
                if (counter == 0) {
                    toRemove.add(i);
                } else {
                    counter--;
                }
            } else if (ch == ')') {
                counter++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!toRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    /**
     string containing '(' ')' or any lower case character
     remove min number of '(' ')' to make string valid

         (a()) - valid
         a -> valid
         a()) -> a()
         (( -> ""
         )a( -> a
         ()()))

     1. Backtracking approach
         In every position consider 2 possible cases: remove or leave character
         At the very end - if string is valid, return number of removed chars
         O(2^n*n)
         track if string is valid or not on each step
         O(2^n)

     Observation:
         The string is valid
         - if no ")" in front of "("  - they all should be removed
         - if no "(" remaining at the end - they should be removed after matching

         ()(()()
         1012121
         01-10101

     */
}
