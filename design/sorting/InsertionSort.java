package design.sorting;

import java.util.Arrays;

public class InsertionSort {

    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }
    }

    public int[] copySort(int[] src) {
        int[] arr = Arrays.copyOf(src, src.length);
        sort(arr);

        return arr;
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        System.out.println(Arrays.toString(insertionSort.copySort(new int[]{3, 1, 4, 0, 5, 2})));
        System.out.println(Arrays.toString(insertionSort.copySort(new int[]{0, 1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(insertionSort.copySort(new int[]{5, 4, 3, 2, 1, 0})));
        System.out.println(Arrays.toString(insertionSort.copySort(new int[]{1, 2, 2, 3, 1, 1})));
    }

}
