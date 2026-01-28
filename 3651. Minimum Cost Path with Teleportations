/*********************************************** JAVA **************************************************/

Optimal Solution - Dynamic programming with value-based relaxation repeated k times to compute the minimum path cost in a weighted grid.
                    Each iteration propagates cheaper paths globally across equal-value cells, similar to relaxation in shortest-path algorithms.

class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        // Store all grid coordinates as points
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                points.add(new int[] { i, j });
            }
        }
        // Sort points based on their grid values
        points.sort(Comparator.comparingInt(p -> grid[p[0]][p[1]]));
        // DP array: minimum cost to reach bottom-right from (i, j)
        int[][] costs = new int[m][n];
        for (int[] row : costs) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // Perform k + 1 relaxations
        for (int t = 0; t <= k; t++) {
            int minCost = Integer.MAX_VALUE;
            // Group points having the same grid value
            for (int i = 0, j = 0; i < points.size(); i++) {
                // Track minimum cost seen so far
                minCost = Math.min(
                    minCost,
                    costs[points.get(i)[0]][points.get(i)[1]]
                );
                // Continue while same value group exists
                if (
                    i + 1 < points.size() &&
                    grid[points.get(i)[0]][points.get(i)[1]] ==
                    grid[points.get(i + 1)[0]][points.get(i + 1)[1]]
                ) {
                    continue;
                }
                // Assign the same minimum cost to all points in this group
                for (int r = j; r <= i; r++) {
                    costs[points.get(r)[0]][points.get(r)[1]] = minCost;
                }
                j = i + 1;
            }
            // Standard bottom-up DP traversal
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    // Base case: destination cell
                    if (i == m - 1 && j == n - 1) {
                        costs[i][j] = 0;
                        continue;
                    }
                    // Move down
                    if (i != m - 1) {
                        costs[i][j] = Math.min(
                            costs[i][j],
                            costs[i + 1][j] + grid[i + 1][j]
                        );
                    }
                    // Move right
                    if (j != n - 1) {
                        costs[i][j] = Math.min(
                            costs[i][j],
                            costs[i][j + 1] + grid[i][j + 1]
                        );
                    }
                }
            }
        }
        // Minimum cost from top-left
        return costs[0][0];
    }
}

Time Complexity :- O().
Space Complexity :- O().
