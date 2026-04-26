/*********************************************** JAVA **************************************************/

// Optimal Solution - Detects cycles in same-character grid regions using DFS with parent tracking to skip trivial back-edges and visited array to catch true cycles.
/* "The parent tracking (prevI, prevJ) is essential — without it, every undirected edge would look like a cycle since you can immediately go back where you came from. 
    Once a cell is marked visited it stays marked — unlike backtracking problems — because any revisit from any path confirms a cycle in the same character region." */

class Solution {
    int m, n;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // start DFS from every unvisited cell
                if (!vis[i][j] && cycleDFS(i, j, i, j, grid, vis))
                    return true;
            }
        }
        return false;
    }
    public boolean cycleDFS(int i, int j, int prevI, int prevJ, char[][] grid, boolean[][] vis) {
        // already visited this cell — cycle detected
        if (vis[i][j]) return true;
        vis[i][j] = true;
        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            // only move to valid same-character cells
            if (ni >= 0 && nj >= 0 && ni < m && nj < n && grid[ni][nj] == grid[i][j]) {
                // skip the cell we came from to avoid trivial back-edge
                if (ni == prevI && nj == prevJ) continue;
                if (cycleDFS(ni, nj, i, j, grid, vis))
                    return true;
            }
        }
        return false;
    }
}

// Time Complexity :- O(m * n). size of char grid.
// Space Complexity :- O(m * n). recursion stack and visited array.
