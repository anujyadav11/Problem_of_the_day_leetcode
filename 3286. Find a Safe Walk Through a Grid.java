/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds safe path using Dijkstra to minimize total damage taken, returning true if minimum damage path leaves positive health at destination.
/*  "Dijkstra works here because we want minimum total damage — greedy shortest path on a weighted grid. The visited marking via Integer.MAX_VALUE is the key optimization — prevents O(m²n²) reprocessing. 
    Always verify the final health check at destination: health - cost > 0 not >= 0 since zero health means dead." */

class Solution {
    int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public boolean findSafeWalk(List<List<Integer>> arr, int health) {
        int m = arr.size();
        int n = arr.get(0).size();
        // convert List<List<Integer>> to int[][] for easier access
        int[][] grid = arr.stream()
            .map(inner -> inner.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new);
        // min heap on accumulated health cost — find minimum damage path
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});
        // mark start as visited
        grid[0][0] = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cost = curr[0], x = curr[1], y = curr[2];
            // reached destination — check if remaining health > 0
            if (x == m - 1 && y == n - 1)
                return health - cost > 0;
            for (int[] d : dir) {
                int r = x + d[0];
                int c = y + d[1];
                // skip out of bounds or visited cells
                if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == Integer.MAX_VALUE)
                    continue;
                int nextCost = cost + grid[r][c];
                // only proceed if we still have health remaining
                if (health - nextCost > 0) {
                    pq.offer(new int[]{nextCost, r, c});
                    // mark as visited to prevent re-processing
                    grid[r][c] = Integer.MAX_VALUE;
                }
            }
        }
        return false;
    }
}

// Time Complexity :- O(m × n × log(m × n)).— Dijkstra with priority queue over all cells
// Space Complexity :- O(m x n).— priority queue and modified grid
