/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximises Alice's stone collection using minimax DP where Alice maximises and Bob minimises across all valid pile-taking choices per turn.
/* "Minimax with memoisation — Alice maximises, Bob minimises. Initialise Alice's base to 0, not -1, since taking 0 stones is valid when no piles remain. 
    The Math.max(M, x) update ensures M grows when more piles are taken, enabling more choices in future turns. This is O(n³) which is acceptable for n ≤ 100." */

class Solution {
    int n;
    // t[person][i][M] = best result for Alice from state (person, i, M)
    int[][][] t;
    public int solveForAlice(int[] piles, int person, int i, int M) {
        // no piles remaining
        if (i >= n) return 0;
        // return cached result
        if (t[person][i][M] != -1) return t[person][i][M];
        // Alice maximises, Bob minimises Alice's stones
        int res = (person == 1) ? 0 : Integer.MAX_VALUE;
        int stones = 0;
        // current player takes between 1 and min(2M, remaining) piles
        for (int x = 1; x <= Math.min(2 * M, n - i); x++) {
            stones += piles[i + x - 1];
            if (person == 1) {
                // Alice's turn — add stones taken and recurse for Bob
                res = Math.max(res, stones + solveForAlice(piles, 0, i + x, Math.max(M, x)));
            } else {
                // Bob's turn — Bob minimises Alice's total
                res = Math.min(res, solveForAlice(piles, 1, i + x, Math.max(M, x)));
            }
        }
        return t[person][i][M] = res;
    }
    public int stoneGameII(int[] piles) {
        n = piles.length;
        // 2 players × n pile indices × n possible M values
        t = new int[2][101][101];
        for (int[][] layer : t)
            for (int[] row : layer)
                Arrays.fill(row, -1);
        // Alice starts at pile 0 with M = 1
        return solveForAlice(piles, 1, 0, 1);
    }
}

// Time Complexity :- O(n³) — n pile indices × n M values × n choices per state.
// Space Complexity :- O(n²) — DP table of size 2 × n × n.
