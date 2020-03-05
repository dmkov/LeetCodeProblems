package design.sorting;

import java.util.Arrays;

public class QuickSort3Way {

    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] pivot = partitioning(arr, left, right);
        sort(arr, left, pivot[0] - 1);
        sort(arr, pivot[1] + 1, right);
    }

    private int[] partitioning(int[] arr, int left, int right) {
        int i = left, k = left, j = right;
        int pivot = arr[right];

        while (k <= j) {
            if (arr[k] > pivot) {
                swap(arr, k, j);
                j--;
            } else if (arr[k] < pivot) {
                swap(arr, k, i);
                i++;
                k++;
            } else {
                k++;
            }
        }

        return new int[]{i, j};
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
        QuickSort3Way selectionSort = new QuickSort3Way();
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{3, 1, 5, 0, 0, 2, 0, 5, 2})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{0, 1, 1, 2, 2, 3, 3, 3, 5})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{5, 5, 5, 3, 3, 3, 2, 1, 0})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{1, 2, 2, 3, 1, 1, 3, 1, 1})));
    }

}
