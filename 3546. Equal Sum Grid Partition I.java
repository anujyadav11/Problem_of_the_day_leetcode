/*********************************************** JAVA **************************************************/

// Optimal Solution - Checks if a grid can be partitioned into two equal-sum halves via a straight horizontal or vertical cut using precomputed row and column prefix sums.
/* "Precomputing row and column sums reduces each cut check to O(1). The key constraint is cutting only between rows or columns — not arbitrary shapes — 
    so two simple prefix sum passes over m+n positions cover all valid cuts efficiently." */

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // precompute sum of each row
        long[] rowSum = new long[m];
        // precompute sum of each column
        long[] colSum = new long[n];
        long total = 0;
        // single pass to fill rowSum, colSum and total
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
                rowSum[i] += grid[i][j];
                colSum[j] += grid[i][j];
            }
        }
        long upper = 0;
        // try every horizontal cut between row i and row i+1
        for (int i = 0; i < m - 1; i++) {
            upper += rowSum[i];
            // valid cut if upper half equals lower half
            if (upper == total - upper)
                return true;
        }
        long left = 0;
        // try every vertical cut between col j and col j+1
        for (int j = 0; j < n - 1; j++) {
            left += colSum[j];
            // valid cut if left half equals right half
            if (left == total - left)
                return true;
        }
        // no valid partition found
        return false;
    }
}

// Time Complexity :- O(m * n).
// Space Complexity :- O(m + n).
