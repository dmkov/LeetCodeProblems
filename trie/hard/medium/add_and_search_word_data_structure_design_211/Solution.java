package trie.hard.medium.add_and_search_word_data_structure_design_211;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 211. Add and Search Word - Data structure design
 https://leetcode.com/problems/add-and-search-word-data-structure-design/

 Design a data structure that supports the following two operations:
 void addWord(word)
 bool search(word)

 search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

 Example:
 addWord("bad")
 addWord("dad")
 addWord("mad")
 search("pad") -> false
 search("bad") -> true
 search(".ad") -> true
 search("b..") -> true

 --------

 1. Complexity
    1.1 Time Complexity is O(n) to add the word,
        O(m) to search where m is all characters in the tree in the worst case
    1.2 Space Complexity is O(m)
 2. Approach
    2.1 Construct a trie from all words and save the boolean flag at the end node.
    2.2 Then check all combinations according to the search pattern
 */

class Solution {
    class Node {
        Map<Character, Node> map = new HashMap<>();
        boolean end = false;
    }

    Node root;

    /** Initialize your data structure here. */
    public Solution() {
        this.root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        addWord(root, word, 0);
    }

    private void addWord(Node node, String word, int pos) {
        char ch = word.charAt(pos);

        Node next = node.map.getOrDefault(ch, new Node());
        node.map.put(ch, next);
        if (pos == word.length() - 1) {
            next.end = true;
        } else {
            addWord(next, word, pos + 1);
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(Node node, String word, int pos) {
        if (pos == word.length()) {
            return node.end;
        }
        char ch = word.charAt(pos);

        if (ch == '.') {
            for (Node child : node.map.values()) {
                if (search(child, word, pos + 1)) {
                    return true;
                }
            }
        } else {
            Node child = node.map.get(ch);
            if (child != null && search(child, word, pos + 1)) {
                return true;
            }
        }
        return false;
    }
}
