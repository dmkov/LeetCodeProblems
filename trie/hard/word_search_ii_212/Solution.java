package trie.hard.word_search_ii_212;

import java.util.LinkedList;
import java.util.List;

/**
 212. Word Search II
 https://leetcode.com/problems/word-search-ii/

 Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 Example:
 Input:
 board = [
     ['o','a','a','n'],
     ['e','t','a','e'],
     ['i','h','k','r'],
     ['i','f','l','v']
 ]
 words = ["oath","pea","eat","rain"]
 Output: ["eat","oath"]

 Note:
 All inputs are consist of lowercase letters a-z.
 The values of words are distinct.

 --------

 1. Complexity
    1.1 Time Complexity is O(m * n * wl * l) = max(O(wl * l), O(m * n * l * wl)) where
        O(wl * l) - Build the trie
        O(m * n * wl * l) - In the worst case where all words start with different chracters, and there is a word starting with a character
        in the cell board[m - 1][n - 1], we have O(m * n * wl * l). However, if there are words starting with same characters and paths sharing
        cells, Trie can check multiple words when DFS from a certain cell, rather than check ONLY ONE word when DFS from a certain cell like the
        brute-force solution.
    1.2 Space Complexity is O(wl * l) = max(O(wl), O(wl * l))
 2. Approach
    2.1 Construct a trie from all words and save the word itself at the end node. Then check all combinations in the grid
        and if trace with trie is possible, add a saved word to the result list.
 2.2 Implementation
    2.2.1 Check if given arrays are valid.
    2.2.2 Create a Trie for all words. Additionally to path, save the final word at the last node. Store the pointer for the root element.
    2.2.3 Create a visited array and traverse all elements in the grid:
        2.2.3.1 If indexes do not cross grid bound and element was not visited before, check if it exists in the Trie
            at the current step. Return if not.
        2.2.3.2 Otherwise, check if it is the end of the word. If it is - store it to the result list.
        2.2.3.3 Set visited flag and call method recursively for all 4 possible directions. At the end - restore visited flag.
 */

class Solution {
    class TrieNode {
        TrieNode[] chars = new TrieNode[26];
        String word = null;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new LinkedList<>();
        if (board == null || board.length == 0 || board[0] == null || words == null || words.length == 0) {
            return res;
        }

        int n = board.length;
        int m = board[0].length;

        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                int ch = word.charAt(i) - 'a';
                if (node.chars[ch] == null) {
                    node.chars[ch] = new TrieNode();
                }
                node = node.chars[ch];
            }
            node.word = word;
        }

        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                traceGrid(i, j, board, root, visited, res);
            }
        }

        return res;
    }

    private void traceGrid(int i, int j, char[][] board, TrieNode node, boolean[][] visited, List<String> res) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length || visited[i][j]) {
            return;
        }

        int ch = board[i][j] - 'a';
        if (node.chars[ch] == null) {
            return;
        }

        node = node.chars[ch];
        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }

        visited[i][j] = true;
        traceGrid(i - 1, j, board, node, visited, res);
        traceGrid(i + 1, j, board, node, visited, res);
        traceGrid(i, j - 1, board, node, visited, res);
        traceGrid(i, j + 1, board, node, visited, res);
        visited[i][j] = false;
    }
}
