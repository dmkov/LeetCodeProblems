package design.string;

import java.util.Arrays;

public class MSDRecursiveSort {

    public String[] sort(String[] arr) {
        sort(arr, new String[arr.length], 0, arr.length - 1, 0);

        return arr;
    }

    private void sort(String[] arr, String[] copied, int left, int right, int d) {
        if (left >= right) {
            return;
        }

        // 1. Count the occurrences of characters
        int[] cnt = new int[27 + 1];
        for (int i = left; i <= right; i++) {
            cnt[chartAt(arr[i], d) + 2]++;
        }

        // 2. Add previous count to the current element
        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }

        // 3. Populate origin elements to the new array at their positions in cnt arr
        for (int i = left; i <= right; i++) {
            int ch = chartAt(arr[i], d) + 1;
            copied[ cnt[ch] ] = arr[i];
            cnt[ch]++;
        }

        // 4. Copy new arr to original
        for (int i = left; i <= right; i++) {
            arr[i] = copied[i - left];
        }

        // 5. Sort subarrays recursively
        for (int r = 0; r < 27; r++) {
            sort(arr, copied, left + cnt[r], left + cnt[r+1] - 1, d+1);
        }
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
        MSDRecursiveSort msdSort = new MSDRecursiveSort();
        System.out.println(Arrays.toString(msdSort.copySort(new String[]{"b", "bd", "ab", "da", "aaa", "aa", "bba"})));
        System.out.println(Arrays.toString(msdSort.copySort(new String[]{"db", "db", "bbb", "ba", "aaa", "a"})));
        System.out.println(Arrays.toString(msdSort.copySort(new String[]{"aaa", "a", "b", "dd"})));
    }

}
