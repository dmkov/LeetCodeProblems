package stack.easy.valid_parentheses_20;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 20. Valid Parentheses
 https://leetcode.com/problems/valid-parentheses/

 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 An input string is valid if:
 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Note that an empty string is also considered valid.

 Example 1:
 Input: "()"
 Output: true

 Example 2:
 Input: "()[]{}"
 Output: true

 Example 3:
 Input: "(]"
 Output: false

 Example 4:
 Input: "([)]"
 Output: false

 Example 5:
 Input: "{[]}"
 Output: true

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is to use stack for comparing closing brackets with the last open one in the string.
 3 Implementation
     3.1 Check if input string is valid
     3.2 For every character in the string define:
        3.2.1 If it is open bracket, put it to the stack
        3.2.2 If it is a closed bracket, compare pair with the first in the stack
     3.3 Check that working stack is empty at the end.
 */

class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int length = s.length();
        if (length % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        Set<Character> open = new HashSet<>(Arrays.asList('{', '[', '('));
        for (int i = 0; i < length; i++) {
            if (open.contains(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (stack.size() == 0 || !match(stack.pop(), s.charAt(i))) {
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }

    private boolean match(char a, char b) {
        if ((a == '{' && b == '}')
                || (a == '[' && b == ']')
                || (a == '(' && b == ')')) {
            return true;
        } else {
            return false;
        }
    }
}
