package design.hard.design_search_autocomplete_system_642;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 642. Design Search Autocomplete System
 https://leetcode.com/problems/design-search-autocomplete-system/

 Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

 The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 If less than 3 hot sentences exist, then just return as many as you can.
 When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 Your job is to implement the following functions:

 The constructor function:
 AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

 Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.

 Example:
 Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 The system have already tracked down the following sentences and their corresponding times:
 "i love you" : 5 times
 "island" : 3 times
 "ironman" : 2 times
 "i love leetcode" : 2 times
 Now, the user begins another search:

 Operation: input('i')
 Output: ["i love you", "island","i love leetcode"]
 Explanation:
 There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

 Operation: input(' ')
 Output: ["i love you","i love leetcode"]
 Explanation:
 There are only two sentences that have prefix "i ".

 Operation: input('a')
 Output: []
 Explanation:
 There are no sentences that have prefix "i a".

 Operation: input('#')
 Output: []
 Explanation:
 The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.

 --------

 1. Complexity
    1.1 Time Complexity is O(n*length) for constructor and O(klogk) for the input,
        where k is the number of linked phrases
    1.2 Space Complexity is huge for large strings :)
 2. Approach
    2.1 The idea is to use Trie with storing linked strings for each node
    2.2 Separately we keep map with strings and counters. Every time we need to pop result - sort existing list
        of strings for the node based on map counters and return the first 3 items
 */

class TrieSolution {

    Node root;
    Node pointer;
    StringBuilder sb;
    Map<String, Integer> phrases;

    class Node {
        Node[] chars;
        List<String> strings;
        public Node() {
            chars = new Node[27];
            strings = new ArrayList<>();
        }

        public List<String> getResult() {
            List<String> res = new ArrayList<>();

            Collections.sort(strings, (a,b) -> {
                if (phrases.get(a).equals(phrases.get(b))) {
                    return a.compareTo(b);
                }
                return phrases.get(b) - phrases.get(a);
            });
            for (int i = 0; i < Math.min(3, strings.size()); i++) {
                res.add(strings.get(i));
            }

            return res;
        }
    }

    public TrieSolution(String[] sentences, int[] times) {
        root = new Node();
        pointer = root;
        sb = new StringBuilder();
        phrases = new HashMap<>();
        for (int i = 0; i < sentences.length; i++) {
            addSentence(sentences[i], 0, root);
            phrases.put(sentences[i], phrases.getOrDefault(sentences[i], 0) + times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();

        if (c == '#') {
            String toAdd = sb.toString();
            if (toAdd.length() > 0) {
                addSentence(toAdd, 0, root);
                phrases.put(toAdd, phrases.getOrDefault(toAdd, 0) + 1);
            }
            sb = new StringBuilder();
            pointer = root;
        } else {
            sb.append(c);
            int indx = getIndx(c);
            if (pointer.chars[indx] == null) {
                pointer.chars[indx] = new Node();
            }
            pointer = pointer.chars[indx];
            res = pointer.getResult();
        }

        return res;
    }

    private void addSentence(String str, int pos, Node node) {
        addString(str, node);
        if (pos == str.length()) {
            return;
        }

        int indx = getIndx(str.charAt(pos));
        if (node.chars[indx] == null) {
            node.chars[indx] = new Node();
        }
        addSentence(str, pos + 1, node.chars[indx]);
    }

    private void addString(String str, Node node) {
        boolean found = false;
        for (String s : node.strings) {
            if (s.equals(str)) {
                found = true;
                break;
            }
        }
        if (!found) {
            node.strings.add(str);
        }
    }

    private int getIndx(char ch) {
        return (ch == ' ') ? 26 : (ch - 'a');
    }

    public static void main(String[] args) {
        TrieSolution obj = new TrieSolution(
                new String[]{"i love you","island","iroman","i love leetcode"},
                new int[]{5,3,2,2}
        );
        List<String> p1 = obj.input('i');
        List<String> p2 = obj.input(' ');
        List<String> p3 = obj.input('a');
        List<String> p4 = obj.input('#');
        List<String> p5 = obj.input('i');
        List<String> p6 = obj.input(' ');
        List<String> p7 = obj.input('a');
        List<String> p8 = obj.input('#');
        List<String> p9 = obj.input('i');
        List<String> p10 = obj.input(' ');
        List<String> p11 = obj.input('a');
        List<String> p12 = obj.input('#');
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */


