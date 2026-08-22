/*********************************************** JAVA **************************************************/

// Optimal Solution - Determines stone game winner using minimax DP where each player maximizes their score while opponent minimizes it, comparing Alex's total against half the sum.
/* "Note that this problem always returns true mathematically — first player always wins since they can choose odd or even piles optimally. But the DP approach generalizes to variants where this isn't guaranteed. 
    The min for opponent's choices is key — after you pick, opponent will minimize your future gains by making the best choice for themselves." */

class Solution {
    int n;
    int[][] t = new int[501][501];
    private int solve(int[] piles, int i, int j) {
        // no stones left — score difference is 0
        if (i > j) return 0;
        // return cached result
        if (t[i][j] != -1) return t[i][j];
        // take pile i — opponent plays optimally on remaining, take minimum of their choices
        int chooseI = piles[i] + Math.min(solve(piles, i + 2, j), solve(piles, i + 1, j - 1));
        // take pile j — opponent plays optimally on remaining, take minimum of their choices
        int chooseJ = piles[j] + Math.min(solve(piles, i, j - 2), solve(piles, i + 1, j - 1));
        // current player picks the option maximizing their own score
        return t[i][j] = Math.max(chooseI, chooseJ);
    }
    public boolean stoneGame(int[] piles) {
        n = piles.length;
        for (int[] row : t)
            Arrays.fill(row, -1);
        int sum = 0;
        for (int p : piles) sum += p;
        int alexScore = solve(piles, 0, n - 1);
        // Alex wins if his score exceeds half the total
        return alexScore > sum / 2;
    }
}

// Time Complexity :- O(n^2).— at most n² unique states each computed once
// Space Complexity :- O(n^2).— memoization table of size 501 × 501
