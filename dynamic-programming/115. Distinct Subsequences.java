/*********************************************** JAVA **************************************************/

// Optimal Solution - Count distinct subsequences using 2D DP with include/skip decisions when characters match.
/* “I define dp[i][j] as the number of ways to form the first j characters of t using the first i characters of s. When the current characters match, 
    I can either use the character or skip it, so I add the two corresponding states. When they don’t match, I can only skip the current character from s. 
    The base case is that an empty target has exactly one way to be formed.” 
              match
               ↓
          ┌──────────┐
          │          │
        TAKE       SKIP
          │          │
    dp[i-1][j-1]   dp[i-1][j]
    */

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        // dp[i][j] = number of ways to form first j characters
        // of t using the first i characters of s
        int[][] dp = new int[m + 1][n + 1];
        // Empty t can always be formed in exactly one way
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        // Non-empty t cannot be formed from an empty s
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // Two choices:
                    // 1. Use this character
                    // 2. Skip this character
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // Characters don't match, so skip s[i - 1]
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}

// Time Complexity :- O(m * n).
// Space Complexity :- O(m * n).
