package bit_operations.medium.maximum_product_of_word_lengths_318;

import java.util.ArrayList;
import java.util.List;

/**
 318. Maximum Product of Word Lengths
 https://leetcode.com/problems/maximum-product-of-word-lengths/

 Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
 You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

 Example 1:
 Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
 Output: 16
 Explanation: The two words can be "abcw", "xtfn".

 Example 2:
 Input: ["a","ab","abc","d","cd","bcd","abcd"]
 Output: 4
 Explanation: The two words can be "ab", "cd".

 Example 3:
 Input: ["a","aa","aaa","aaaa"]
 Output: 0
 Explanation: No such pair of words.

 --------

 1. Complexity
    1.1 Time Complexity is O(n^2) - which is long but technically is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Use bitwise mask for words where each character is a bit (26 letters are covered by 32 int number) and then
        do a bitwise '&' operation to check if there is any common bits.
 2.2 Implementation
    2.2.1 Check input array is valid.
    2.2.2 Populate two arrays with masks and lengths for every word in the array. To get a mask, iterate all character
        in the string and shift '1' to the number of bits starting from 'a'.
    2.2.3 Do two iterations of the array with two pointers i and j. If binary '&' of both masks gives 0, compute product
        of length and return the max value at the end.
 */

public class Solution {
    public int maxProduct(String[] words) {
        int result = 0;
        if (words == null || words.length < 2) {
            return result;
        }

        int size = words.length;
        int[] mask = new int[size];
        int[] length = new int[size];
        for (int i = 0; i < size; i++) {
            mask[i] = getMask(words[i]);
            length[i] = words[i].length();
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if ((mask[i] & mask[j]) == 0) {
                    result = Math.max(result, length[i] * length[j]);
                }
            }
        }

        return result;
    }

    private int getMask(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int shift = s.charAt(i) - 'a';
            result = result | (1 << shift);
        }

        return result;
    }
}
