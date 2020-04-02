package array.hard.game24_679;

/**
 679. 24 Game
 https://leetcode.com/problems/24-game/

 You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

 Example 1:
 Input: [4, 1, 8, 7]
 Output: True
 Explanation: (8-4) * (7-1) = 24

 Example 2:
 Input: [1, 2, 1, 2]
 Output: False

 Note:
 The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

 --------

 https://leetcode.com/problems/sum-of-subsequence-widths/discuss/161267/C%2B%2BJava1-line-Python-Sort-and-One-Pass

 1. Complexity
     1.1 Time Complexity is more than O(n!)
     1.2 Space Complexity is O(1)
 2. Approach
     2.1 Pull every number from the list, get '-','+','/' and '*' with all other numbers in the list and store
        a new value back. Proceed to the next step until there are 2 numbers left.
        In this case - check if 24 is possible to get from them
 3. Implementation
     3.1 Check if the given array is valid. Create a double list from integer to get the math division in the result.
     3.2 Execute backtracking method with the double values
     3.3 Iterate over all numbers from 0 to 3 indexes.
        3.3.1 If number is not null, get it as num1 and set to null in the array
        3.3.2 Iterate over all numbers from 0 to 3 indexes:
            3.3.2.1 If number is not null, get it as num2
            3.3.2.2 If it is the last cycle - check all possible cases between num1 and num2 - multiplication, addition,
                subtraction and division (two last should be for num1 and num2 and then for num2 and num1). If result
                of any of operations in 24 - return true.
            3.3.2.3 Otherwise, update num2 with all possible cases (push the result of operation back to the array)
                and call backtracking functions.
     3.4 For comparing double values use addition precision since numbers may be different after division.
 */

class BacktrackingSolution {

    final double eps = 0.0001;

    public boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length != 4) {
            return false;
        }

        Double[] list = new Double[4];
        for (int i = 0; i < 4; i++) {
            list[i] = (double)nums[i];
        }

        return backtracking(list, 3);

    }

    private boolean backtracking(Double[] list, int cycle) {
        cycle--;
        boolean result = false;

        for (int i = 0; i < 4; i++) {
            if (list[i] != null) {
                Double num1 = list[i];
                list[i] = null;

                for (int j = 0; j < 4; j++) {
                    if (list[j] != null) {
                        Double num2 = list[j];

                        if (cycle == 0) {
                            list[i] = num1;
                            return (Math.abs(num1 * num2 - 24.0) < eps || Math.abs(num1 / num2 - 24.0) < eps
                                    || Math.abs(num1 + num2 - 24.0) < eps || Math.abs(num1 - num2 - 24.0) < eps
                                    || Math.abs(num2 / num1 - 24.0) < eps || Math.abs(num2 - num1 - 24.0) < eps);
                        } else {
                            list[j] = num1 * num2;
                            result = result || backtracking(list, cycle);

                            list[j] = num1 / num2;
                            result = result || backtracking(list, cycle);

                            list[j] = num1 - num2;
                            result = result || backtracking(list, cycle);

                            list[j] = num1 + num2;
                            result = result || backtracking(list, cycle);

                            list[j] = num2 / num1;
                            result = result || backtracking(list, cycle);

                            list[j] = num2 - num1;
                            result = result || backtracking(list, cycle);

                            if (result) {
                                return true;
                            }
                        }

                        list[j] = num2;
                    }
                }

                list[i] = num1;
            }
        }

        return result;
    }
}
