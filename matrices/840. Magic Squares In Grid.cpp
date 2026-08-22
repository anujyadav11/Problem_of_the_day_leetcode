/**************************************** JAVA *************************************/

Optimal solution - This one is the best solution I can find. I couldn't solve this problem by myself, soo i took the help of 
                    A YouTuber video named CodeStoryWithMIK.

class Solution {
    // Main function to count magic 3x3 squares inside the grid
    public int numMagicSquaresInside(int[][] grid) {
        // Number of rows
        int n = grid.length;
        // Number of columns
        int m = grid[0].length;
        // If grid is smaller than 3x3, no magic square is possible
        if (n < 3 || m < 3) {
            return 0;
        }
        int count = 0;
        // Traverse all possible top-left corners of 3x3 subgrids
        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 3; j++) {
                // Check if the current 3x3 subgrid is a magic square
                if (isMagic(grid, i, j)) {
                    count++;
                }
            }
        }
        // Return total count of magic squares found
        return count;
    }
    // Helper method to check if a 3x3 grid starting at (r, c) is magic
    private boolean isMagic(int[][] grid, int r, int c) {
        // Boolean array to track numbers 1 to 9
        boolean[] seen = new boolean[10];
        // Check all 9 cells in the 3x3 grid
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                int num = grid[i][j];
                // Each number must be between 1 and 9
                // and must appear exactly once
                if (num < 1 || num > 9 || seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
        }
        // Calculate the sum of the first row
        int sum = grid[r][c] + grid[r][c + 1] + grid[r][c + 2];
        // Check all rows and columns
        for (int i = 0; i < 3; i++) {
            // Check row sum
            if (grid[r + i][c] + grid[r + i][c + 1] + grid[r + i][c + 2] != sum) {
                return false;
            }
            // Check column sum
            if (grid[r][c + i] + grid[r + 1][c + i] + grid[r + 2][c + i] != sum) {
                return false;
            }
        }
        // Check main diagonal
        if (grid[r][c] + grid[r + 1][c + 1] + grid[r + 2][c + 2] != sum) {
            return false;
        }
        // Check anti-diagonal
        if (grid[r][c + 2] + grid[r + 1][c + 1] + grid[r + 2][c] != sum) {
            return false;
        }
        // All conditions satisfied → magic square
        return true;
    }
}
Time Complexity :- O(n * m).
Space Complexity :- O(1).
