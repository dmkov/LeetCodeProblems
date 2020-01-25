package graph.easy.find_the_town_judge_997;

/**
 997. Find the Town Judge
 https://leetcode.com/problems/find-the-town-judge/

 In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.

 If the town judge exists, then:
 - The town judge trusts nobody.
 - Everybody (except for the town judge) trusts the town judge.
 - There is exactly one person that satisfies properties 1 and 2.
 You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.

 If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.


 Example 1:
 Input: N = 2, trust = [[1,2]]
 Output: 2

 Example 2:
 Input: N = 3, trust = [[1,3],[2,3]]
 Output: 3

 Example 3:
 Input: N = 3, trust = [[1,3],[2,3],[3,1]]
 Output: -1

 Example 4:
 Input: N = 3, trust = [[1,2],[2,3]]
 Output: -1

 Example 5:
 Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 Output: 3


 Note:
 1 <= N <= 1000
 trust.length <= 10000
 trust[i] are all different
 trust[i][0] != trust[i][1]
 1 <= trust[i][0], trust[i][1] <= N

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 Use array of counters and increase it for the person who is trusted and reduce it if the person trusts himself.
 3. Implementation
     3.1 Check if given number of people is bigger than 0 and if it is equal to 1, there should be no trust data (edge case).
     3.1 Create array to track number of trusting. Iterate all trusts and increase for t[1] and decrease for t[0].
     3.2 At the end find the person with the counter == N - 1.
 */

class InOutCounterSolution {
    public int findJudge(int N, int[][] trust) {
        int result = -1;
        if (N < 1) {
            return result;
        }
        if (trust == null || trust.length == 0) {
            return (N == 1) ? N : result;
        }

        int[] count = new int[N + 1];
        for (int[] t : trust) {
            count[t[1]]++;
            count[t[0]]--;
        }

        for (int i = 1; i <= N; i++) {
            if (count[i] == N - 1) {
                result = i;
                break;
            }
        }

        return result;
    }
}
