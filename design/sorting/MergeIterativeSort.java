package design.sorting;

import java.util.Arrays;

public class MergeIterativeSort {

    public void sort(int[] arr) {
        for (int k = 0; k < arr.length; k = 2*k + 1) {
            for (int l = 0; l < arr.length - k; l += 2*k + 2) {
                merge(arr, l, l + k, Math.min(l + 2*k + 1, arr.length - 1));
            }
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] copied = Arrays.copyOf(arr, arr.length);
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (copied[i] < copied[j]) {
                arr[left] = copied[i];
                i++;
            } else {
                arr[left] = copied[j];
                j++;
            }
            left++;
        }
        if (i <= mid) {
            System.arraycopy(copied, i, arr, left, right - left + 1);
        } else {
            System.arraycopy(copied, j, arr, left, right - left + 1);
        }
    }

    public int[] copySort(int[] src) {
        int[] arr = Arrays.copyOf(src, src.length);
        sort(arr);

        return arr;
    }

    public static void main(String[] args) {
        MergeIterativeSort selectionSort = new MergeIterativeSort();
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{3, 1, 4, 0, 5, 2})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{0, 1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{5, 4, 3, 2, 1, 0})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{1, 2, 2, 3, 1, 1})));
    }

}
