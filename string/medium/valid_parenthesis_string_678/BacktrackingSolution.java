package string.medium.valid_parenthesis_string_678;

import java.util.HashMap;
import java.util.Map;

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
    1.1 Time Complexity is O(n^3) since there are 3 branches on every possible *
    1.2 Space Complexity is O(n) because of memory stack depth
 2. Approach
    2.1 The idea is to split process into 3 branches with every possible * and then return true if any of
        directions leads to '0'
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

class BacktrackingSolution {

    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Map<String, Boolean> memo = new HashMap<>();

        return backtracking(s, 0, 0, memo);
    }

    private boolean backtracking(String s, int pos, int counter, Map<String, Boolean> memo) {
        if (counter < 0) {
            return false;
        }
        if (pos == s.length()) {
            return counter == 0;
        }

        String key = pos + "_" + counter;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        char ch = s.charAt(pos);
        boolean result = false;
        if (ch == '(') {
            result = backtracking(s, pos+1, counter+1, memo);
        } else if (ch == ')') {
            result = backtracking(s, pos+1, counter-1, memo);
        } else {
            result = backtracking(s, pos+1, counter+1, memo)
                    || backtracking(s, pos+1, counter-1, memo)
                    || backtracking(s, pos+1, counter, memo);
        }

        memo.put(key, result);
        return result;

    }

}
