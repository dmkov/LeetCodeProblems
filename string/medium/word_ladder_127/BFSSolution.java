package string.medium.word_ladder_127;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 127. Word Ladder
 https://leetcode.com/problems/word-ladder/

 Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
    - Only one letter can be changed at a time.
    - Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

 Note:
 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.

 Example 1:
 Input:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 Output: 5

 Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.

 Example 2:
 Input:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 Output: 0

 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 --------

 1. Complexity
    1.1 Time Complexity is O(n^2*length), where n is the number of words and length is the number of characters
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is based on BFS or Dijkstra path searching. We store number of steps and then visit this node again
        only if active counter is smaller than the stored value.
    2.2 The main question is how to build a graph. Here we check all pairs and if there is difference
        only in one character - add the link. It is not the most efficient way. Please check approach with '*' masking
 */

class BFSSolution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int depth = 0;

        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) {
            return depth;
        }
        wordList.add(beginWord);

        List<Integer>[] grid = new List[wordList.size()];
        for (int i = 0; i < wordList.size(); i++) {
            grid[i] = new LinkedList<>();
        }

        int start = -1;
        int end = -1;
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).equals(beginWord)) {
                start = i;
            } else if (wordList.get(i).equals(endWord)) {
                end = i;
            }

            for (int j = i+1; j < wordList.size(); j++) {
                if (canTransform(wordList.get(i), wordList.get(j))) {
                    grid[i].add(j);
                    grid[j].add(i);
                }
            }
        }

        int[] visited = new int[wordList.size()];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[start] = 1;

        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> visited[a] - visited[b]);
        heap.add(start);
        while(heap.size() > 0) {
            int node = heap.poll();
            for (int child : grid[node]) {
                if (visited[child] > visited[node] + 1) {
                    visited[child] = visited[node] + 1;
                    heap.add(child);
                }
            }
        }

        return (end == -1 || visited[end] == Integer.MAX_VALUE) ? 0 : visited[end];
    }

    private boolean canTransform(String s1, String s2) {
        int p = 0;
        boolean flag = false;
        while (p < s1.length()) {
            if (s1.charAt(p) == s2.charAt(p)) {
                p++;
            } else if (!flag) {
                p++;
                flag = true;
            } else {
                return false;
            }
        }

        return true;
    }

}
