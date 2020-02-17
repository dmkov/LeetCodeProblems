package array.medium.letter_combinations_of_a_phone_number_17;

import java.util.LinkedList;
import java.util.List;

/**
 17. Letter Combinations of a Phone Number
 https://leetcode.com/problems/letter-combinations-of-a-phone-number/

 Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

 Example:
 Input: "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

 Note:
 Although the above answer is in lexicographical order, your answer could be in any order you want.

 --------

 1. Complexity
    1.1 Time Complexity is O(3^n * 4^m), where n is the number of digits in the input that maps
        to 3 letters (e.g. 2, 3, 4, 5, 6, 8) and m is the number of digits in the input that maps
        to 4 letters (e.g. 7, 9), and N+M is the total number digits in the input.
    1.2 Space Complexity is O(3^n * 4^m)
 2. Approach
    2.1 Idea is to use queue to store all possible string variations. For every character pop existing strings on this level
        and compute new by adding the active character.
 2.2 Implementation
    2.2.1 Check if input string is not empty and create an array with mapped characters.
    2.2.2 Create a queue and populate it with empty string for the beginning.
    2.2.3 For every number in the digit string:
        2.2.3.1 Iterate queue. Pop the string from it
        2.2.3.2 Compute 3 or 4 new string from it by adding a new character
        2.2.3.3 Push new items to the queue
 */

public class QueueSolution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[][] map = new String[][]{
                {}, {},
                {"a", "b", "c"},
                {"d", "e", "f"},
                {"g", "h", "i"},
                {"j", "k", "l"},
                {"m", "n", "o"},
                {"p", "q", "r", "s"},
                {"t", "u", "v"},
                {"w", "x", "y", "z"}
        };

        LinkedList<String> queue = new LinkedList<>();
        queue.add("");
        for (int i = 0; i < digits.length(); i++) {
            int num = Integer.valueOf(String.valueOf(digits.charAt(i)));
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String str = queue.remove();
                for (String ch : map[num]) {
                    queue.add(str + ch);
                }
            }
        }

        return queue;
    }
}
