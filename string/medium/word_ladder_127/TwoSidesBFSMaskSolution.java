package string.medium.word_ladder_127;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    2.1 The idea is based on BFS path searching. We store number of steps and then visit this node again
        only if active counter is smaller than the stored value.
    2.2 The main question is how to build a graph. Here we check all pairs and if there is difference
        only in one character - add the link. It is not the most efficient way. Please check approach with '*' masking
    2.3 To improve BFS search in deep graphs we can use two-direction search from start and end points at the same time.
        Doing it level by level when we have intersection in the middle means we found the answer.
 */

class TwoSidesBFSMaskSolution {

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
        if (end == -1) {
            return 0;
        }

        int[] visitedTop = new int[wordList.size()];
        Arrays.fill(visitedTop, Integer.MAX_VALUE);
        visitedTop[start] = 1;
        int[] visitedBottom = new int[wordList.size()];
        Arrays.fill(visitedBottom, Integer.MAX_VALUE);
        visitedBottom[end] = 0;

        LinkedList<Integer> top = new LinkedList<>();
        top.add(start);
        LinkedList<Integer> bottom = new LinkedList<>();
        bottom.add(end);
        int res = 0;
        while(top.size() > 0 && bottom.size() > 0) {
            int bucket = top.size();
            for (int k = 0; k < bucket; k++) {
                int topNode = top.poll();
                List<Integer> childrenTop = new ArrayList<>();
                for (String mask : strToMask.get(topNode)) {
                    childrenTop.addAll(maskToStr.get(mask));
                }

                for (int child : childrenTop) {
                    if (visitedTop[child] > visitedTop[topNode] + 1) {
                        visitedTop[child] = visitedTop[topNode] + 1;
                        top.add(child);

                        if (visitedBottom[child] != Integer.MAX_VALUE) {
                            int sum = visitedTop[child] + visitedBottom[child];
                            res = (res != 0) ? Math.min(res, sum) : sum;
                        }
                    }
                }
            }

            bucket = bottom.size();
            for (int k = 0; k < bucket; k++) {
                int bottomNode = bottom.poll();
                List<Integer> childrenBottom = new ArrayList<>();
                for (String mask : strToMask.get(bottomNode)) {
                    childrenBottom.addAll(maskToStr.get(mask));
                }

                for (int child : childrenBottom) {
                    if (visitedBottom[child] > visitedBottom[bottomNode] + 1) {
                        visitedBottom[child] = visitedBottom[bottomNode] + 1;
                        bottom.add(child);

                        if (visitedTop[child] != Integer.MAX_VALUE) {
                            int sum = visitedTop[child] + visitedBottom[child];
                            res = (res != 0) ? Math.min(res, sum) : sum;
                        }
                    }
                }
            }

            if (res != 0) {
                break;
            }

        }

        return res;
    }

}
