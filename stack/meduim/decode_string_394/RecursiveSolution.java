package stack.meduim.decode_string_394;

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
     2.1 The idea is to collect letters between brackets and call method recursively if we get to another brackets.
        The issue with returning position where the recursive method ended in addition to the computed string. To solve this
        we use private property in the class
 3 Implementation
     3.1 Check if given string is valid. Create StringBuilder for local string and number variables.
     3.2 For every character in the given string:
        3.2.2 If it is '[' - execute method recursively, at the end compute the result by repeating it number times. Reset number variable.
        3.2.3 If it is ']' - return the local string
        3.2.1 If it is a number, add it to the local string with numbers (number can have more than 1 digit at the end).
        3.2.4 Otherwise, just add a character to the local string.
 */

class RecursiveSolution {

    private int pos = 0;

    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String number = "";
        StringBuilder str = new StringBuilder();
        while (pos < s.length()) {
            char ch = s.charAt(pos);
            pos++;

            if (ch == '[') {
                String sub = decodeString(s);
                for (int k = 0; k < Integer.valueOf(number); k++) {
                    str.append(sub);
                }
                number = "";
            } else if (ch == ']') {
                return str.toString();
            } else if (ch >= '0' && ch <= '9') {
                number += String.valueOf(ch);
            } else {
                str.append(String.valueOf(ch));
            }
        }

        return str.toString();
    }

}
