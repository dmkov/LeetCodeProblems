package dynamic.medium.palindromic_substrings_647;

/**
 647. Palindromic Substrings
 https://leetcode.com/problems/palindromic-substrings/

 Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:
 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".

 Example 2:
 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

 Note:
 The input string length won't exceed 1000.

 --------

 1. Complexity
     1.1 Time Complexity is O(n^2)
     1.2 Space Complexity is O(n^2)
 2. Approach
     2.1 The idea is is based on expanding centers approach but instead of trying to count all palindromes from the beginning
        we use mirror index to initialize the value and reduce the number of checking
     2.2 Implementation
        2.2.1 Check if input string is valid. Transform it to the form "$#a#b#a#@" to simplify work with boundaries.
        2.2.2 Set i to the first character with center and right bounder pointers.
        2.2.3 For the every character, check if it is less than right boundary (it means we are inside the bigger palindrome
            and can improve our start point).
            2.2.3.1 If yes, set nums[i] to the min of (right - 1) and mirror item (2*center - i)
        2.2.4 Continue check and increment nums[i] if str.charAt(i - nums[i] - 1) equals str.charAt(i + nums[i] + 1)
        2.2.5 At the end add to result nums[i] / 2 as a number of palindromes. Also add a length of the initial string
            since every character is a palindrome itself.
 */

class ManachersAlgorithmSolution {

    public int countSubstrings(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }

        result = s.length(); // 6

        StringBuilder sb = new StringBuilder();
        sb.append("$#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append("#");
        }
        sb.append("@");

        String str = sb.toString(); // aaa -> $#a#a#a#@
        int[] nums = new int[str.length()];
        int center = 1, right = 1;

        // $#a#a#a#@
        //. 0123
        for (int i = 1; i < str.length() - 1; i++) {
            if (right > i) {
                nums[i] = Math.min(nums[2 * center - i], right - i);
            }
            while (str.charAt(i - nums[i] - 1) == str.charAt(i + nums[i] + 1)) {
                nums[i]++;
            }
            if (nums[i] >= nums[center]) {
                center = i;
                right = i + nums[i];
            }
            result += nums[i] / 2;
        }

        return result;
    }
}
