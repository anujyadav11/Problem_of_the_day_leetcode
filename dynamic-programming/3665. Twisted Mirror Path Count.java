/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved directional grid path counting using bottom-up 3D dynamic programming with incoming-direction states in O(m*n) time and space.
/* "Since mirror behavior depends on the direction from which we enter a cell, I use a 3D DP state consisting of row, column, and incoming direction. 
    I process the grid bottom-up, calculating the number of valid paths for both incoming directions. Empty cells allow both movements, while mirror cells force a direction-dependent transition." */

class Solution {
    private static final int LEFT = 0;
    private static final int UP = 1;
    private static final int MOD = 1_000_000_007;
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // dp[row][col][direction]:
        // Number of valid paths from this cell to the destination,
        // based on the direction from which we entered this cell.
        int[][][] dp = new int[m + 1][n + 1][2];
        // Process cells in reverse order.
        for (int row = m - 1; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {
                // Calculate both possible incoming directions.
                for (int cameFrom = 0; cameFrom < 2; cameFrom++) {
                    // Reaching the destination completes one valid path.
                    if (row == m - 1 && col == n - 1) {
                        dp[row][col][cameFrom] = 1;
                        continue;
                    }
                    // Mirror cell: movement depends on incoming direction.
                    if (grid[row][col] == 1) {
                        if (cameFrom == LEFT) {
                            // Entered from left: mirror redirects downward.
                            dp[row][col][cameFrom] = dp[row + 1][col][UP];
                        } else {
                            // Entered from above: mirror redirects rightward.
                            dp[row][col][cameFrom] = dp[row][col + 1][LEFT];
                        }
                        continue;
                    }
                    // Empty cell: can move either right or down.
                    long total = 0;
                    // Move right: next cell is entered from its left.
                    total += dp[row][col + 1][LEFT];
                    // Move down: next cell is entered from above.
                    total += dp[row + 1][col][UP];
                    dp[row][col][cameFrom] = (int) (total % MOD);
                }
            }
        }
        // Starting cell is entered from the left conceptually.
        return dp[0][0][LEFT];
    }
}

// Time Complexity :- O(m * n).
// Space Complexity :- O(m * n).
