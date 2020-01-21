package bit_operations.easy.find_the_difference_389;

/**
 389. Find the Difference
 https://leetcode.com/problems/find-the-difference/

 Given two strings s and t which consist of only lowercase letters.
 String t is generated by random shuffling string s and then add one more letter at a random position.
 Find the letter that was added in t.

 Example:
 Input:
 s = "abcd"
 t = "abcde"
 Output:
 e
 Explanation:
 'e' is the letter that was added.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is that bitwise '^' return 0 after applying the same number (char) twice. So we need to apply all numbers
        from the first string and then from the second one to get the difference at the end.
 2.2 Implementation
    2.2.1 Iterate first string and apply all chars from it to the temp variable, do the same for the second string
    2.2.2 Since the second string is bigger, apply one more character at the end of the loop.
    2.2.3 Return temp variable and it will contain the difference.
 */

public class BitwiseSolution {
    public char findTheDifference(String s, String t) {
        int n = t.length();
        char c = 0;
        for (int i = 0; i < n - 1; ++i) {
            c ^= s.charAt(i);
            c ^= t.charAt(i);
        }
        c ^= t.charAt(n - 1);

        return c;
    }
}