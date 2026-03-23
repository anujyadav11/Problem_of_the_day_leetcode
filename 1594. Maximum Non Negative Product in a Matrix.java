/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds the maximum product path in a grid using DP by tracking both min and max products per cell to correctly handle negative number sign flips.
/* "Whenever negatives are involved in product DP, always track both min and max — a large negative × negative becomes the new maximum. 
    This is the same core idea as Maximum Product Subarray but extended to 2D paths." */

class Solution {
    final int MOD = 1000000007;
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // each cell stores (maxProduct, minProduct) to handle negative multiplications
        Pair<Long, Long>[][] t = new Pair[m][n];
        // base case: top-left cell has only one path to itself
        t[0][0] = new Pair<>((long) grid[0][0], (long) grid[0][0]);
        // fill first row: only one path — all from the left
        for (int j = 1; j < n; j++)
            t[0][j] = new Pair<>(
                t[0][j-1].getKey() * grid[0][j],
                t[0][j-1].getValue() * grid[0][j]);
        // fill first column: only one path — all from above
        for (int i = 1; i < m; i++)
            t[i][0] = new Pair<>(
                t[i-1][0].getKey() * grid[i][0],
                t[i-1][0].getValue() * grid[i][0]);
        // fill rest of DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // candidates from cell directly above
                long upMax = t[i-1][j].getKey();
                long upMin = t[i-1][j].getValue();
                // candidates from cell directly to the left
                long leftMax = t[i][j-1].getKey();
                long leftMin = t[i][j-1].getValue();
                // max product: consider all 4 combos (negatives can flip signs)
                t[i][j] = new Pair<>(
                    Math.max(Math.max(upMax * grid[i][j], upMin * grid[i][j]),
                             Math.max(leftMax * grid[i][j], leftMin * grid[i][j])),
                    // min product: same 4 combos for tracking most negative value
                    Math.min(Math.min(upMax * grid[i][j], upMin * grid[i][j]),
                             Math.min(leftMax * grid[i][j], leftMin * grid[i][j])));
            }
        }
        // retrieve max product at bottom-right destination
        long maxProd = t[m-1][n-1].getKey();
        // negative max product means no valid positive path exists
        return maxProd < 0 ? -1 : (int) (maxProd % MOD);
    }
}

// Time Complexity :- O(m × n).
// Space Complexity :- O(m × n).
