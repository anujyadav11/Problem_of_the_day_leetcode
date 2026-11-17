/*********************************************** JAVA **************************************************/

// Optimal Solution - Combined divisor-based substring cost precomputation with bottom-up partition DP to minimize character modifications across exactly k semi-palindromic substrings.
/* "I solve this problem in two phases. First, I precompute the minimum number of character changes required to make every substring semi-palindromic. 
    I do this by trying every proper divisor of the substring length and counting mismatched mirrored characters across the resulting groups.
    Then I use bottom-up partition DP. The state dp[i][pieces] represents the minimum cost of partitioning the suffix starting at index i into exactly the required number of pieces. 
    I try every valid ending position for the first piece and combine its precomputed cost with the optimal cost of the remaining suffix. This avoids recalculating substring conversion costs during DP." */

class Solution {
    private int n;
    private String str;
    // costMemo[i][j] = minimum changes to make s[i...j] semi-palindromic.
    private int[][] costMemo;
    // memo[i][pieces] = minimum cost to partition s[i...n-1]
    // into exactly 'pieces' semi-palindromic substrings.
    private int[][] memo;
    private static final int INF = Integer.MAX_VALUE;
    // Calculate the minimum changes required to make s[i...j]
    // a semi-palindrome.
    private int getCost(int i, int j) {
        int len = j - i + 1;
        int minCost = INF;
        // Try every proper divisor of the substring length.
        for (int d = 1; d < len; d++) {
            // d must divide the substring length.
            if (len % d != 0) {
                continue;
            }
            int cost = 0;
            // Divide the substring into d groups.
            for (int group = 0; group < d; group++) {
                // First character of the current group.
                int left = i + group;
                // Last character of the current group.
                int right = left + (len / d - 1) * d;
                // Compare mirrored characters.
                while (left < right) {
                    if (str.charAt(left) != str.charAt(right)) {
                        cost++;
                    }
                    // Move towards the center of the group.
                    left += d;
                    right -= d;
                }
            }
            // Keep the minimum cost among all valid divisors.
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }
    public int minimumChanges(String s, int k) {
        n = s.length();
        str = s;
        // -----------------------------------------
        // STEP 1: Precompute substring conversion costs.
        // -----------------------------------------
        costMemo = new int[n][n];
        for (int[] row : costMemo) {
            Arrays.fill(row, INF);
        }
        // Only substrings of length >= 2 are needed.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                costMemo[i][j] = getCost(i, j);
            }
        }
        // -----------------------------------------
        // STEP 2: Initialize partition DP.
        // -----------------------------------------
        memo = new int[n + 1][k + 1];
        for (int[] row : memo) {
            Arrays.fill(row, INF);
        }
        // Empty suffix requires zero cost for zero pieces.
        memo[n][0] = 0;
        // -----------------------------------------
        // STEP 3: Bottom-up DP.
        // -----------------------------------------
        for (int start = n; start >= 0; start--) {
            for (int pieces = 1; pieces <= k; pieces++) {
                // Every remaining piece must have at least 2 characters.
                if (start + 2 * pieces > n) {
                    continue;
                }
                int result = INF;
                // Try every possible ending position
                // for the current substring.
                for (int end = start + 1; end < n; end++) {
                    // Cost of partitioning the remaining suffix.
                    int remainingCost = memo[end + 1][pieces - 1];
                    // Skip impossible DP states.
                    if (remainingCost == INF) {
                        continue;
                    }
                    // Current substring cost + remaining suffix cost.
                    int totalCost =
                        costMemo[start][end] + remainingCost;
                    result = Math.min(result, totalCost);
                }
                memo[start][pieces] = result;
            }
        }
        // Minimum cost to partition the entire string into k pieces.
        return memo[0][k];
    }
}

// Time Complexity :- O(n^4 + kn^2).
// Space Complexity :- O(n^2 + nk).
