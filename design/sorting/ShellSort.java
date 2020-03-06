package design.sorting;

import java.util.Arrays;

public class ShellSort {

    public void sort(int[] arr) {
        int length = arr.length;
        int k = 1;
        while (k < length / 3) {
            k = 3*k + 1;
        }

        while (k >= 1) {
            for (int j = k; j < length; j++) {
                int p = j;
                while (p - k >= 0 && arr[p - k] > arr[p]) {
                    int temp = arr[p - k];
                    arr[p - k] = arr[p];
                    arr[p] = temp;
                    p = p - k;
                }
            }
            k = k / 3;
        }
    }

    public int[] copySort(int[] src) {
        int[] arr = Arrays.copyOf(src, src.length);
        sort(arr);

        return arr;
    }

    public static void main(String[] args) {
        ShellSort selectionSort = new ShellSort();
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{1, 3, 4, 0, 5, 2})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{0, 1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{5, 4, 3, 2, 1, 0})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{1, 2, 2, 3, 1, 1})));
    }

}
