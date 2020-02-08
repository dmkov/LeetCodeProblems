package dynamic.medium.generate_parentheses_22;

import java.util.LinkedList;
import java.util.List;

/**
 22. Generate Parentheses
 https://leetcode.com/problems/generate-parentheses/

 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:
 [
     "((()))",
     "(()())",
     "(())()",
     "()(())",
     "()()()"
 ]

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(m)
 2. Approach
     2.1 Moving from the first element we check if we have remaining open brackets "(" and if it is possible to put
        closed bracket ")" (only if number of already open is bigger than closed brackets). Depending on results we add
        both characters to the string and iterate function again.
 3 Implementation
     3.1 Check if input number is bigger than 0.
     3.2 Execute method with initial "(" string and counters open=1 and closed=0
        3.2.1 Inside the method, if length of the string is n*2 - its our goal, put it to the result list
        3.2.2 If counter for open brackets is less than n, execute method recursively for before+"(" string and open+1 counter.
        3.2.3 If number of placed open brackets is larger than number of closed brackets, we can add one more closed bracket.
            Execute function recursively for before+")" string and closed+1 counter.
 */

class BacktrackingSolution {

    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        if (n < 1) {
            return res;
        }
        generate("(", n, 1, 0, res);

        return res;
    }

    private void generate(String before, int n, int open, int closed, List<String> res) {
        if (before.length() == n * 2) {
            res.add(before);
            return;
        }

        if (open < n) {
            generate(before + "(", n, open + 1, closed, res);
        }
        if (n - open < n - closed) {
            generate(before + ")", n, open, closed + 1, res);
        }
    }
}
