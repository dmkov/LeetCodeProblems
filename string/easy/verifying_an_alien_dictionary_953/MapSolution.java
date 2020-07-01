package string.easy.verifying_an_alien_dictionary_953;

/**
 953. Verifying an Alien Dictionary
 https://leetcode.com/problems/verifying-an-alien-dictionary/

 In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

 Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 Example 1:
 Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 Output: true
 Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

 Example 2:
 Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 Output: false
 Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

 Example 3:
 Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 Output: false
 Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).

---

 1. Complexity
    1.1 Time Complexity is O(n) where n is the content of all words (in other words size*length)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Use array to weight every character in the order string and then compare all strings char by char
 */

class MapSolution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || order == null || words.length == 0 || order.length() == 0) {
            return false;
        }

        int[] map = new int[26];
        for (int i = 0; i < order.length(); i++) {
            map[order.charAt(i) - 'a'] = i;
        }

        for (int i = 1; i < words.length; i++) {
            if (!smaller(words[i - 1], words[i], map)) {
                return false;
            }
        }
        return true;
    }

    private boolean smaller(String s1, String s2, int[] map) {
        for (int i = 0; i < s1.length(); i++) {
            if (s2.length() <= i) {
                return false;
            }

            int res = map[s1.charAt(i) - 'a'] - map[s2.charAt(i) - 'a'];
            if (res < 0) {
                break;
            } else if (res > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     1. So I do not need to sort, but I need to return true or false if list is sorted?
     2. What is the input format? Is it an array of words and string for order?
     3. Is it garantee that all characters from words are presented in order string?
     4. Are there possible duplicates in the order string? Is it lower case string?

     "hlabcdefgijkmnopqrstuvwxyz"

     */
}
