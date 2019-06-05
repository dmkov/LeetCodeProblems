package string.easy.longest_common_prefix_14;

/**
 14. Longest Common Prefix
 https://leetcode.com/problems/longest-common-prefix/

 Write a function to find the longest common prefix string amongst an array of strings.
 If there is no common prefix, return an empty string "".

 Example 1:
 Input: ["flower","flow","flight"]
 Output: "fl"

 Example 2:
 Input: ["dog","racecar","car"]
 Output: ""
 Explanation: There is no common prefix among the input strings.

 Note:
 All given inputs are in lowercase letters a-z

 ------------------------

 1. Complexity
    1.1 Time Complexity is O(n*m) - where n is the number of strings and m is the length of the smallest one
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 This solution is based on iterating all strings and compare them with first selected one
    2.2 Implementation
        2.2.1 Check if array is not null, length is more than 0 and the first element is not null.
        2.2.2 Select the first element as checked item and iterate through all its characters.
        2.2.3 Iterate all strings from 1 to the end of the list and check if elements with selected index are equal
        2.2.4 Use StringBuilder to concat the result string
 */

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0] == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String check = strs[0];

        for (int i = 0; i < check.length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j] == null || strs[j].length() < i + 1 || strs[j].charAt(i) != check.charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(check.charAt(i));
        }
        return sb.toString();
    }
}
