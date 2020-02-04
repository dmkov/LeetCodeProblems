package stack.meduim.score_of_parentheses_856;

import java.util.Stack;

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
    2.1 With every '(' we push a value '0' to the stack as a mark of depth level. With ')' we pop the last value,
        multiple it by 2 (or set to 1 if it is 0), then pop next value and sum up them both. In this way we move from
        from inner level to the top in calculations.
 3. Implementation
    3.1 Check if the input string is valid
    3.2 Create a stack and push the first '0' as a holder for the root level.
    3.3 For every character:
        3.3.1 If it is '(' - push another '0' to the stack
        3.3.2 If it is ')' - get the last value, multiple it by 2 (or set to 1 if it is 0). Then pop one more value and
            push sum of the first and second ones.
    3.4 Return the last element in the stack
 */

class StackSolution {
    public int scoreOfParentheses(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        Stack<Integer> nums = new Stack<>();
        nums.push(0);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                nums.push(0);
            } else {
                Integer n = nums.pop();
                n = (n.equals(0)) ? 1 : n * 2;
                nums.push(nums.pop() + n);
            }
        }

        return nums.pop();
    }
}
