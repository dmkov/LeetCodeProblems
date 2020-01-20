package greedy.medium.reorganize_string_767;

/**
 767. Reorganize String
 https://leetcode.com/problems/reorganize-string/

 Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

 If possible, output any possible result.  If not possible, return the empty string.

 Example 1:
 Input: S = "aab"
 Output: "aba"

 Example 2:
 Input: S = "aaab"
 Output: ""

 Note:
 S will consist of lowercase letters and have length in range [1, 500].

 --------

 1. Complexity
    1.1 Time Complexity is O(n), we only need to find max value
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to split string to separate characters and then combine them in a new string on "n + 2" positions
 2.2 Implementation
    2.2.1 Check if given string is valid.
    2.2.2 Use an array of 26 integers to count number of every character in the string.
    2.2.3 Iterate array and find the max index (character) in it. We will start to populate new string with it. Also
        check if number of elements is greater than Math.ceil(length/2.0), it is not possible to reorganize the string, return "".
    2.2.3 Set start index to "0" and populate all characters and max index to "start+2" positions. Reduce number
        of characters in the array with every iteration.
    2.2.4 Then continue process for other characters (order does not matter) in another loop. When start index exceeds
        length, update it to 1 to populate odd elements.
    2.2.5 Using StringBuilder compute string from the array with characters.
 */

public class GoodMathSolution {
    public String reorganizeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int length = s.length();
        int[] chars = new int[26];
        for (int i = 0; i < length; i++) {
            chars[s.charAt(i) - 'a']++;
        }

        int max = 0;
        int idx = 0;
        for (int i = 0; i < 26; i++) {
            if (chars[i] > max) {
                max = chars[i];
                idx = i;
            }
        }

        if (max > Math.ceil(length/2.0)) {
            return "";
        }

        int[] result = new int[length];
        int start = 0;
        while (chars[idx] > 0) {
            result[start] = idx;
            chars[idx]--;
            start += 2;
        }

        for (int i = 0; i < 26; i++) {
            while(chars[i] > 0) {
                if (start > length - 1) {
                    start = 1;
                }
                result[start] = i;
                chars[i]--;
                start += 2;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char)('a' + result[i]));
        }

        return sb.toString();
    }
}
