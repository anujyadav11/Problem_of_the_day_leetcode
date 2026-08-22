/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Stone Game III using bottom-up dynamic programming by storing the maximum score difference each player can achieve from every index and choosing the optimal move among taking 1, 2, or 3 stones.
/* Rather than tracking Alice’s and Bob’s scores independently, store the score difference between the current player and the opponent. At every position, try taking 1, 2, or 3 stones, then subtract the opponent’s optimal score difference from the remaining game. 
    This converts the minimax game into a simple bottom-up DP with three transitions per state, resulting in an O(n) solution. */

class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] stores the maximum score difference (current player - opponent)
        // starting from index i
        int[] dp = new int[n + 1];
        // Build the DP table from the end towards the beginning
        for (int i = n - 1; i >= 0; i--) {
            // Option 1: Take one stone
            dp[i] = stoneValue[i] - dp[i + 1];
            // Option 2: Take two stones (if available)
            if (i + 1 < n) {
                dp[i] = Math.max(
                        dp[i],
                        stoneValue[i] + stoneValue[i + 1] - dp[i + 2]
                );
            }
            // Option 3: Take three stones (if available)
            if (i + 2 < n) {
                dp[i] = Math.max(
                        dp[i],
                        stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]
                );
            }
        }
        // Final score difference
        int scoreDifference = dp[0];
        if (scoreDifference > 0) {
            return "Alice";
        }
        if (scoreDifference < 0) {
            return "Bob";
        }
        return "Tie";
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
