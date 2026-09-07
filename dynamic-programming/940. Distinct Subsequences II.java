/*********************************************** JAVA **************************************************/

// Optimal Solution - Count distinct subsequences using iterative DP and previous character positions to eliminate duplicate subsequences.
/* “For every character, I double the previous number of subsequences because I can either include or exclude the character. 
    If that character appeared earlier, some of the newly generated subsequences are duplicates, so I subtract the number of subsequences that existed before its previous occurrence.” */

class Solution {
    int M = 1000000007;
    // dp[n] = number of distinct subsequences using first n characters
    int[] dp = new int[2001];
    // prev[n] = previous occurrence of the nth character
    // using 1-based indexing
    int[] prev;
    int solve(int n) {
        // Empty string has one subsequence: the empty subsequence
        if (n == 0)
            return 1;
        // Return already calculated result
        if (dp[n] != -1)
            return dp[n];
        // Each subsequence can either take or skip the current character
        int total = (int) (2L * solve(n - 1) % M);
        // Remove duplicate subsequences caused by previous occurrence
        if (prev[n] != 0) {
            int duplicates = solve(prev[n] - 1);
            total = (total - duplicates + M) % M;
        }
        return dp[n] = total;
    }
    public int distinctSubseqII(String s) {
        int n = s.length();
        // Mark all DP states as uncomputed
        Arrays.fill(dp, -1);
        // Store previous occurrence for each character
        prev = new int[n + 1];
        int[] lastSeen = new int[26];
        for (int i = 1; i <= n; i++) {
            int idx = s.charAt(i - 1) - 'a';
            // Store previous occurrence of this character
            prev[i] = lastSeen[idx];
            // Update latest occurrence
            lastSeen[idx] = i;
        }
        // Remove the empty subsequence
        return (solve(n) - 1 + M) % M;
    }
}

class Solution {
    int M = 1000000007;
    // dp[n] = number of distinct subsequences using first n characters
    int[] dp = new int[2001];
    // prev[n] = previous occurrence of the nth character
    // using 1-based indexing
    int[] prev;
    public int distinctSubseqII(String s) {
        int n = s.length();
        // Mark all DP states as uncomputed
        Arrays.fill(dp, -1);
        // Store previous occurrence for each character
        prev = new int[n + 1];
        int[] lastSeen = new int[26];
        for (int i = 1; i <= n; i++) {
            int idx = s.charAt(i - 1) - 'a';
            // Store previous occurrence of this character
            prev[i] = lastSeen[idx];
            // Update latest occurrence
            lastSeen[idx] = i;
        }
        // Empty string has one subsequence
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            // Every subsequence can either take or skip s[i - 1]
            int total = 2 * dp[i - 1] % M;
            // Remove duplicates caused by previous occurrence
            if (prev[i] != 0) {
                int dups = dp[prev[i] - 1];
                total = (total - dups + M) % M;
            }
            dp[i] = total;
        }
        // Remove the empty subsequence
        return (dp[n] - 1 + M) % M;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
