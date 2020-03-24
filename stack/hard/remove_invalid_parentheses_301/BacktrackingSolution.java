package stack.hard.remove_invalid_parentheses_301;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 301. Remove Invalid Parentheses
 https://leetcode.com/problems/remove-invalid-parentheses/

 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 Note: The input string may contain letters other than the parentheses ( and ).

 Example 1:
 Input: "()())()"
 Output: ["()()()", "(())()"]

 Example 2:
 Input: "(a)())()"
 Output: ["(a)()()", "(a())()"]

 Example 3:
 Input: ")("
 Output: [""]

---

 https://leetcode.com/problems/remove-invalid-parentheses/discuss/75041/Java-BFS-solution-16ms-avoid-generating-duplicate-strings

 1. Complexity
    1.1 Time Complexity is O(2^n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.2 The idea is iterate over all string and try to remove every ')' or '(' symbol checking if string is valid or not.
        Then we need to select valid strings with the max lengths and ignore others.
    2.3 To improve this algorithm we can exclude duplicates on fly:
        #1 For the next version of string remove brackets after the last removed position
        #2 If there are repeatable elements like '((' or '))' - remove only first since the removing second char
            will lead to the same result string
        #3 If last removed char was '(' - we can not remove ')' because it will lead to the removing the valid pair
            and reducing string without needed effect.
 3. Implementation
    3.1 Check if the input string is valid.
    3.2 Add queue to store invalid and in process elements as well as the result string. To store in process elements,
        use a separate class with the string, last removed character and position of removed character.
    3.3 Add initial string to the queue and repeat while queue is not empty:
        3.2.1 Pop the string and iterate its all characters.
        3.2.2 If character is not '(' or ')' - skip it and proceed to the next char.
        3.2.3 If character is the same as previous char in the string - skip it
        3.2.4 If character is ')' and last removed was '(' - skip it
        3.2.5 Otherwise substring the new string without current character. If it is valid - add to the result,
            otherwise - put into the queue.
        3.2.6 Track size of the first string in the result. Later, skip all strings lesser than this size.
    3.3 Return result array.
 */

class BacktrackingSolution {
    class StringElement {
        String val;
        Integer pos;
        Character lastRemoved;

        public StringElement(String val, Integer pos, Character lastRemoved) {
            this.val = val;
            this.pos = pos;
            this.lastRemoved = lastRemoved;
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return new LinkedList<>(Arrays.asList(""));
        }
        if (isValid(s)) {
            return new LinkedList<>(Arrays.asList(s));
        }

        List<String> res = new LinkedList<>();
        LinkedList<StringElement> queue = new LinkedList<>();
        queue.add(new StringElement(s, 0, ' '));

        int size = -1;
        while (queue.size() > 0) {
            StringElement str = queue.remove();

            // #1 optimization
            for (int i = str.pos; i < str.val.length(); i++) {
                char ch = str.val.charAt(i);

                // #2 optimization
                if (i > 0 && str.val.charAt(i - 1) == ch) {
                    continue;
                }
                // #3 optimization
                if (ch == ')' && str.lastRemoved == '(') {
                    continue;
                }

                if (ch != '(' && ch != ')') continue;

                // '(' or ')'
                String newStr = ((i != 0) ? str.val.substring(0, i) : "")
                        + ((i != str.val.length() - 1) ? str.val.substring(i+1) : "");

                if (isValid(newStr)) {
                    if (size == -1) {
                        size = newStr.length();
                    }
                    res.add(newStr);
                } else if (size == -1 || size < newStr.length()) {
                    queue.add(new StringElement(newStr, i, ch));
                }
            }
        }

        return res;
    }

    private boolean isValid(String s) {
        boolean res = true;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                open++;
            } else if (ch == ')') {
                if (open > 0) {
                    open--;
                } else {
                    res = false;
                    break;
                }
            }
        }

        return res && (open == 0);
    }
}
