package string.hard.palindrome_pairs_336;

import java.util.ArrayList;
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
    2.1 The approach here is to build a trie with reversed strings, then we iterate over all string in the list
        and check the trie.
    2.2 If there is a word of the same length - we have mirror pairs
        If there is some ends - check the rest of the word to be a palindrome
        If there is some remainings - check that they are palindromes
            In all success cases - we will have pair
 */

class TrieSolution {

    class TrieNode {
        TrieNode[] chars = new TrieNode[256];
        int indx = -1;
        List<Integer> followed = new ArrayList<>();
    }

    TrieNode root = new TrieNode();

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new LinkedList<>();
        if (words == null || words.length == 0) {
            return res;
        }

        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            TrieNode node = root;
            root.followed.add(i);
            for (int k = str.length() - 1; k >= 0; k--) {
                char ch = str.charAt(k);
                if (node.chars[ch] == null) {
                    node.chars[ch] = new TrieNode();
                }
                node = node.chars[ch];
                if (k == 0) {
                    node.indx = i;
                } else {
                    node.followed.add(i);
                }
            }
        }
        //abc , ab, ba, a
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            TrieNode node = root;

            for (int k = 0; k < str.length(); k++) {
                char ch = str.charAt(k);
                if (node.chars[ch] == null) {
                    node = null;
                    break;
                }
                node = node.chars[ch];

                if (node.indx != -1 && node.indx != i
                        && (k == str.length() - 1 || isPalindrome(str.substring(k + 1, str.length())))) {
                    res.add(Arrays.asList(i, node.indx));
                }
            }

            if (node != null) {
                for (Integer pos : node.followed) {
                    int size = words[pos].length() - str.length();
                    String remaining = words[pos].substring(0, size);
                    if (isPalindrome(remaining) && !pos.equals(i)) {
                        res.add(Arrays.asList(i, pos));
                        if (words[i].length() == 0) {
                            res.add(Arrays.asList(pos, i));
                        }
                    }
                }
            }

        }

        return res;
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

 4. trie approach
     1. put reverse words into a trie, if word is not ended - keep list of indexes
     2. lookup word in the trie:
         - if there is no char: continue to the next word
         - otherwise:
             if char is end position:
                 if the current pos matches the length -> add pair
                 if rest of the word is palindrome -> add pair
         - no more chars in the word:
         look at list of followed indexes -> if substring(0, length-k) is palindrome -> add a pair

 */
