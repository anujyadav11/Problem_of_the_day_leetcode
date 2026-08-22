/************************************************** JAVA ***************************************************/

Optimal Approach- We solve this problem using Binary Search to determine the latest possible day to cross the grid, and DFS to check whether there exists a valid path through land 
                  cells from the top row to the bottom row on a given day.

class Solution {
    // Number of rows and columns
    private int ROW, COL;
    // Directions: down, up, right, left
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int latestDayToCross(int row, int col, int[][] cells) {
        // Initialize grid dimensions
        this.ROW = row;
        this.COL = col;
        // Binary search boundaries on days
        int left = 0;
        int right = cells.length - 1;
        // Stores the last possible valid day
        int lastDay = 0;
        // Binary search on days
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // Check if crossing is possible on 'mid' day
            if (canCross(cells, mid)) {
                lastDay = mid + 1;   // +1 because days are 1-indexed
                left = mid + 1;      // Try to find a later valid day
            } else {
                right = mid - 1;     // Try earlier days
            }
        }
        return lastDay;
    }
    // Check if it's possible to cross the grid on a given day
    private boolean canCross(int[][] cells, int day) {
        // Create grid: 0 = land, 1 = water
        int[][] grid = new int[ROW][COL];
        // Mark flooded cells up to 'day'
        for (int i = 0; i <= day; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            grid[r][c] = 1;
        }
        // Try DFS from each column in the top row
        for (int j = 0; j < COL; j++) {
            if (grid[0][j] == 0 && dfs(grid, 0, j)) {
                return true; // Found a valid path to bottom
            }
        }
        return false;
    }
    // DFS to check if we can reach the bottom row
    private boolean dfs(int[][] grid, int i, int j) {
        // Boundary or water check
        if (i < 0 || i >= ROW || j < 0 || j >= COL || grid[i][j] == 1) {
            return false;
        }
        // If bottom row is reached, crossing is possible
        if (i == ROW - 1) return true;
        // Mark cell as visited (convert to water)
        grid[i][j] = 1;
        // Explore all 4 directions
        for (int[] dir : dirs) {
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (dfs(grid, newi, newj)) {
                return true;
            }
        }
        return false;
    }
}

Time Complexity :-  O( N log N).
Space Complexity :- O(N).
