/*********************************************** JAVA **************************************************/

// Optimal Solution - Tracked reachable path XOR values using grid DP and BitSet state compression to find the minimum achievable XOR.
/* “I use dynamic programming to track all reachable XOR values at every grid cell. Each cell combines the reachable states from above and left, then XORs each state with its own value. 
    A BitSet efficiently represents the reachable values without enumerating individual paths. At the destination, I select the smallest reachable XOR value.” */

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // dp[i][j] stores all XOR values reachable at cell (i, j).
        // A set bit at position x means XOR value x is achievable.
        BitSet[][] dp = new BitSet[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new BitSet(1024);
            }
        }
        // The only path to the starting cell contains its own value.
        dp[0][0].set(grid[0][0]);
        // Process cells in row-major order.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // The starting cell is already initialized.
                if (i == 0 && j == 0) {
                    continue;
                }
                // Collect XOR values reachable from above and left.
                BitSet combined = new BitSet(1024);
                if (i > 0) {
                    combined.or(dp[i - 1][j]);
                }
                if (j > 0) {
                    combined.or(dp[i][j - 1]);
                }
                // Extend every reachable path by including grid[i][j].
                for (int xorVal = combined.nextSetBit(0);
                     xorVal >= 0;
                     xorVal = combined.nextSetBit(xorVal + 1)) {
                    int newXor = xorVal ^ grid[i][j];
                    dp[i][j].set(newXor);
                }
            }
        }
        // Return the smallest XOR value reachable at the destination.
        return dp[m - 1][n - 1].nextSetBit(0);
    }
}

// Time Complexity :- O().
// Space Complexity :- O().
