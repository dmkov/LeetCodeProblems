package string.hard.integer_to_english_words_273;

/**
 273. Integer to English Words
 https://leetcode.com/problems/integer-to-english-words/

 Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

 Example 1:
 Input: 123
 Output: "One Hundred Twenty Three"

 Example 2:
 Input: 12345
 Output: "Twelve Thousand Three Hundred Forty Five"

 Example 3:
 Input: 1234567
 Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

 Example 4:
 Input: 1234567891
 Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is to cover cases from 1 to 999 and then use it as a pattern for thousands, millions etc.
    2.2 To cover all edge cases we can use string arrays instead of switch/case or ifs to simplify the code.
 3 Implementation
    3.1 Return "Zero" if number is zero.
    3.2 Otherwise, check if it is greater than 1_000_000_000 (max available number "digit").
        3.2.1 If yes, get the integer number of billions and call helper method getNumber for 1..999 representation
        3.2.2 Reduce number for next calculations as num = num % 1_000_000_000
    3.3 Repeat same operation for 1_000_000, 1_000 and 0..999 intervals and concat it to the result.
    3.4 In getNumber() helper method:
        3.4.1 Check if number is between 1 and 19 - return the edge case strings from the array
        3.4.2 If number is between 20 and 99 - get the first part from stored 'tens' strings and add 3.4.1 for the rest if needed
        3.4.3 For the rest 100..999 numbers - get the first part from 3.4.1 with 'Hundred' label, then add rest from 3.4.2 if needed
 */

class Solution {
    private final String[] upTo20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        String result = "";
        if (num >= 1_000_000_000) {
            result += getBillions(num / 1_000_000_000);
            num = num % 1_000_000_000;
        }
        if (num >= 1_000_000) {
            result += ((result.length() > 0) ? " " : "") + getMillions(num / 1_000_000);
            num = num % 1_000_000;
        }
        if (num >= 1_000) {
            result += ((result.length() > 0) ? " " : "") + getThousands(num / 1_000);
            num = num % 1_000;
        }
        if (num > 0) {
            result += ((result.length() > 0) ? " " : "") + getNumber(num);
        }

        return result;
    }

    private String getNumber(int num) {
        // 1 .. 999
        if (num < 20) {
            return upTo20[num];
        } else if (num < 100) {
            return getTens(num);
        } else {
            return upTo20[num / 100] + " Hundred" + ((num % 100 > 0) ? (" " + getNumber(num % 100)) : "");
        }
    }

    private String getTens(int num) {
        String res = tens[num / 10];
        int n = (num / 10) * 10; // 20, 30, 40 ... 90
        if ((num % n) != 0) {
            res += " " + upTo20[num % n];
        }

        return res;
    }


    private String getBillions(int num) {
        return getNumber(num) + " Billion";
    }

    private String getMillions(int num) {
        return getNumber(num) + " Million";
    }

    private String getThousands(int num) {
        return getNumber(num) + " Thousand";
    }
}
