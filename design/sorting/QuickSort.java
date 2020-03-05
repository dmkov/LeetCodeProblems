package design.sorting;

import java.util.Arrays;

public class QuickSort {

    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partitioning(arr, left, right);
        sort(arr, left, pivot - 1);
        sort(arr, pivot + 1, right);
    }

    private int partitioning(int[] arr, int left, int right) {
        int i = left, j = left;
        while (i < right) {
            if (arr[i] < arr[right]) {
                swap(arr, j, i);
                j++;
            }
            i++;
        }
        swap(arr, j, right);

        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int[] copySort(int[] src) {
        int[] arr = Arrays.copyOf(src, src.length);
        sort(arr);

        return arr;
    }

    public static void main(String[] args) {
        QuickSort selectionSort = new QuickSort();
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{3, 1, 4, 0, 5, 2})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{0, 1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{5, 4, 3, 2, 1, 0})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{1, 2, 2, 3, 1, 1})));
    }

}
