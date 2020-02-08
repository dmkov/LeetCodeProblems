package dynamic.medium.generate_parentheses_22;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     1.1 Time Complexity is O(n ?)
     1.2 Space Complexity is O(n^2) since we store all combinations for every level in memo
 2. Approach
     2.1 Moving from top to bottom, generate all possible options for strings. Use memoization to do not repeat
        process on different nodes.
     2.2 The bottom case is 1, then we can remove single pair "()".
     2.3 For others - we need to get f(i-1) first, then add parentheses around it. It will be the first string.
     2.4 Other strings we will get from combination of all options f(k)+f(n-k) where k is 1..i
 3 Implementation
     3.1 Check if input number is bigger than 0.
     3.2 Create a memo hash map for every level and call the recursive function with it.
     3.3 At the beginning of the function, check if given level is already in the memo variable,
        if not - proceed to the next step
     3.4 Check if level equals 1 (the bottom case), if it does - result is "()".
     3.5 Otherwise, create a list for storing answers and hash set to check that they are unique.
     3.6 Generate options for previous f(i-1) with "(" and ")" around the option.
     3.7 Then, get all combinations of f(k)+f(n-k) where k is 1..i
     3.8 Use hash set to exclude duplicates. At the end store result to memo with the given level.
 */

class DPSolution {

    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        if (n < 1) {
            return res;
        }
        Map<Integer, List<String>> memo = new HashMap<>();

        return getStrings(n, memo);
    }

    private List<String> getStrings(int num, Map<Integer, List<String>> memo) {
        if (memo.containsKey(num)) {
            return memo.get(num);
        }

        List<String> list = new LinkedList<>();
        if (num == 1) {
            list.add("()");
        } else {
            Set<String> unique = new HashSet<>();

            List<String> prev = getStrings(num - 1, memo);
            for (String str : prev) {
                unique.add("(" + str + ")");
            }

            for (int i = 1; i < num; i++) {
                List<String> first = getStrings(i, memo);
                List<String> second = getStrings(num - i, memo);
                for (String str1 : first) {
                    for (String str2 : second) {
                        unique.add(str1 + str2);
                    }
                }
            }

            list.addAll(unique);
        }

        memo.put(num, list);

        return list;
    }
}
