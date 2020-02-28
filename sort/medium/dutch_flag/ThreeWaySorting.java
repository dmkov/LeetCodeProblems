package sort.medium.dutch_flag;

import java.util.Arrays;

/**
 Dijkstra 3-way partitioning
 We need to make a partitioning for the array with duplicated key

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Iterate over the array with two pointers (left and right). If element is greater than the pivot - move it to the
        right, if less - to the left.
 2.2 Implementation
    2.2.1 Add left and right pointers, select last element as a pivot.
    2.2.2 Start iterating the array:
        2.2.2.1 If element is greater than pivot, replace it with element at the right pointer, decrease the pointer.
        2.2.2.2 If element is less than pivot, replace it with element at the left pointer, increase both i and the pointer.
        2.2.2.3 Otherwise, if element equals pivot, increase only i with no replacements.
 */

class ThreeWaySorting {
    public int[] sort(int[] arr) {
        int p = arr[arr.length - 1];
        int left = 0, i = 0, right = arr.length - 1;
        while (i <= right) {
            if (arr[i] > p) {
                swap(arr, i, right);
                right--;
            } else if (arr[i] < p) {
                swap(arr, i, left);
                left++;
                i++;
            } else {
                i++;
            }
        }

        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        ThreeWaySorting sorting = new ThreeWaySorting();
        System.out.println(Arrays.toString(sorting.sort(new int[]{0, 1, 2, 0, 5, 2, 0, 1, 5, 0, 4, 2, 2})));
    }
}
