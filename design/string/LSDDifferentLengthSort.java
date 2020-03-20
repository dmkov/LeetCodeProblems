package design.string;

import java.util.Arrays;

public class LSDDifferentLengthSort {

    public String[] sort(String[] arr) {
        // We need to find the max length in the array
        int length = 0;
        for (String str : arr) {
            length = Math.max(length, str.length());
        }

        for (int k = length - 1; k >= 0; k--) {
            // 1. Count the occurrences of characters
            int[] cnt = new int[27 + 1];
            for (String str : arr) {
                cnt[chartAt(str, k) + 2]++;
            }

            // 2. Add previous count to the current element
            for (int i = 1; i < cnt.length; i++) {
                cnt[i] += cnt[i - 1];
            }

            // 3. Populate origin elements to the new array at their positions in cnt arr
            String[] copied = new String[arr.length];
            for (String str : arr) {
                int ch = chartAt(str, k) + 1;
                copied[ cnt[ch] ] = str;
                cnt[ch]++;
            }

            // 4. Copy new arr to original
            arr = copied;
        }
        return arr;
    }

    private int chartAt(String str, int k) {
        if (k >= str.length()) {
            return -1;
        }
        return str.charAt(k) - 'a';
    }

    public String[] copySort(String[] src) {
        String[] arr = sort(Arrays.copyOf(src, src.length));

        return arr;
    }

    public static void main(String[] args) {
        LSDDifferentLengthSort lsdSort = new LSDDifferentLengthSort();
        System.out.println(Arrays.toString(lsdSort.copySort(new String[]{"b", "bd", "ab", "da", "aaa", "aa", "bba"})));
        System.out.println(Arrays.toString(lsdSort.copySort(new String[]{"db", "db", "bbb", "ba", "aaa", "a"})));
        System.out.println(Arrays.toString(lsdSort.copySort(new String[]{"aaa", "a", "b", "dd"})));
    }

}
