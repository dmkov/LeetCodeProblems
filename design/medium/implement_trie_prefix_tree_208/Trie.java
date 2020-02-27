package design.medium.implement_trie_prefix_tree_208;

/**
 208. Implement Trie (Prefix Tree)
 https://leetcode.com/problems/implement-trie-prefix-tree/

 Implement a trie with insert, search, and startsWith methods.

 Example:
 Trie trie = new Trie();
 trie.insert("apple");
 trie.search("apple");   // returns true
 trie.search("app");     // returns false
 trie.startsWith("app"); // returns true
 trie.insert("app");
 trie.search("app");     // returns true

 Note:
 You may assume that all inputs are consist of lowercase letters a-z.
 All inputs are guaranteed to be non-empty strings.

 --------

 1. Complexity
     1.1 Time Complexity is O(n) where n is the number of characters
     1.2 Space Complexity is O(26^n) for the full trie
 2. Approach
     2.1 The idea behind the solution is to use bucket array to store all possible characters for 1 step. The next object
        contains another bucket array for the second possible character... and so on.
        To mark complete word we use additional boolean flag in the node object and set it when is it the last character in the word.
 */

class Trie {

    class Node {
        Node[] chars = new Node[26];
        boolean complete = false;
    }

    private Node root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.chars[ch - 'a'] == null) {
                node.chars[ch - 'a'] = new Node();
            }
            node = node.chars[ch - 'a'];
        }
        node.complete = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.chars[ch - 'a'] == null) {
                return false;
            }
            node = node.chars[ch - 'a'];
        }
        return node.complete;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (node.chars[ch - 'a'] == null) {
                return false;
            }
            node = node.chars[ch - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
