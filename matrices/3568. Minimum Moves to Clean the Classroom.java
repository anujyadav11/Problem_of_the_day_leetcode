/*********************************************** JAVA **************************************************/

// Optimal Solution- Find the minimum moves to collect all litter using BFS with bitmasking and energy-state optimisation.
/* "I use BFS because every movement costs one move. The state consists of the current cell and a bitmask representing which litter cells have been collected. 
    Energy is also part of the state, but instead of storing every possible energy value, I store the maximum energy achieved for each (row, column, mask). 
    If I reach the same state with less or equal energy, I discard it because it can never produce a better result. Recharge cells reset the energy, 
    and once the mask contains all litter, BFS guarantees that the number of moves is minimum.” */

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        // Assign an ID to every litter cell
        int[][] id = new int[m][n];
        for (int r = 0; r < m; r++) {
            java.util.Arrays.fill(id[r], -1);
        }
        int k = 0;
        int sr = 0, sc = 0;
        // Find start position and assign IDs to litter
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (classroom[r].charAt(c) == 'S') {
                    sr = r;
                    sc = c;
                } else if (classroom[r].charAt(c) == 'L') {
                    id[r][c] = k++;
                }
            }
        }
        // No litter to collect
        if (k == 0)
            return 0;
        // Mask with all litter bits set
        int totalMask = (1 << k) - 1;
        // best[r][c][mask] = maximum energy seen for this state
        int[][][] best = new int[m][n][1 << k];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                java.util.Arrays.fill(best[r][c], -1);
            }
        }
        // BFS state
        class State {
            int r, c, mask, e, moves;

            State(int r, int c, int mask, int e, int moves) {
                this.r = r;
                this.c = c;
                this.mask = mask;
                this.e = e;
                this.moves = moves;
            }
        }
        java.util.ArrayDeque<State> queue = new java.util.ArrayDeque<>();
        // Start BFS from S
        best[sr][sc][0] = energy;
        queue.offer(new State(sr, sc, 0, energy, 0));
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            State cur = queue.poll();
            // Try all four directions
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                // Ignore cells outside the grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;
                // Cannot move through walls
                if (classroom[nr].charAt(nc) == 'X')
                    continue;
                // Moving costs one energy
                int ne = cur.e - 1;
                if (ne < 0)
                    continue;
                // Carry collected litter
                int nmask = cur.mask;
                // Recharge energy at a recharge cell
                if (classroom[nr].charAt(nc) == 'R') {
                    ne = energy;
                }
                // Mark litter as collected
                if (classroom[nr].charAt(nc) == 'L') {
                    nmask |= (1 << id[nr][nc]);
                }
                // All litter collected
                if (nmask == totalMask) {
                    return cur.moves + 1;
                }
                // Skip if this state was reached with more energy
                if (ne <= best[nr][nc][nmask])
                    continue;
                best[nr][nc][nmask] = ne;
                queue.offer(
                    new State(nr, nc, nmask, ne, cur.moves + 1)
                );
            }
        }
        // Impossible to collect all litter
        return -1;
    }
}
// Time Complexity :- O(m * n 2^k).
// Space Complexity :- O(m * n 2^k).
