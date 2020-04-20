package string.hard.palindrome_pairs_336;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 336. Palindrome Pairs
 https://leetcode.com/problems/palindrome-pairs/

 Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

 Example 1:
 Input: ["abcd","dcba","lls","s","sssll"]
 Output: [[0,1],[1,0],[3,2],[2,4]]
 Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

 Example 2:
 Input: ["bat","tab","cat"]
 Output: [[0,1],[1,0]]
 Explanation: The palindromes are ["battab","tabbat"]

 --------

 1. Complexity
    1.1 Time Complexity is O(str.length()*str.length()*words)
    1.2 Space Complexity is O(word) or O(word*word) if we count result
 2. Approach
    https://leetcode.com/problems/palindrome-pairs/solution/
    2.1 The idea is to check every string and get suffixes and prefixes around the palindrome part of the word.
    2.2 After getting these suffixes and prefixes we can easily reverse them and check if reversed version exists
        in pre-cached words in the hash map.
 */

class HashMapSolution {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new LinkedList<>();
        if (words == null || words.length == 0) {
            return res;
        }

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        //abc , ab, ba, a
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            // 1. Get reversed version
            Integer pos = map.get(reverse(str));
            if (pos != null && !pos.equals(i)) {
                res.add(Arrays.asList(i, pos));
            }

            // 2. Append smaller word
            String check = "";
            for (int k = str.length() - 1; k > 0; k--) {
                check = str.charAt(k) + check;
                if (isPalindrome(check)) {
                    pos = map.get(reverse(str.substring(0, k)));
                    if (pos != null && !pos.equals(i)) {
                        res.add(Arrays.asList(i, pos));
                    }
                }
            }

            // 3. Prepend smaller word
            check = "";
            for (int k = 0; k < str.length() - 1; k++) {
                check = check + str.charAt(k);
                if (isPalindrome(check)) {
                    pos = map.get(reverse(str.substring(k + 1, str.length())));
                    if (pos != null && !pos.equals(i)) {
                        res.add(Arrays.asList(pos, i));
                    }
                }
            }

            // 4. Check empty string
            if (isPalindrome(str)) {
                pos = map.get("");
                if (pos != null && !pos.equals(i)) {
                    res.add(Arrays.asList(i, pos));
                    res.add(Arrays.asList(pos, i));
                }
            }
        }

        return res;
    }

    private String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    private boolean isPalindrome(String str) {
        if (str.length() < 2) {
            return true;
        }

        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

/**

 1. So the word itself is not a palindrome but if we add another word to it, it becames palindrome?
 2. And we add only one word, not two or three?
 3. Are all words have the same length?
 4. And palindrome - the word that looks from both directions identically?
 5. Is it ASCII string? What is the possible range of characters?
 - What is the max possible length of the string? Can we have empty strings?
 6. What is the max/min length of the given list?
 7. All indexes start from 0?


 "abc", "ba", "b", "ab", "k" -> 0,1   1,2   2,3   1,3    3,1
 baca ? b, cab acab

 baca



 1. brute force - O(n^2 * str.length) -

 2. for every string add element to the end - check if it is palindrome
     str.length * str.length * n  - more efficient with small words
     --- does not work because of "s" -> "lls" = slls ---

 3.   abc -> "ba", "cba", "..cba"
      "cb", "cba", "cb..." <- abc

     1. iterate strings - put to hash map <string, position>
     2. iterate strings:
         from end to start:
            if passed string is palindrome - reverse remaining part and check values from map
         from start to end:
            if passed string is palindrome - reverse remaining part and check values from map
         O(str1.length*str1.length*words)

 */
