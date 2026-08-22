/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds valid street path from top-left to bottom-right using DFS with bidirectional connection validation between adjacent street-type cells.
/* "The bidirectional check is the core insight — moving right into a cell only works if that cell also connects leftward back to you. 
    Storing all 6 street types as direction pairs makes this check a simple loop over the neighbor's connections. Visited array prevents cycles in connected street networks." */

class Solution {
    int m, n;
    // each street type maps to its two connection directions
    Map<Integer, int[][]> directions = new HashMap<>();
    public Solution() {
        // type 1: horizontal — connects left and right
        directions.put(1, new int[][]{{0, -1}, {0, 1}});
        // type 2: vertical — connects up and down
        directions.put(2, new int[][]{{-1, 0}, {1, 0}});
        // type 3: connects left and down
        directions.put(3, new int[][]{{0, -1}, {1, 0}});
        // type 4: connects right and down
        directions.put(4, new int[][]{{0, 1}, {1, 0}});
        // type 5: connects left and up
        directions.put(5, new int[][]{{0, -1}, {-1, 0}});
        // type 6: connects right and up
        directions.put(6, new int[][]{{-1, 0}, {0, 1}});
    }

    public boolean dfs(int[][] grid, int i, int j, boolean[][] vis) {
        // reached bottom-right — valid path exists
        if (i == m - 1 && j == n - 1) return true;
        vis[i][j] = true;
        for (int[] dir : directions.get(grid[i][j])) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            // skip out of bounds or already visited cells
            if (ni < 0 || nj < 0 || ni >= m || nj >= n || vis[ni][nj])
                continue;
            // verify neighbor connects back to current cell (bidirectional check)
            for (int[] backDir : directions.get(grid[ni][nj])) {
                if (ni + backDir[0] == i && nj + backDir[1] == j) {
                    if (dfs(grid, ni, nj, vis))
                        return true;
                }
            }
        }
        return false;
    }
    public boolean hasValidPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        return dfs(grid, 0, 0, vis);
    }
}

// Time Complexity :- O(m * n).
// Space Complexity :- O(m * n).
