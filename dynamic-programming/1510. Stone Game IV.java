/*********************************************** JAVA **************************************************/

// Optimal Solution - Use memoised game DP where a state is winning if any square-number move leaves the opponent in a losing state.
/* “I define dp[n] as whether the current player can win with n stones. For every possible square i², I check the state n - i². If any move produces a losing state for the opponent, 
    the current state is winning. If all possible moves lead to winning states, the current state is losing. Memoisation ensures each state is calculated only once.” */

class Solution {
    // dp[n] stores:
    // 1 -> current player can win with n stones
    // 0 -> current player will lose with n stones
    int[] dp;
    public int solve(int n) {
        // If no stones are left, the current player cannot make a move
        // and therefore loses.
        if (n <= 0) {
            return 0;
        }
        // Return the already calculated result
        if (dp[n] != -1) {
            return dp[n];
        }
        // Try every possible square number:
        // 1, 4, 9, 16, ...
        for (int i = 1; i * i <= n; i++) {
            // If taking i*i leaves the opponent in a losing state,
            // then the current player can force a win.
            if (solve(n - i * i) == 0) {
                return dp[n] = 1;
            }
        }
        // None of the possible moves leads to a losing state
        // for the opponent, so the current player loses.
        return dp[n] = 0;
    }
    public boolean winnerSquareGame(int n) {
        // Create memoisation array
        dp = new int[n + 1];
        // -1 means this state has not been calculated yet
        Arrays.fill(dp, -1);
        // If state n is winning, Alice wins
        return solve(n) == 1;
    }
}

// Time Complexity :- O(n\sqrt n). Because of all the possible square numbers.
// Space Complexity :- O(n). Recursive stack memory.
