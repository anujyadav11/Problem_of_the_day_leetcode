/******************************************************** JAVA ****************************************************/

Optimal solution - We use dynamic programming to compare both strings character by character and compute the minimum ASCII cost of deletions required to make their prefixes equal, 
                    choosing the cheaper deletion whenever characters do not match.


class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // dp[i][j] = minimum ASCII delete sum
        // to make s1[0..i-1] and s2[0..j-1] equal
        int[][] dp = new int[m + 1][n + 1];
        // Build DP table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // If s1 is empty, delete all characters of s2
                if (i == 0) {
                    dp[i][j] = (j > 0) ? dp[i][j - 1] + s2.charAt(j - 1) : 0;
                // If s2 is empty, delete all characters of s1
                } else if (j == 0) {
                    dp[i][j] = (i > 0) ? dp[i - 1][j] + s1.charAt(i - 1) : 0;
                // If characters match, no deletion needed
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                // Otherwise, delete from either s1 or s2 and take minimum cost
                } else {
                    dp[i][j] = Math.min(
                            dp[i - 1][j] + s1.charAt(i - 1),
                            dp[i][j - 1] + s2.charAt(j - 1)
                    );
                }
            }
        }
        // Result is minimum delete sum for full strings
        return dp[m][n];
    }
}

Time Complexity - O(m * n).
Space Complexity - O(m * n).
