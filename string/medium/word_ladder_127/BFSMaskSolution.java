package string.medium.word_ladder_127;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    1.1 Time Complexity is O(n*length), where n is the number of words and length is the number of characters
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 The idea is based on BFS path searching. We store number of steps and then visit this node again
        only if active counter is smaller than the stored value.
    2.2 The main question is how to build a graph. To improve matching - convert every word to all possible
        masks where char is replaced with '*' and then match these masks instead of word processing
 */

class BFSMaskSolution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int depth = 0;

        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) {
            return depth;
        }
        wordList.add(beginWord);

        int start = -1;
        int end = -1;
        Map<Integer, List<String>> strToMask = new HashMap<>();
        Map<String, List<Integer>> maskToStr = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).equals(beginWord)) {
                start = i;
            } else if (wordList.get(i).equals(endWord)) {
                end = i;
            }

            String str = wordList.get(i);
            for (int k = 0; k < str.length(); k++) {
                String mask = ((k > 0) ? str.substring(0, k) : "") + "*"
                        + ((k < str.length() - 1) ? str.substring(k+1) : "");

                List<String> masks = strToMask.getOrDefault(i, new ArrayList<>());
                masks.add(mask);
                strToMask.put(i, masks);

                List<Integer> strs = maskToStr.getOrDefault(mask, new ArrayList<>());
                strs.add(i);
                maskToStr.put(mask, strs);
            }
        }

        int[] visited = new int[wordList.size()];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[start] = 1;

        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> visited[a] - visited[b]);
        heap.add(start);
        while(heap.size() > 0) {
            int node = heap.poll();
            List<Integer> children = new ArrayList<>();
            for (String mask : strToMask.get(node)) {
                children.addAll(maskToStr.get(mask));
            }

            for (int child : children) {
                if (visited[child] > visited[node] + 1) {
                    visited[child] = visited[node] + 1;
                    heap.add(child);
                }
            }
        }

        return (end == -1 || visited[end] == Integer.MAX_VALUE) ? 0 : visited[end];
    }

}
