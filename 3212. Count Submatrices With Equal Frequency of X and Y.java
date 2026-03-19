/*********************************************** JAVA **************************************************/

// Optimal Solution - Use 2D prefix sums to count submatrices from (0,0) where the number of X and Y is equal.
/* "I use two prefix sum matrices to track counts of X and Y. For each cell, I check if the submatrix from (0,0) has equal counts." */

class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // Prefix sum for count of 'X'
        int[][] prefixX = new int[rows][cols];
        // Prefix sum for count of 'Y'
        int[][] prefixY = new int[rows][cols];
        int count = 0; // Number of valid submatrices
        // Build prefix sums
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //Initialise based on current cell
                prefixX[i][j] = (grid[i][j] == 'X') ? 1 : 0;
                prefixY[i][j] = (grid[i][j] == 'Y') ? 1 : 0;
                // Add from top
                if (i - 1 >= 0) {
                    prefixX[i][j] += prefixX[i - 1][j];
                    prefixY[i][j] += prefixY[i - 1][j];
                }
                // Add from left
                if (j - 1 >= 0) {
                    prefixX[i][j] += prefixX[i][j - 1];
                    prefixY[i][j] += prefixY[i][j - 1];
                }
                // Subtract overlapping top-left
                if (i - 1 >= 0 && j - 1 >= 0) {
                    prefixX[i][j] -= prefixX[i - 1][j - 1];
                    prefixY[i][j] -= prefixY[i - 1][j - 1];
                }
                // Check condition:
                // equal number of X and Y AND at least one X
                if (prefixX[i][j] == prefixY[i][j] && prefixX[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }
}

// Time Complexity :- O(m * n).
// Space Complexity :- O(m * n).
