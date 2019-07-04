package math.easy.set_mismatch_645;

/**
 645. Set Mismatch
 https://leetcode.com/problems/set-mismatch/

 The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

 Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

 Example 1:
 Input: nums = [1,2,2,4]
 Output: [2,3]

 Note:
 The given array size will in the range [2, 10000].
 The given array's numbers won't have any order.


 ---

 1. Complexity
    1.1 Time Complexity is O(n) since in all cases we need to go through all list
    1.2 Space Complexity is O(n) using a map and O(1) for the inversion in array
 2. Approach
    2.1 Create a second array, then iterate through first list and increment items in the second one by index. Then
        check result array. Index with value of two is duplicate and 0 is missing one.
    2.2 The second approach is based on the idea to use same array and invert sign of all numbers in it by index.
        If number is already negative, then it is duplicated. If number remains positive, then it was missing in values.
 */
public class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] list = new int[nums.length];
        for (int i: nums) {
            list[i - 1]++;
        }

        int missing = -1;
        int duplicated = -1;
        for (int i = 0; i < list.length; i++) {
            switch (list[i]) {
                case 0:
                    missing = i + 1;
                    break;
                case 2:
                    duplicated = i + 1;
                    break;
                default:
                    break;
            }
        }
        return new int[]{duplicated, missing};
    }

//    Using a map
//    public int[] findErrorNums(int[] nums) {
//        Map< Integer, Integer > map = new HashMap();
//        int dup = -1, missing = 1;
//        for (int n: nums) {
//            map.put(n, map.getOrDefault(n, 0) + 1);
//        }
//        for (int i = 1; i <= nums.length; i++) {
//            if (map.containsKey(i)) {
//                if (map.get(i) == 2)
//                    dup = i;
//            } else
//                missing = i;
//        }
//        return new int[]{dup, missing};
//    }

//    Using a constant space
//    We can save the space used in the last approach, if we can somehow, include the information regarding the duplicacy of an element or absence of an element in the numsnums array. Let's see how this can be done.
//
//    We know that all the elements in the given numsnums array are positive, and lie in the range 11 to nn only. Thus, we can pick up each element ii from numsnums. For every number ii picked up, we can invert the element at the index \left|i\right|∣i∣. By doing so, if one of the elements jj occurs twice, when this number is encountered the second time, the element nums[\left|i\right|]nums[∣i∣] will be found to be negative. Thus, while doing the inversions, we can check if a number found is already negative, to find the duplicate number.
//
//    After the inversions have been done, if all the elements in numsnums are present correctly, the resultant numsnums array will have all the elements as negative now. But, if one of the numbers, jj is missing, the element at the j^{th}j
//            th
//    index will be positive. This can be used to determine the missing number.
//    public int[] findErrorNums(int[] nums) {
//        int dup = -1, missing = 1;
//        for (int n: nums) {
//            if (nums[Math.abs(n) - 1] < 0)
//                dup = Math.abs(n);
//            else
//                nums[Math.abs(n) - 1] *= -1;
//        }
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] > 0)
//                missing = i + 1;
//        }
//        return new int[]{dup, missing};
//    }

}
