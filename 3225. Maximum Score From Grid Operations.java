/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximizes grid path score using column-wise DP with top-down and bottom-up propagation to capture optimal entry points from any previous column row.
/* "The key insight is decoupling column transitions — for each column, the best score at any row comes from the best previous column value reachable vertically (any row above or below). 
    Two linear passes per column capture both directions in O(n), giving O(mn) total. This avoids O(n²) per column naive comparison." */

class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // dp arrays track best score ending at each row for current and previous columns
        long[] prevDp = new long[n];
        long[] currDp = new long[n];
        long res = 0;
        for (int col = 0; col < m; col++) {
            // scan top to bottom — accumulate max from above
            long maxFromAbove = 0;
            long[] topDown = new long[n];
            for (int row = 0; row < n; row++) {
                maxFromAbove = Math.max(maxFromAbove, prevDp[row]);
                topDown[row] = maxFromAbove + grid[row][col];
            }
            // scan bottom to top — accumulate max from below
            long maxFromBelow = 0;
            long[] bottomUp = new long[n];
            for (int row = n - 1; row >= 0; row--) {
                maxFromBelow = Math.max(maxFromBelow, prevDp[row]);
                bottomUp[row] = maxFromBelow + grid[row][col];
            }
            // combine both directions for each row
            for (int row = 0; row < n; row++) {
                currDp[row] = Math.max(topDown[row], bottomUp[row]);
                res = Math.max(res, currDp[row]);
            }
            // current becomes previous for next column
            prevDp = currDp.clone();
            currDp = new long[n];
        }
        return res;
    }
}

// Time Complexity :- O(m * n).
// Space Complexity :- O(n).
