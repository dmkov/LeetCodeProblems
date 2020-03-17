package design.string;

import java.util.Arrays;

public class SuffixArray {

    public int numberOfOccurrences(String text, String search) {
        // 1. Populate suffix array
        String[] suffixArray = new String[text.length()];
        StringBuilder sb = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            sb.insert(0, text.charAt(i));
            suffixArray[i] = sb.toString();
        }

        // 2. Radix sort suffixes
        Arrays.sort(suffixArray);

        // 3. Binary search
        int[] limits = new int[]{0, suffixArray.length - 1};
        int pos = 0;
        while (limits != null && pos < search.length()) {
            limits = search(suffixArray, limits, search, pos);
            pos++;
        }

        // 4. Output results
        int res = 0;
        if (limits != null) {
            res = limits[1] - limits[0] + 1;
        }
        return res;
    }

    private int[] search(String[] suffixArray, int[] limits, String search, int pos) {
        int ch = search.charAt(pos);
        int left = limits[0];
        int right = limits[1];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int check = charAt(suffixArray[mid], pos);
            if (check == ch) {
                left = mid;
                while (left > limits[0] && charAt(suffixArray[left - 1], pos) == ch) {
                    left--;
                }
                right = mid;
                while (right < limits[1] && charAt(suffixArray[right + 1], pos) == ch) {
                    right++;
                }
                return new int[]{left, right};
            } else if (check < ch) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    private int charAt(String str, int pos) {
        if (pos > str.length() - 1) {
            return -1;
        }
        return str.charAt(pos);
    }

    public static void main(String[] args) {
        SuffixArray suffixArray = new SuffixArray();
        System.out.println("Result: 2 = " + suffixArray.numberOfOccurrences("abbcdacbdbcdead", "bcd"));
        System.out.println("Result: 0 = " + suffixArray.numberOfOccurrences("abbcdbsdsdsdswwqa", "www"));
        System.out.println("Result: 5 = " + suffixArray.numberOfOccurrences("aaaaa", "a"));
        System.out.println("Result: 4 = " + suffixArray.numberOfOccurrences("aaaaa", "aa"));
        System.out.println("Result: 2 = " + suffixArray.numberOfOccurrences("abaab", "ab"));
        System.out.println("Result: 1 = " + suffixArray.numberOfOccurrences("abc", "abc"));
    }

}
