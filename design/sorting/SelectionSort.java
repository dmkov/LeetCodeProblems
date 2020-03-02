package design.sorting;

import java.util.Arrays;

public class SelectionSort {

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i], indx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    indx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[indx];
            arr[indx] = temp;
        }
    }

    public int[] copySort(int[] src) {
        int[] arr = Arrays.copyOf(src, src.length);
        sort(arr);

        return arr;
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{3, 1, 4, 0, 5, 2})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{0, 1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{5, 4, 3, 2, 1, 0})));
        System.out.println(Arrays.toString(selectionSort.copySort(new int[]{1, 2, 2, 3, 1, 1})));
    }

}
