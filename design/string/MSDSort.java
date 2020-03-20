package design.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MSDSort {

    // This is iterative solution.
    // It can be replaced with the more simple recursive approach

    public String[] sort(String[] arr) {
        // We need to find the max length in the array
        int length = 0;
        for (String str : arr) {
            length = Math.max(length, str.length());
        }

        List<int[]> ranges = new ArrayList<>();
        ranges.add(new int[]{0, arr.length - 1});

        for (int k = 0; k < length; k++) {
            List<int[]> newRanges = new ArrayList<>();
            for (int[] range: ranges) {
                int left =  range[0];
                int right = range[1];
                if (left >= right) {
                    continue;
                }

                // 1. Count the occurrences of characters
                int[] cnt = new int[27 + 1];
                for (int i = left; i <= right; i++) {
                    cnt[chartAt(arr[i], k) + 2]++;
                }

                // 2. Add previous count to the current element
                for (int i = 1; i < cnt.length; i++) {
                    cnt[i] += cnt[i - 1];

                    // Add a new sub range to the list
                    if (cnt[i] - cnt[i - 1] > 1) {
                        newRanges.add(new int[]{cnt[i - 1], cnt[i] - 1});
                    }
                }

                // 3. Populate origin elements to the new array at their positions in cnt arr
                String[] copied = new String[arr.length];
                for (int i = left; i <= right; i++) {
                    int ch = chartAt(arr[i], k) + 1;
                    copied[ cnt[ch] ] = arr[i];
                    cnt[ch]++;
                }

                // 4. Copy new arr to original
                for (int i = left; i <= right; i++) {
                    arr[i] = copied[i - left];
                }
            }

            ranges = newRanges;
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
        MSDSort msdSort = new MSDSort();
        System.out.println(Arrays.toString(msdSort.copySort(new String[]{"b", "bd", "ab", "da", "aaa", "aa", "bba"})));
        System.out.println(Arrays.toString(msdSort.copySort(new String[]{"db", "db", "bbb", "ba", "aaa", "a"})));
        System.out.println(Arrays.toString(msdSort.copySort(new String[]{"aaa", "a", "b", "dd"})));
    }

}
