package design.string;

import java.util.Arrays;

public class LSDFixedLengthSort {

    public String[] sort(String[] arr) {
        // We assume that all strings in the array have same length
        int length = arr[0].length();
        for (int k = length - 1; k >= 0; k--) {
            // 1. Count the occurrences of characters
            int[] cnt = new int[27];
            for (String str : arr) {
                cnt[str.charAt(k) - 'a' + 1]++;
            }

            // 2. Add previous count to the current element
            for (int i = 1; i < cnt.length; i++) {
                cnt[i] += cnt[i - 1];
            }

            // 3. Populate origin elements to the new array at their positions in cnt arr
            String[] copied = new String[arr.length];
            for (String str : arr) {
                char ch = str.charAt(k);
                copied[ cnt[ch - 'a'] ] = str;
                cnt[ch - 'a']++;
            }

            // 4. Copy new arr to original
            arr = copied;
        }
        return arr;
    }

    public String[] copySort(String[] src) {
        String[] arr = sort(Arrays.copyOf(src, src.length));

        return arr;
    }

    public static void main(String[] args) {
        LSDFixedLengthSort lsdSort = new LSDFixedLengthSort();
        System.out.println(Arrays.toString(lsdSort.copySort(new String[]{"bdc", "bda", "abb", "dba", "aaa", "aba", "bba"})));
        System.out.println(Arrays.toString(lsdSort.copySort(new String[]{"dbc", "dcb", "bbb", "bba", "aaa"})));
        System.out.println(Arrays.toString(lsdSort.copySort(new String[]{"aaa", "aba", "bbb", "ddd"})));
    }

}
