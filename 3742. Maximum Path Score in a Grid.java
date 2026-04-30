/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds maximum grid path score bottom-up using 3D DP tracking positive cell budget, choosing best of down/right moves at each state.
/* "Bottom-up avoids recursion stack overflow and is cache-friendlier than top-down memoization. Key insight: use newCost (after visiting current cell) to look up future states — not cost. 
    The -1 sentinel cleanly distinguishes unreachable states from valid zero-score paths without needing a separate boolean validity array." */

class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        // dp[i][j][cost] = max score from (i,j) with 'cost' positive cells used so far
        int[][][] dp = new int[m + 1][n + 1][k + 1];
        // initialize all states as invalid — -1 means unreachable
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= n; j++)
                for (int c = 0; c <= k; c++)
                    dp[i][j][c] = -1;
        // fill bottom-up from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // iterate cost in reverse — higher costs processed first
                for (int cost = k; cost >= 0; cost--) {
                    // compute new cost after visiting current cell
                    int newCost = cost + (grid[i][j] > 0 ? 1 : 0);
                    // exceeded positive cell budget — skip this state
                    if (newCost > k)
                        continue;
                    // base case: destination cell — return its value
                    if (i == m - 1 && j == n - 1) {
                        dp[i][j][cost] = grid[i][j];
                        continue;
                    }
                    // get best score from moving down
                    int down = (i + 1 < m) ? dp[i + 1][j][newCost] : -1;
                    // get best score from moving right
                    int right = (j + 1 < n) ? dp[i][j + 1][newCost] : -1;
                    // take best valid next step
                    int bestNext = Math.max(down, right);
                    // only update if a valid path exists forward
                    if (bestNext != -1)
                        dp[i][j][cost] = grid[i][j] + bestNext;
                }
            }
        }
        // answer: max score from (0,0) with 0 positive cells used
        return dp[0][0][0];
    }
}

// Time Complexity :- O(m * n * k).
// Space Complexity :- O(m * n * k).
