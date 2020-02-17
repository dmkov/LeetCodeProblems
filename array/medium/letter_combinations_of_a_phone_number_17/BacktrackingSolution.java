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
    2.1 Idea is to use backtracking for every possible combination - for every string execute method with adding a new
        character to it.
 2.2 Implementation
    2.2.1 Check if input string is not empty and create an array with mapped characters.
    2.2.2 Starting from the first position compute strings from mapped characters and execute method recursively
        for every next position.
    2.2.3 If the last character was added, save string to the result list.
 */

public class BacktrackingSolution {
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
        populateCombinations("", 0, digits, map, result);

        return result;
    }

    private void populateCombinations(String str, int pos, String digits, String[][] map, List<String> result) {
        if (pos == digits.length()) {
            result.add(str);
            return;
        }
        int num = Integer.parseInt(String.valueOf(digits.charAt(pos)));
        for (String ch : map[num]) {
            populateCombinations(str + ch, pos + 1, digits, map, result);
        }
    }
}
