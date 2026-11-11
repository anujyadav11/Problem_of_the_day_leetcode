/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved minimum-cost stair traversal using bottom-up 1D dynamic programming with up to three transitions per state in O(n) time and O(n) space.
/* "I use bottom-up dynamic programming. I define dp[i] as the minimum cost required to reach the destination from position i. From each position, 
    I consider jumps of one, two, and three steps, adding the destination stair's cost and the squared jump distance. I calculate states backwards because each state depends on future positions." */

class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] = minimum cost required to reach stair n
        // starting from position i.
        int[] dp = new int[n + 1];
        // Base case: Reaching position n requires no additional cost.
        dp[n] = 0;
        // Calculate minimum costs from right to left.
        for (int i = n - 1; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;
            // Option 1: Jump 1 step.
            int j = i + 1;
            if (j <= n) {
                int jumpCost = (j - i) * (j - i);
                minCost = Math.min(minCost,dp[j] + costs[j - 1] + jumpCost);
            }
            // Option 2: Jump 2 steps.
            j = i + 2;
            if (j <= n) {
                int jumpCost = (j - i) * (j - i);
                minCost = Math.min(minCost,dp[j] + costs[j - 1] + jumpCost);
            }
            // Option 3: Jump 3 steps.
            j = i + 3;
            if (j <= n) {
                int jumpCost = (j - i) * (j - i);
                minCost = Math.min(minCost,dp[j] + costs[j - 1] + jumpCost);
            }
            // Store the minimum cost for the current position.
            dp[i] = minCost;
        }
        return dp[0];
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
