/************************************************ JAVA *********************************************/

Optimal Solution - We use dynamic programming, where at each index we either skip an element from one array or take the current pair and extend a previous subsequence only 
                    If it increases the dot product, ensuring at least one pair is chosen even when all values are negative.


class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        // dp[i][j] = maximum dot product using
        // first i elements of nums1 and first j elements of nums2
        int[][] dp = new int[n + 1][m + 1];
        //Initialise dp with minimal values
        // to handle negative products correctly
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        // Build DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // Product of current pair
                int prod = nums1[i - 1] * nums2[j - 1];
                // Three choices:
                // 1) Skip nums2[j-1]
                // 2) Skip nums1[i-1]
                // 3) Take the current pair and optionally extend the previous subsequence
                dp[i][j] = Math.max(
                        dp[i][j - 1],
                        Math.max(
                                dp[i - 1][j],
                                prod + Math.max(0, dp[i - 1][j - 1])
                        )
                )
            }
        }
        // Final answer
        return dp[n][m];
    }
}

Time Complexity :- O(N * M).
Space Complexity :- O(N * M).
