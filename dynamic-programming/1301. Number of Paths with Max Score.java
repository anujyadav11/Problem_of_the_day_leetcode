/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds maximum score path count in grid using bottom-up DP with rolling arrays, tracking best score and way count from three diagonal neighbors per cell.
/* "Rolling arrays reduce O(n²) space to O(n) — only previous row needed for transitions. The -1 sentinel elegantly handles unreachability without a separate boolean array. 
    Counting ways requires matching all neighbors with the best score — any neighbor achieving best contributes its way count, summed with modulo to prevent overflow." */

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        final int MOD = 1_000_000_007;
        int n = board.size();
        // dp arrays for current and next row
        int[] nextScore = new int[n + 1];
        int[] nextWays = new int[n + 1];
        // -1 means unreachable
        Arrays.fill(nextScore, -1);
        for (int i = n - 1; i >= 0; i--) {
            int[] currScore = new int[n + 1];
            int[] currWays = new int[n + 1];
            Arrays.fill(currScore, -1);
            for (int j = n - 1; j >= 0; j--) {
                char cell = board.get(i).charAt(j);
                // blocked cell — skip
                if (cell == 'X') continue;
                // start cell — initialize as reachable with 0 score
                if (cell == 'S') {
                    currScore[j] = 0;
                    currWays[j] = 1;
                    continue;
                }
                // find best score reachable from three neighbors (down, right, diagonal)
                int best = Math.max(nextScore[j],
                           Math.max(currScore[j + 1], nextScore[j + 1]));
                // no reachable neighbor — cell unreachable
                if (best == -1) continue;
                // count ways to achieve best score from all matching neighbors
                long ways = 0;
                if (nextScore[j] == best)     ways += nextWays[j];
                if (currScore[j + 1] == best) ways += currWays[j + 1];
                if (nextScore[j + 1] == best) ways += nextWays[j + 1];
                // E is end cell — contributes 0 value
                int value = (cell == 'E') ? 0 : cell - '0';
                currScore[j] = best + value;
                currWays[j] = (int) (ways % MOD);
            }
            nextScore = currScore;
            nextWays = currWays;
        }
        // unreachable end cell
        if (nextScore[0] == -1) return new int[]{0, 0};
        return new int[]{nextScore[0], nextWays[0]};
    }
}

// Time Complexity :- O(n^2).
// Space Complexity :- O(n).
