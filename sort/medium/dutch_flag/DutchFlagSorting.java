package sort.medium.dutch_flag;

import java.util.Arrays;

/**
 You have an unsorted array of integers and a function string getCategory(integer),
 which deterministically returns 1 of three possible strings: "low", "medium", or "high", depending on the input integer.
 You need to output an array with all the "low" numbers at the bottom, all the "medium"
 numbers in the middle, and all the "high" numbers at the top. This is basically a partial sort.
 Within each category, the order of the numbers does not matter.

 For example, you might be give the array [5,7,2,9,1,14,12,10,5,3].
 For input integers 1 - 3, getCategory(integer) returns "low", for 4 - 10 it returns "medium," and for 11 - 15 it returns "high".

 You could output an array (or modify the given array) that looks like this: [3,1,2,5,5,9,7,10,14,12]

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(1)
 2. Approach
     2.1 Modification of 3-way partitioning. Iterate over the array with two pointers (left and right).
     If element is greater than the pivot - move it to the right, if less - to the left.
 2.2 Implementation
     2.2.1 Add left and right pointers.
     2.2.2 Start iterating the array:
         2.2.2.1 If element is in the high section, replace it with element at the right pointer, decrease the pointer.
         2.2.2.2 If element is in the less group, replace it with element at the left pointer, increase both i and the pointer.
         2.2.2.3 Otherwise, if element is in the middle, increase only i with no replacements.
 */

class DutchFlagSorting {
    public int[] sort(int[] arr) {
        int left = 0, i = 0, right = arr.length - 1;
        while (i <= right) {
            if (getCategory(arr[i]) == -1) {
                swap(arr, i, left);
                left++;
                i++;
            } else if (getCategory(arr[i]) == 1) {
                swap(arr, i , right);
                right--;
            } else { // 0
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

    private int getCategory(int num) {
        if (num == 0) {
            return -1;
        } else if (num == 2) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        DutchFlagSorting sorting = new DutchFlagSorting();
        System.out.println(Arrays.toString(sorting.sort(new int[]{0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2})));
    }
}
