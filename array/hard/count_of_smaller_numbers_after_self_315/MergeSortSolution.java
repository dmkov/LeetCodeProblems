package array.hard.count_of_smaller_numbers_after_self_315;

import java.util.ArrayList;
import java.util.List;

/**
 315. Count of Smaller Numbers After Self
 https://leetcode.com/problems/count-of-smaller-numbers-after-self/

 You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 Example:
 Input: [5,2,6,1]
 Output: [2,1,1,0]
 Explanation:
 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.

 --------

 1. Complexity
    1.1 Time Complexity is O(n logn).
    1.2 Space Complexity is O(n)
 2. Approach
        https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation
        The idea is to do a merge sort and count every shift of right elements behind the left part (it means that the smaller
        number was on the right of the current index)
 */

public class MergeSortSolution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int[] res = new int[nums.length];
        int[] indx = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indx[i] = i;
        }

        mergeSort(indx, 0, nums.length - 1, nums, res);

        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < res.length; i++){
            result.add(res[i]);
        }
        return result;
    }

    // res = 0,0,0,0
    // indx = 0,1,2,3
    // nums = 5,2,6,1
    // 2,5(1)

    private void mergeSort(int[] indx, int start, int end, int[] nums, int[] res) {
        if (end <= start) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(indx, start, mid, nums, res);
        mergeSort(indx, mid + 1, end, nums, res);

        int rightCounter = 0;
        int[] copied = indx.clone();
        int left = 0;
        int right = 0;
        while (start + left <= mid && mid + 1 + right <= end) {
            if (nums[ copied[start + left] ] <= nums[ copied[mid + 1 + right] ]) {
                indx[start + left + right] = copied[start + left];
                res[ copied[start + left] ] += rightCounter;
                left++;
            } else {
                indx[start + left + right] = copied[mid + 1 + right];
                rightCounter++;
                right++;
            }
        }
        while (start + left <= mid) {
            indx[start + left + right] = copied[start + left];
            res[ copied[start + left] ] += rightCounter;
            left++;
        }
        while (mid + 1 + right <= end) {
            indx[start + left + right] = copied[mid + 1 + right];
            rightCounter++;
            right++;
        }

    }
}


/**
     1. Can you provide some examples?
     2. So for every number in the list I need to return the number of smaller numbers on the right from it?
     3. What should be returned for the last number or if all remaining numbers are larger than the current one?
         [3,2,1] -> [2,1,0]
         [1,0,1] -> [0,0,0]
     4. Are negatives and zero allowed? Are taking about integers or any other type of numbers?
     5. How many numbers in the list we may have? So the answer should be from 0 to MAX_VALUE
     6. Strict smaller or smaller or equal?

         [5,6,3,-1,3] -> [3,3,1,0,0]
         [1,2,3] -> [0,0,0]
         [5,3,1] -> [2,1,0]
         [2,2,2] -> [0,0,0]
         [2] -> [0]

     1. Brute force approach
         - for every number iterate the rest of the list and count smaller elements
         O(n^2)

     2. Merge sort with countint numbers crossing selected index

         1. Divide into two parts, merge sort each of them
         2. To merge select the smallest number from both ranges.
         - moving from the right part, increase counter
         - moving from the left, add to the index counter value


 */
