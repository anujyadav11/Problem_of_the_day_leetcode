/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Count Islands using DFS-based connected-component traversal and sum aggregation in O(n × m) time.
/* “I traverse the grid and start DFS whenever I encounter an unvisited positive cell. The DFS explores all four neighbouring directions, marks visited cells, and accumulates their values into the component’s total. 
    Once the component is fully explored, I check whether its sum is divisible by k and increment the answer accordingly.” */

class Solution {
    int n, m;
    // Four directions: right, left, up, and down.
    private final int[][] dirs = {
        {0, 1}, {0, -1}, {-1, 0}, {1, 0}
    };
    public int countIslands(int[][] grid, int k) {
        n = grid.length;
        m = grid[0].length;
        int count = 0;
        // Visit every cell to find unvisited islands.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // A positive cell belongs to an unvisited island.
                if (grid[i][j] > 0) {
                    // Calculate the sum of this connected component.
                    long sum = dfs(grid, i, j);
                    // Count the island if its total is divisible by k.
                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    private long dfs(int[][] grid, int i, int j) {
        // Include the current cell's value in the island sum.
        long sum = grid[i][j];
        // Mark the cell as visited to prevent repeated traversal.
        grid[i][j] = 0;
        // Explore all four neighboring cells.
        for (int[] dir : dirs) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];
            // Visit valid, unvisited cells belonging to the island.
            if (newRow >= 0 && newRow < n
                    && newCol >= 0 && newCol < m
                    && grid[newRow][newCol] > 0) {
                sum += dfs(grid, newRow, newCol);
            }
        }
        // Return the total value of this connected component.
        return sum;
    }
}

// Time Complexity :- O(n * m).
// Space Complexity :- O(n * m).
