package string.hard.text_justification_68;

import java.util.ArrayList;
import java.util.List;

/**
 68. Text Justification
 https://leetcode.com/problems/text-justification/

 Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

 Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 Note:
 A word is defined as a character sequence consisting of non-space characters only.
 Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 The input array words contains at least one word.

 Example 1:
 Input:
 words = ["This", "is", "an", "example", "of", "text", "justification."]
 maxWidth = 16
 Output:
     [
         "This    is    an",
         "example  of text",
         "justification.  "
     ]

 --------

 1. Complexity
    1.1 Time Complexity is O(n) where n is the number of characters
    1.2 Space Complexity is O(n) for string builder
 2. Approach
    2.1 The idea is to split process into 1) count and 2) formatting string using selected words when limit is reached
    2.2 Use StringBuilder to append strings
 */

class Solution {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0 || maxWidth < 1) {
            return res;
        }

        int sum = 0;
        int pointer = 0;
        for (int i = 0; i < words.length; i++) {
            int length = words[i].length();
            if (sum + (i - pointer) + length <= maxWidth) {
                sum += length;
            } else {
                res.add(formatString(words, pointer, i - 1, sum, maxWidth, false));
                sum = length;
                pointer = i;
            }
        }
        res.add(formatString(words, pointer, words.length - 1, sum, maxWidth, true));

        return res;
    }

    private String formatString(String[] words, int start, int end, int sum, int maxWidth, boolean isLast) {
        StringBuilder sb = new StringBuilder();
        int totalSpace = maxWidth - sum;

        for (int i = start; i < end; i++) {
            sb.append(words[i]);
            int space = 1;
            if (!isLast) {
                space = totalSpace / (end - i);
                if ((totalSpace % (end - i)) != 0) {
                    space++;
                }
            }
            sb.append(spaces(space));
            totalSpace -= space;
        }

        sb.append(words[end]);
        sb.append(spaces(totalSpace));

        return sb.toString();
    }


    private String spaces(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }


    /**
     1. What type of strings we are given? What is min and max number of characters?
     Can one word be greater then max length?
     2. I should use presented order?
     3. Can array be empty? 0 max width?


         a a a <-- 5
         a  a a <-- 6
         a a a a <-- 7
         abc   <-- 5
         ab

         a a a a a
         |   |

        max capacity = sum of all lengths + num of words - 1

     - put pointer to the first word, set sum = 0, iterate over words:
     - get length of string,
         if sum + num of words - 1 + length <= limit - add length to sum
         otherwise - format string with pointer...i-1 strings
     - set sum = length, set pointer to i
     - format last string
     */
}
