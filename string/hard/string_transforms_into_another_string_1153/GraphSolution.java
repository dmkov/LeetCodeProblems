package string.hard.string_transforms_into_another_string_1153;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 1153. String Transforms Into Another String
 https://leetcode.com/problems/string-transforms-into-another-string/

 Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.

 In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.

 Return true if and only if you can transform str1 into str2.

 Example 1:
 Input: str1 = "aabcc", str2 = "ccdee"
 Output: true
 Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.

 Example 2:
 Input: str1 = "leetcode", str2 = "codeleet"
 Output: false
 Explanation: There is no way to transform str1 to str2.

 --------

 1. Complexity
    1.1 Time Complexity is O(n) where n is the string length
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is to check every string and get suffixes and prefixes around the palindrome part of the word.
    2.2 After getting these suffixes and prefixes we can easily reverse them and check if reversed version exists
        in pre-cached words in the hash map.
 */

class GraphSolution {

    public boolean canConvert(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        if (str1.equals(str2)) {
            return true;
        }

        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(i);

            if (map.containsKey(ch1) && !map.get(ch1).equals(ch2)) {
                return false;
            }
            map.put(ch1, ch2);
        }

        return new HashSet<Character>(map.values()).size() < 26;
    }
}

/**
     aabcc -> cceee
     2,1,2    2,3


     1,1,5,3,2  -> 7,4,1

     1. Backtracking approach
        O(k*2^n)


     2. Graph approach
        O(n)
 */
