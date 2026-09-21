/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts subarrays by product remainder using O(nk) remainder-state DP with rolling state compression.
/* “I use DP where dp[r] represents the number of subarrays ending at the previous position whose product has remainder r modulo k. 
    For each new number, I start a new one-element subarray and also extend every previous subarray. When a subarray with remainder r is extended by num, 
    its new remainder is (r * num) % k. After processing the current element, I add all states in dp to the global result because every one represents a valid subarray ending at the current position.” */

class Solution {
    public long[] resultArray(int[] nums, int k) {
        // res[r] = total number of subarrays
        // whose product % k == r
        long[] res = new long[k];
        // dp[r] = number of subarrays ending at the
        // previous position with product % k == r
        long[] dp = new long[k];
        for (int num : nums) {
            long[] next_dp = new long[k];
            // Start a new subarray containing only num
            next_dp[num % k] += 1;
            // Extend every subarray ending at the previous index
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newR = (int) (((long) r * num) % k);
                    next_dp[newR] += dp[r];
                }
            }
            // Move to the current position
            dp = next_dp;
            // Add all subarrays ending here to the global answer
            for (int r = 0; r < k; r++) {
                res[r] += dp[r];
            }
        }
        return res;
    }
}

// Time Complexity :- O(n * k).
// Space Complexity :- O(k).
