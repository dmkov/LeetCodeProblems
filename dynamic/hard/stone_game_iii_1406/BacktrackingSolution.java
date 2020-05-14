package dynamic.hard.stone_game_iii_1406;

import java.util.HashMap;
import java.util.Map;

/**
 1406. Stone Game III
 https://leetcode.com/problems/stone-game-iii/

 Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

 Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones from the first remaining stones in the row.

 The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.

 The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.

 Assume Alice and Bob play optimally.

 Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.

 Example 1:
 Input: values = [1,2,3,7]
 Output: "Bob"
 Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.

 Example 2:
 Input: values = [1,2,3,-9]
 Output: "Alice"
 Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative score.
 If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. The next move Alice will take the pile with value = -9 and lose.
 If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. The next move Alice will take the pile with value = -9 and also lose.
 Remember that both play optimally so here Alice will choose the scenario that makes her win.

 Example 3:
 Input: values = [1,2,3,6]
 Output: "Tie"
 Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.

 Example 4:
 Input: values = [1,2,3,-1,-2,-3,7]
 Output: "Alice"

 Example 5:
 Input: values = [-1,-2,-3]
 Output: "Tie"

 --------

 1. Complexity
    1.1 Time Complexity is O(n)
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Idea is to maximise the result for Alice turn and minimise it for Bob
    2.2 If the final result is greater than 0 - Alice won, otherwise - Bob, if it is 0 - it is tie
 */

class BacktrackingSolution {
    public String stoneGameIII(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length == 0) {
            return "Tie";
        }

        Map<String, Integer> memo = new HashMap<>();
        int res = backtracking(stoneValue, 0, true, memo);

        if (res == 0) {
            return "Tie";
        } else if (res > 0) {
            return "Alice";
        } else {
            return "Bob";
        }
    }

    private int backtracking(int[] arr, int pos, boolean player, Map<String, Integer> memo) {
        int res = 0;
        if (pos >= arr.length) {
            return res;
        }

        String key = pos + "_" + player;
        Integer cache = memo.get(key);
        if (cache != null) {
            return cache;
        }

        int stones = 0;
        int subSum = 0;
        res = (player) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        while (stones < 3 && pos + stones < arr.length) {
            subSum += arr[pos + stones];

            if (player) {
                // maximize -- Alice
                res = Math.max(res, backtracking(arr, pos + stones + 1, !player, memo) + subSum);
            } else {
                // minimize -- Bob
                res = Math.min(res, backtracking(arr, pos + stones + 1, !player, memo) - subSum);
            }
            stones++;
        }

        memo.put(key, res);
        return res;
    }
}



/**

     1. The value of the stone is something from 1 to 1000? No, negatives allowed.
     2. Should I actually care about integer overflow?
     3. How many stones could we have?
     4. And we usualy pick only from one side of the list?
     5. What should I return if there are same score?

         1,2,-3,4

         1 -> 2,-3,4 (1:3)
         1,2 -> -3,4 (3:1)
         1,2,-3 -> 4 (0:4)

     if I can have more than total sum / 2 - I win
     I need to maximise selection to get more than half of the total score

     on every step we have decide 1 of 3 possible choices
     and take the most optimal one


                  1,2,3,6

         1,2,3:6         1,2:3,6                 1:2,3,6

                    1,2+6:3  1,2:3,6      1+3,6:2     1+6:2,3     1:2,3,6

                                                1+6+3:2     1+6:2,3


     I win only if there are no chances for the oppornent


     2. Call backtracking method for the pos=0 and player=true
         if player == true
             -> Get the max possible score of three choices
             else -> get the min possible score of three choices

         add or subtrack subsum of selected elements and return back
     3. If result is bigger than 0 - Alice, 0 - both, smaller - Bob

 */
