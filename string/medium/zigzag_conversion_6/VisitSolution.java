package string.medium.zigzag_conversion_6;

/**
 6. ZigZag Conversion
 https://leetcode.com/problems/zigzag-conversion/

 The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
     P   A   H   N
     A P L S I I G
     Y   I   R

 And then read line by line: "PAHNAPLSIIGYIR"

 Write the code that will take a string and make this conversion given a number of rows:
 string convert(string s, int numRows);

 Example 1:
 Input: s = "PAYPALISHIRING", numRows = 3
 Output: "PAHNAPLSIIGYIR"

 Example 2:
 Input: s = "PAYPALISHIRING", numRows = 4
 Output: "PINALSIGYAHRPI"
 Explanation:
     P     I    N
     A   L S  I G
     Y A   H R
     P     I

 --------

 1. Complexity
    1.1 Time Complexity is O(n), where n is the number of characters
    1.2 Space Complexity is O(n) for string builder
 2. Approach
    2.1 We can calculate position of every character, so visit them like we output the strings
 */

class VisitSolution {

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || s.length() <= numRows || numRows <= 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        int bucket = (numRows - 1)*2; // 4
        for (int i = 0; i < numRows; i++) {
            int shift = 0;
            while (shift < s.length()) {
                if (shift + i < s.length()) {
                    sb.append(s.charAt(shift + i));
                }
                if (i > 0 && i < (numRows-1) && (shift + bucket - i) < s.length()) {
                    sb.append(s.charAt(shift + bucket - i));
                }
                shift += bucket;
            }
        }

        return sb.toString();
    }

    /**
     1. Is it ASCII string? Any characters with codes froom 0 to 255 allowed?
     2. And output is also a string? What should I return if number of rows is bigger than string length?


     PAYPALISHIRING

     P   A   H   N   --->> PAHNAPLSIIGYIR
     A P L S I I G
     Y   I   R


     P     I    N    --->> PINALSIGYAHRPI
     A   L S  I G
     Y A   H R
     P     I


     1st - 0,                (n-1)*2,                 (n-1)*4
     2nd - 1,  (n-1)*2-1     (n-1)*2+1   (n-1)*4-1    (n-1)*4 + 1
     3rd - 2,  (n-1)*2-2.    (n-1)*2+2   (n-1)*4-2
     4th - 3                 (n-1)*2+3

     */
}
