package string.easy.count_and_say_38;

/**
 38. Count and Say
 https://leetcode.com/problems/count-and-say/

 The count-and-say sequence is the sequence of integers with the first five terms as following:
 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221

 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.

 Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

 Note: Each term of the sequence of integers will be represented as a string.

 Example 1:
 Input: 1
 Output: "1"

 Example 2:
 Input: 4
 Output: "1211"
 */

public class Solution {
    public static String countAndSay(int n) {
        String result = "1";
        for (int i = 2; i <= n; i++) {
            result = convertString(result);
        }
        return result;
    }

    private static String convertString(String str) {
        StringBuilder result = new StringBuilder();
        char[] arr = str.toCharArray();
        char match = arr[0];
        int counter = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (match != arr[i + 1]) {
                result.append(counter).append(match);
                counter = 1;
                match = arr[i + 1];
            } else {
                counter++;
            }
        }
        result.append(counter).append(match);

        return result.toString();
    }


    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }
}
