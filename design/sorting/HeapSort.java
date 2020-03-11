package design.sorting;

import design.heap.MinHeap;
import java.util.Arrays;

public class HeapSort {

    public void sort(int[] arr) {
        MinHeap heap = new MinHeap(10);
//        for (int num : arr) {
//            heap.insert(num);
//        }
        heap.build(arr);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.poll();
        }
    }

    public int[] copySort(int[] src) {
        int[] arr = Arrays.copyOf(src, src.length);
        sort(arr);

        return arr;
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        System.out.println(Arrays.toString(heapSort.copySort(new int[]{1, 3, 4, 0, 5, 2, 4, 0, 5, 2})));
        System.out.println(Arrays.toString(heapSort.copySort(new int[]{0, 1, 4, 0, 5, 2, 4, 0, 5, 2})));
        System.out.println(Arrays.toString(heapSort.copySort(new int[]{5, 4, 3, 2, 1, 0, 3, 2, 1, 0})));
        System.out.println(Arrays.toString(heapSort.copySort(new int[]{1, 2, 2, 3, 1, 1, 2, 3, 1, 1})));
    }

}
