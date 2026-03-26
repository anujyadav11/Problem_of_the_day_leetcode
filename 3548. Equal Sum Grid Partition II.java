/*********************************************** JAVA **************************************************/

// Optimal Solution - I didn't solve this question by myself. I have taken help from CodeStoryWithMIK YouTube channel

import java.util.*;

class Solution {
    // shared total sum across all method calls — risky if reused
    long total = 0;

    public boolean checkHorCuts(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // stores all individual cell values seen so far across rows
        HashSet<Long> set = new HashSet<>();
        long top = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                // add current cell value to seen set
                set.add((long) grid[i][j]);
                // accumulate top partition sum row by row
                top += grid[i][j];
            }
            // bottom is whatever total minus top
            long bottom = total - top;
            // diff = how much top exceeds bottom
            long diff = top - bottom;
            // equal halves — valid cut
            if (diff == 0) return true;
            // diff matches top-left corner value
            if (diff == grid[0][0]) return true;
            // diff matches top-right corner value
            if (diff == grid[0][n - 1]) return true;
            // diff matches leftmost value of current row
            if (diff == grid[i][0]) return true;
            // diff exists as a previously seen cell value
            if (i > 0 && n > 1 && set.contains(diff)) return true;
        }
        return false;
    }

    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // compute grand total of all elements
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                total += grid[i][j];
        // check horizontal cuts on original grid
        if (checkHorCuts(grid)) return true;
        // reverse rows and check again (bottom-up horizontal cuts)
        reverse(grid);
        if (checkHorCuts(grid)) return true;
        // restore original order
        reverse(grid);
        // transpose grid to convert vertical cuts into horizontal ones
        int[][] transposeGrid = new int[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                transposeGrid[j][i] = grid[i][j];
        // check horizontal cuts on transposed grid
        if (checkHorCuts(transposeGrid)) return true;
        // reverse transposed grid and check again
        reverse(transposeGrid);
        return checkHorCuts(transposeGrid);
    }

    private void reverse(int[][] grid) {
        int top = 0, bottom = grid.length - 1;
        while (top < bottom) {
            // swap top and bottom rows in place
            int[] temp = grid[top];
            grid[top] = grid[bottom];
            grid[bottom] = temp;
            top++;
            bottom--;
        }
    }
}

// Time Complexity :- O(m * n).
// Space Complexity :- O(m + n).
