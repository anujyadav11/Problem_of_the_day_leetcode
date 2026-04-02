/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximises coin collection along a grid path using 3D memoisation, tracking remaining neutralisations to optimally skip up to 2 negative cells.
/* "The third dimension neu is the key — it converts 'up to 2 skips' into a finite state space of size 3. Integer.MIN_VALUE doubles as both the unvisited sentinel and invalid-path marker, 
    so guard all additions against it to avoid overflow. Base case must handle neutralisation at the final cell separately." */

class Solution {
    int m;
    int n;
    int[][][] dp;
    public int solve(int[][] coins, int i, int j, int neu) {
        // base case: reached bottom-right cell
        if (i == m - 1 && j == n - 1) {
            // if cell is negative and we still have neutralisations — skip it for free
            if (coins[i][j] < 0 && neu > 0)
                return 0;
            return coins[i][j];
        }
        // out of bounds — invalid path
        if (i >= m || j >= n)
            return Integer.MIN_VALUE;
        // return cached result if already computed
        if (dp[i][j][neu] != Integer.MIN_VALUE)
            return dp[i][j][neu];
        int best = Integer.MIN_VALUE;
        // option 1: move down without neutralising the current cell
        int down = solve(coins, i + 1, j, neu);
        if (down != Integer.MIN_VALUE)
            best = Math.max(best, coins[i][j] + down);
        // option 2: move right without neutralising the current cell
        int right = solve(coins, i, j + 1, neu);
        if (right != Integer.MIN_VALUE)
            best = Math.max(best, coins[i][j] + right);
        // option 3 & 4: neutralise the current negative cell and move down or right
        if (coins[i][j] < 0 && neu > 0) {
            int downSkip = solve(coins, i + 1, j, neu - 1);
            int rightSkip = solve(coins, i, j + 1, neu - 1);
            // take best of both skip directions
            int skipBest = Math.max(downSkip, rightSkip);
            if (skipBest != Integer.MIN_VALUE)
                best = Math.max(best, skipBest);
        }
        //memoise and return the best result for this state
        return dp[i][j][neu] = best;
    }
    public int maximumAmount(int[][] coins) {
        m = coins.length;
        n = coins[0].length;
        // third dimension = neutralisations remaining (0, 1, or 2)
        dp = new int[m][n][3];
        // initialize dp with MIN_VALUE as unvisited sentinel
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < 3; k++)
                    dp[i][j][k] = Integer.MIN_VALUE;
        // start from top-left with 2 neutralisations available
        return solve(coins, 0, 0, 2);
    }
}

// Time Complexity :- O(m * n * 3).
// Space Complexity :- O(m * n * 3).
