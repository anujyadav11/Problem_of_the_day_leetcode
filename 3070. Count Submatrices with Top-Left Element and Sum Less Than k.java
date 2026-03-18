/*********************************************** JAVA **************************************************/

// Optimal Solution - Use in-place 2D prefix sum to count submatrices starting from (0,0) with sum ≤ k.
/* Use in-place 2D prefix sum to count submatrices starting from (0,0) with sum ≤ k. */

class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0; // Number of valid submatrices
        // Build prefix sum in-place
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Add value from top
                if (i - 1 >= 0) {
                    grid[i][j] += grid[i - 1][j];
                }
                // Add value from left
                if (j - 1 >= 0) {
                    grid[i][j] += grid[i][j - 1];
                }
                // Subtract overlapping top-left area
                if (i - 1 >= 0 && j - 1 >= 0) {
                    grid[i][j] -= grid[i - 1][j - 1];
                }
                // If submatrix sum from (0,0) to (i,j) ≤ k → count it
                if (grid[i][j] <= k) {
                    count++;
                } else {
                    // Optimization: further columns will only increase sum
                    break;
                }
            }
        }
        return count;
    }
}

// Time Complexity :- O(n * m).
// Space Complexity :- O(1).
