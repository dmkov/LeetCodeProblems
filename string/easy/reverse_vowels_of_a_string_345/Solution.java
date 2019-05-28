package string.easy.reverse_vowels_of_a_string_345;

/**
 345. Reverse Vowels of a String
 https://leetcode.com/problems/reverse-vowels-of-a-string/

 Write a function that takes a string as input and reverse only the vowels of a string.

 Example 1:
 Input: "hello"
 Output: "holle"

 Example 2:
 Input: "leetcode"
 Output: "leotcede"

 Note:
 The vowels does not include the letter "y".
 */

public class Solution {
    public String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] str = s.toCharArray();
        while (left < right) {
            if (isVowel(str[left])) {
                if (isVowel(str[right])) {
                    char temp = str[left];
                    str[left] = str[right];
                    str[right] = temp;
                    left++;
                    right--;
                } else {
                    right--;
                }
            } else {
                left++;
            }
        }

        return String.valueOf(str);
    }

    private boolean isVowel(char c) {
        return "aAeEiIoOuU".indexOf(c) != -1;
    }
}
