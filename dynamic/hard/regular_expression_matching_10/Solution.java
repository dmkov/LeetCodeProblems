package dynamic.hard.regular_expression_matching_10;

/**
 10. Regular Expression Matching
 https://leetcode.com/problems/regular-expression-matching/

 Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.
 The matching should cover the entire input string (not partial).

 Note:
 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like . or *.

 Example 1:
 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".

 Example 2:
 Input:
 s = "aa"
 p = "a*"
 Output: true
 Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

 Example 3:
 Input:
 s = "ab"
 p = ".*"
 Output: true
 Explanation: ".*" means "zero or more (*) of any character (.)".

 Example 4:
 Input:
 s = "aab"
 p = "c*a*b"
 Output: true
 Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

 Example 5:
 Input:
 s = "mississippi"
 p = "mis*is*p*."
 Output: false

 --------

 1. Complexity
    1.1 Time Complexity is O(s*p)
    1.2 Space Complexity is O(s*p)
 2. Approach
    2.1 Every char in the string we should compare with regexp template. Additionally, we need to check second character
        in the pattern. If it is not '*' then everything is simple - quantifier is '1'. Otherwise we need to check three
        different scenarios - 1) when string is subtracted, pattern remains without changes, 2) both string and pattern
        are subtracted and 3) pattern is subtracted without changes in string (s = "aab" and p = "c*a*b").
 2.2 Implementation
    2.2.1 Create a memo object - 2d array with length of s and p strings.
    2.2.2 Call deepSearch to check every char recursively.
        If case if both strings are empty - the result is true and search is finished.
        If only s is empty - we need to check if pattern has only '*' characters remaining.
        If pattern is empty but not string - the result is false and search is ended.
    2.2.3 Get letter from string and check if pattern character has quantifier '*'. If yes - check if char in pattern
        matches string letter. In positive scenario check recursively 3 possible cases: 1) subtracted string with
        the same pattern, 2) subtracted string and pattern and 3) only subtracted pattern. In the negative scenario
        check only case when pattern is subtracted but string remains the same.
    2.2.4 If pattern does not have "*" - check only matching of characters and reduce both pattern and string in
        positive scenario.
    2.2.5 Store results for every s and p in memo to reduce the time complexity.
 */

class Solution {
    int[][] memo;

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        memo = new int[s.length() + 1][p.length() + 1];
        return deepSearch(s, p);
    }

    private boolean deepSearch(String s, String p) {
        if (memo[s.length()][p.length()] == -1) {
            return false;
        } else if (memo[s.length()][p.length()] == 1) {
            return true;
        }

        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        boolean asteriskInPattern = p.length() > 1 && p.charAt(1) == '*';
        if (s.length() == 0 && asteriskInPattern) {
            return deepSearch(s, p.substring(2));
        }
        if (s.length() == 0 || p.length() == 0) {
            return false;
        }

        char letter = s.charAt(0);
        char regSymbol = p.charAt(0);

        boolean result;
        if (asteriskInPattern) {
            if (regSymbol == '.' || letter == regSymbol) {
                result = deepSearch(s.substring(1), p) || deepSearch(s.substring(1), p.substring(2)) || deepSearch(s, p.substring(2));
            } else {
                result = deepSearch(s, p.substring(2));
            }
        } else if (regSymbol == '.' || letter == regSymbol) {
            result = deepSearch(s.substring(1), p.substring(1));
        } else {
            result = false;
        }

        if (result) {
            memo[s.length()][p.length()] = 1;
        } else {
            memo[s.length()][p.length()] = -1;
        }

        return result;
    }
}
