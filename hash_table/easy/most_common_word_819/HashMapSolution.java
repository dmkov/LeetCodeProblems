package hash_table.easy.most_common_word_819;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 819. Most Common Word
 https://leetcode.com/problems/most-common-word/

 Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

 Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

 Example:
 Input:
 paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 banned = ["hit"]
 Output: "ball"
 Explanation:
 "hit" occurs 3 times, but it is a banned word.
 "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 Note that words in the paragraph are not case sensitive,
 that punctuation is ignored (even if adjacent to words, such as "ball,"),
 and that "hit" isn't the answer even though it occurs more because it is banned.

 Note:
 1 <= paragraph.length <= 1000.
 0 <= banned.length <= 100.
 1 <= banned[i].length <= 10.
 The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
 paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 There are no hyphens or hyphenated words.
 Words only consist of letters, never apostrophes or other punctuation symbols.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 The idea is to split paragraph by non-string characters and then count the number of words.
 3. Implementation
     3.1 Check if given paragraph string is not empty.
     3.1 Use split functions with regexp including all special characters.
     3.2 Iterate words array, skip empty and banned strings but count occurrences of others
 */

class HashMapSolution {
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) {
            return "";
        }

        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> result = new HashMap<>();

        String[] words = paragraph.toLowerCase().split("[!?',;. ]");
        for (String w : words) {
            if (w.length() > 0 && !ban.contains(w)) {
                result.put(w, result.getOrDefault(w, 0) + 1);
            }
        }

        int max = 0;
        String str = "";
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                str = entry.getKey();
            }
        }

        return str;
    }
}
