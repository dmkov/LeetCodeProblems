package array.hard.first_missing_positive_41;

/**
 41. First Missing Positive
 https://leetcode.com/problems/first-missing-positive/

 Given an unsorted integer array, find the smallest missing positive integer.

 Example 1:
 Input: [1,2,0]
 Output: 3

 Example 2:
 Input: [3,4,-1,1]
 Output: 2

 Example 3:
 Input: [7,8,9,11,12]
 Output: 1
 Note:

 Your algorithm should run in O(n) time and uses constant extra space.

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(1)
 2. Approach
    2.1 Iterate numbers and put each number to its index until you find the num that is out of range or already on its place
    2.2 Iterate numbers again, the first index where element is not on its place - is the answer
    2.3 Be careful with overflow numbers. Fow example, [1]
 */

public class IndexSolution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        boolean overlap = false;
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i] && nums[i] >= 0 && nums[i] < nums.length && nums[i] != nums[ nums[i] ]) {
                int indx = nums[i];
                int temp = nums[indx];
                nums[indx] = nums[i];
                nums[i] = temp;
            }
            if (nums[i] == nums.length) {
                overlap = true;
            }
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return (!overlap) ? nums.length : nums.length + 1;
    }
}

/**

     1. Are negatives and zero allowed in the array?
     2. Is it list of integers? Are duplicates allowed?
     3. Do we consider 0 as a possible answer?
     4. What is max length of the array?

         -100,100 -> 0
         0,-100 -> 1
         0 -> 1
         -1,-2,3 -> 0


     1. Naive approach:
         - store values in the hash map, then iterate from 0 to list.length:
             if element is the hash map -> continue, otherwise -> return index
             O(n) time and space complexitty

     2. Reduce space complexity using indexes:
         - iterate all elements:
         - while element is >= 0 and < list.length and element != index -> swap it with element at the index
         - first element with i != el is missing number

 */
