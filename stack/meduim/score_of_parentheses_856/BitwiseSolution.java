package stack.meduim.score_of_parentheses_856;

/**
 856. Score of Parentheses
 https://leetcode.com/problems/score-of-parentheses/

 Given a balanced parentheses string S, compute the score of the string based on the following rule:

 () has score 1
 AB has score A + B, where A and B are balanced parentheses strings.
 (A) has score 2 * A, where A is a balanced parentheses string.

 Example 1:
 Input: "()"
 Output: 1

 Example 2:
 Input: "(())"
 Output: 2

 Example 3:
 Input: "()()"
 Output: 2

 Example 4:
 Input: "(()(()))"
 Output: 6

 Note:
 S is a balanced parentheses string, containing only ( and ).
 2 <= S.length <= 50

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 We count the number of layers. Every layer means *2 of 1 (if we open parentheses).
    2.2 If we meet '(' layers number l++ else we meet ')' layers number l--
    2.3 If we meet "()", we know the number of layer outside, so we can calculate the score res += 1 << l.
 3. Implementation
    3.1 Check if the input string is valid
    3.2 Create 0 sum and 0 lvl counter.
    3.3 For every character:
        3.3.1 If it is '(':
            3.3.1.1 Check if the next character is ')', then add to sum the value (1 << lvl) to 1*2*2*2 for every lvl
            3.3.1.2 Just increment lvl at the end
        3.3.1 If it is ')' - decrement lvl counter
    3.4 Return sum as the result
 */

class BitwiseSolution {
    public int scoreOfParentheses(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int sum = 0;
        int lvl = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                if (str.charAt(i + 1) == ')') {
                    sum += (1 << lvl);
                }
                lvl++;
            } else {
                lvl--;
            }
        }

        return sum;
    }
}
