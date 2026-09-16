/*********************************************** JAVA **************************************************/

// Optimal Solution - Uses 1D DP with a running prefix sum to count non-overlapping segment configurations in O(nk) time and O(n) space.
/* “I use dynamic programming where each iteration adds one more segment. Instead of storing every DP state in a 2D table, 
    I compress it into one dimension. A running prefix sum lets me calculate the transition in constant time, giving O(nk) time and O(n) space.” */

class Solution {
    private static final int MOD = 1000000007;
    public int numberOfSets(int n, int k) {
        // dp[j] represents the number of ways to form
        // the current number of segments using j points.
        long[] dp = new long[n + 1];
        dp[1] = 1;
        // Build the answer one segment at a time.
        for (int i = 0; i < k; i++) {
            long sum = 0;
            for (int j = 1; j <= n; j++) {
                // Prefix sum of the previous DP values.
                sum = (sum + dp[j]) % MOD;
                // Include the current point as a possible
                // endpoint of the new segment.
                dp[j] = (sum + dp[j - 1]) % MOD;
            }
        }
        // Sum valid configurations.
        long res = 0;
        for (int i = 0; i < n - k + 1; i++) {
            res = (res + dp[i]) % MOD;
        }
        return (int) res;
    }
}

// Time Complexity :- O(n * k).
// Space Complexity :- O(n).
