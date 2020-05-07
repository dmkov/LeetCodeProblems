package string.hard.guess_the_word_843;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 843. Guess the Word
 https://leetcode.com/problems/guess-the-word/

 This problem is an interactive problem new to the LeetCode platform.

 We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

 You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

 This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

 For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

 Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

 Example 1:
 Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

 Explanation:
 master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

 We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 Note:  Any solutions that attempt to circumvent the judge will result in disqualification.

 --------

 1. Complexity
    1.1 Time Complexity is O(n^2)
    1.2 Space Complexity is O(n)
 2. Approach
    https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison
    2.1 The idea is maximise probability of possible options. Since the most of strings will have 0 matching
        we need to select the one that has the least matched coefficient with other numbers.

  */

class Solution {

    interface Master {
        int guess(String word);
    }

    public void findSecretWord(String[] wordlist, Master master) {
        if (wordlist == null || master == null) {
            throw new IllegalArgumentException();
        }

        int res = 0;
        int attempts = 0;
        while (res != 6 && attempts < 10) {
            String matcher = getMatcher(wordlist);
            res = master.guess(matcher);
            if (res != 6) {
                wordlist = findMatchingWordList(wordlist, matcher, res);
            }
            attempts++;
        }
    }

    private String getMatcher(String[] wordlist) {
        Map<String, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        String minWord = wordlist[0];

        for (String word : wordlist) {
            int counter = 0;
            for (String word2 : wordlist) {
                if (!word.equals(word2) && getDiff(word, word2) == 0) {
                    counter++;
                }
            }
            if (min > counter) {
                min = counter;
                minWord = word;
            }
        }

        return minWord;
    }

    private String[] findMatchingWordList(String[] wordlist, String matcher, int num) {
        List<String> result = new ArrayList<>();
        for (String word : wordlist) {
            if (getDiff(matcher, word) == num) {
                result.add(word);
            }
        }
        String[] arr = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }

        return arr;
    }

    private int getDiff(String word1, String word2) {
        int res = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                res++;
            }
        }
        return res;
    }
}


/**
     1. list of words, 6 characters in each
     2. one of the words is secret
     3. 100 test cases, 10 attempts


     1. what possible characters in the string? is it loowercase letters?
     2. we have list of 100 words always?
     3. and match means technically - matching position and the value? if value is not on the same place it is not a match?
     4. if result is 6 - should I proceed querying or just end the method?
     5. btw, can we have duplicates?
     6. can it be no answer?

 1. Naive solution:
     Pick a ranfom word, get the result from API
         filter remaining words by matched argument
         consider filtered result as input list and repeat process until answer from API is 6

     The questions is how to narrow down the list of words, find the same matched number of characters?
        - compare all words in the list with the picked one -> O(n * 6)

 2. Improved approach
     - To pick a word, instead of randomisation select the word that has the less 0 matchings with remaining list.

 */
