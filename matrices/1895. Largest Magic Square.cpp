/*********************************************** JAVA **************************************************/

Optimal Solution - Finds the largest magic square in a grid using row and column prefix sums for efficient validation.

class Solution {
    public int largestMagicSquare(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // Prefix sum for each row
        int[][] rowCumsum = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            rowCumsum[i][0] = grid[i][0];
            for (int j = 1; j < cols; j++) {
                rowCumsum[i][j] = rowCumsum[i][j - 1] + grid[i][j];
            }
        }
        // Prefix sum for each column
        int[][] colCumsum = new int[rows][cols];
        for (int j = 0; j < cols; j++) {
            colCumsum[0][j] = grid[0][j];
            for (int i = 1; i < rows; i++) {
                colCumsum[i][j] = colCumsum[i - 1][j] + grid[i][j];
            }
        }
        // Try all possible square sizes from largest to smallest
        for (int side = Math.min(rows, cols); side >= 2; side--) {
            // Fix top-left corner of the square
            for (int i = 0; i + side - 1 < rows; i++) {
                for (int j = 0; j + side - 1 < cols; j++) {
                    // Sum of the first row of the square (reference sum)
                    int targetSum =
                        rowCumsum[i][j + side - 1] - (j > 0 ? rowCumsum[i][j - 1] : 0);
                    boolean isMagic = true;
                    // Check all rows inside the square
                    for (int r = i + 1; r < i + side; r++) {
                        int rowSum =
                            rowCumsum[r][j + side - 1] - (j > 0 ? rowCumsum[r][j - 1] : 0);
                        if (rowSum != targetSum) {
                            isMagic = false;
                            break;
                        }
                    }
                    if (!isMagic) continue;
                    // Check all columns inside the square
                    for (int c = j; c < j + side; c++) {
                        int colSum =
                            colCumsum[i + side - 1][c] - (i > 0 ? colCumsum[i - 1][c] : 0);
                        if (colSum != targetSum) {
                            isMagic = false;
                            break;
                        }
                    }
                    if (!isMagic) continue;
                    // Check main diagonal and anti-diagonal
                    int diag = 0, antiDiag = 0;
                    for (int k = 0; k < side; k++) {
                        diag += grid[i + k][j + k];
                        antiDiag += grid[i + k][j + side - 1 - k];
                    }
                    // If all sums match, this is the largest possible magic square
                    if (diag == targetSum && antiDiag == targetSum) {
                        return side;
                    }
                }
            }
        }
        // If no magic square larger than 1 exists
        return 1;
    }
}

Time Complexity :- O(S² × R × C).
Space Complexity :- O(R × C).
