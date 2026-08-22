/*********************************************** JAVA **************************************************/

// Optimal Solution - Reverses a k×k submatrix vertically in-place using a two-pointer row-swap approach within defined column boundaries.
/* "This is a classic two-pointer reversal applied to a subregion. The key is constraining the column loop to [startCol, endCol] so swaps stay within the k×k window — 
    same as reversing a 1D array but restricted to a 2D slice." */

class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        // define the top and bottom row boundaries of the k×k submatrix
        int startRow = x;
        int endRow = x + k - 1;
        // define the left and right column boundaries of the k×k submatrix
        int startCol = y;
        int endCol = y + k - 1;
        // swap rows from outside in until pointers meet
        while (startRow < endRow) {
            // swap each element between startRow and endRow column by column
            for (int j = startCol; j <= endCol; j++) {
                // standard three-variable swap
                int temp = grid[startRow][j];
                grid[startRow][j] = grid[endRow][j];
                grid[endRow][j] = temp;
            }
            // move top pointer down
            startRow++;
            // move bottom pointer up
            endRow--;
        }
        // return the modified grid with reversed submatrix
        return grid;
    }
}

// Time Complexity :- O(k^2).
// Space Complexity :- O(1).
