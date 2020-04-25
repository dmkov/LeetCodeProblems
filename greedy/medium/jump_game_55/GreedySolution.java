package greedy.medium.jump_game_55;

public class GreedySolution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int steps = nums[0]; // 1
        int indx = 0;  // 0
        while (indx < nums.length - 1) {
            if (nums[indx] > steps) {
                steps = nums[indx];
                if (indx + steps >= nums.length - 1) {
                    indx = nums.length - 1;
                    break;
                }
            }

            if (steps > 0) {
                indx++;
                steps--;
            } else {
                break;
            }
        }

        return indx == nums.length - 1;
    }
}
