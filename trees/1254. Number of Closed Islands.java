/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Number of Closed Islands using DFS, connected-component traversal, and boundary detection in O(n × m) time.
/* “I traverse the grid and start DFS whenever I encounter an unvisited land cell. During DFS, I mark each land cell as visited and explore all four neighbours. 
    If any path goes outside the grid, the island is not closed. I count an island only when all four recursive directions confirm that it is enclosed.” */

class Solution {
    int n, m;
    public int closedIsland(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int count = 0;
        // Explore every cell in the grid.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // A zero represents an unvisited land cell.
                if (grid[i][j] == 0) {
                    // Count the island only if it does not touch
                    // the boundary of the grid.
                    if (dfs(grid, i, j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    public boolean dfs(int[][] grid, int r, int c) {
        // Reaching outside the grid means this island is not closed.
        if (r < 0 || r >= n || c < 0 || c >= m) {
            return false;
        }
        // Water does not make an island open.
        if (grid[r][c] == 1) {
            return true;
        }
        // Mark this land cell as visited to prevent repeated traversal.
        grid[r][c] = 1;
        // Explore all four neighboring cells.
        boolean left = dfs(grid, r, c - 1);
        boolean right = dfs(grid, r, c + 1);
        boolean up = dfs(grid, r - 1, c);
        boolean down = dfs(grid, r + 1, c);
        // The island is closed only if every direction remains closed.
        return left && right && up && down;
    }
}

// Time Complexity :- O(n * m).
// Space Complexity :- O(1).
