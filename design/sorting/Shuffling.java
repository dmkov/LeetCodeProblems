package design.sorting;

import java.util.Arrays;

public class Shuffling {

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int j = (int)(Math.random() * (arr.length - i)) + i;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public int[] copyAndAction(int[] src) {
        int[] arr = Arrays.copyOf(src, src.length);
        sort(arr);

        return arr;
    }

    public static void main(String[] args) {
        Shuffling shuffling = new Shuffling();
        System.out.println(Arrays.toString(shuffling.copyAndAction(new int[]{1, 3, 4, 0, 5, 2})));
        System.out.println(Arrays.toString(shuffling.copyAndAction(new int[]{0, 1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(shuffling.copyAndAction(new int[]{5, 4, 3, 2, 1, 0})));
        System.out.println(Arrays.toString(shuffling.copyAndAction(new int[]{1, 2, 2, 3, 1, 1})));
    }

}
