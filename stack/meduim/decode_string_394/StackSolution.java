package stack.meduim.decode_string_394;

import java.util.Stack;

/**
 394. Decode String
 https://leetcode.com/problems/decode-string/

 Given an encoded string, return its decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 Examples:
 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is to collect letters into the local string and with every '[' start from the beginning and push previous value
        to the stack with the number of occurrences for the future usage. With every ']' character we finalise local string and
        add it to the value previously stored in the stack.
 3 Implementation
     3.1 Check if given string is valid. Create two stacks to keep numbers and local strings.
     3.2 For every character in the given string:
        3.2.1 If it is a number, add it to the local string with numbers (number can have more than 1 digit at the end).
        3.2.2 If it is '[' - push current string and computed number to the stacks. Then clear string and number variables.
        3.2.3 If it is ']' - pop number from stack, concat local string popped number of times. Get previous string from stack and
            add to it current local variable.
        3.2.4 Otherwise, just add a character to the local string.
 */

class StackSolution {

    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String number = "";
        String str = "";
        Stack<Integer> nums = new Stack<>();
        Stack<String> strings = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                number += String.valueOf(ch);
            } else if (ch == '[') {
                strings.push(str);
                str = "";
                nums.push(Integer.valueOf(number));
                number = "";
            } else if (ch == ']') {
                String res = "";
                int n = nums.pop();
                for (int k = 0; k < n; k++) {
                    res += str;
                }
                str = strings.pop() + res;
            } else {
                str += String.valueOf(ch);
            }
        }

        return str;
    }

}
