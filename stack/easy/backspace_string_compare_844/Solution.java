package stack.easy.backspace_string_compare_844;

/**
 844. Backspace String Compare
 https://leetcode.com/problems/backspace-string-compare/

 Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

 Example 1:
 Input: S = "ab#c", T = "ad#c"
 Output: true
 Explanation: Both S and T become "ac".

 Example 2:
 Input: S = "ab##", T = "c#d#"
 Output: true
 Explanation: Both S and T become "".

 Example 3:
 Input: S = "a##c", T = "#a#c"
 Output: true
 Explanation: Both S and T become "c".

 Example 4:
 Input: S = "a#c", T = "b"
 Output: false
 Explanation: S becomes "c" while T becomes "b".

 Note:
 1 <= S.length <= 200
 1 <= T.length <= 200
 S and T only contain lowercase letters and '#' characters.

 ---


 1. Complexity
    1.1 Time Complexity is O(n + m) - where n and m is the length of strings
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on operations with StringBuilder.
        Alternative solution is to create stacks and compare them at the end (extra space)
    2.2 Implementation
        2.2.1 Check if strings are not null.
        2.2.2 Compute strings without backslashes and compare them.
        2.2.3 To compute - use StringBuilder with append() and deleteCharAt() methods.

 */
class Solution {
    public boolean backspaceCompare(String S, String T) {
        if (S == null || T == null) {
            return S == T;
        }
        String str1 = computeBackspaces(S);
        String str2 = computeBackspaces(T);
        return str1.equals(str2);
    }

    private String computeBackspaces(String str) {
        StringBuilder sb = new StringBuilder();
        for (char ch: str.toCharArray()) {
            if (ch == '#') {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

//    public boolean backspaceCompare(String S, String T) {
//        if (S == null || T == null) {
//            return S == T;
//        }
//        Stack<Character> str1 = computeBackspaces(S);
//        Stack<Character> str2 = computeBackspaces(T);
//        return str1.equals(str2);
//    }
//
//    private Stack<Character> computeBackspaces(String str) {
//        Stack<Character> stack = new Stack<>();
//        for (char ch: str.toCharArray()) {
//            if (ch == '#') {
//                if (stack.size() > 0) {
//                    stack.pop();
//                }
//            } else {
//                stack.push(ch);
//            }
//        }
//        return stack;
//    }
}
