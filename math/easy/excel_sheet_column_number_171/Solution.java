package math.easy.excel_sheet_column_number_171;

/**
 171. Excel Sheet Column Number
 https://leetcode.com/problems/excel-sheet-column-number/

 Given a column title as appear in an Excel sheet, return its corresponding column number.

 For example:
 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28
 ...

 Example 1:
 Input: "A"
 Output: 1

 Example 2:
 Input: "AB"
 Output: 28

 Example 3:
 Input: "ZY"
 Output: 701

---

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 The idea is based on ASCII codes for every character (A starts at 65, Z is 90)
    2.2 Iterate through string and sum up every time - 26^n * (ch - 64)

 */
public class Solution {
    public static int ALPHA_LETTERS = 26;
    public static int ASCII_SHIFT = 64;

    public int titleToNumber(String s) {
        int result = 0;
        int pow = 1;

        s = s.toUpperCase();
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            result += pow * (ch - ASCII_SHIFT);
            pow = pow * ALPHA_LETTERS;
        }
        return result;
    }
}
