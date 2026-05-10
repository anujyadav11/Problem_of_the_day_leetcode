 /*********************************************** JAVA **************************************************/

// Optimal Solution - Finds maximum jumps from start to end using right-to-left DP, allowing forward jumps where absolute value difference stays within target.
/* "Right-to-left DP is natural here — future states are already computed when we need them. Integer.MIN_VALUE as sentinel distinguishes unreachable indices from valid zero-jump states. 
    The dp[j] != Integer.MIN_VALUE guard prevents adding 1 to an unreachable state which would wrap around or give wrong results." */

class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        // dp[i] = maximum jumps from index i to index n-1
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        // base case: already at last index — 0 jumps needed
        dp[n - 1] = 0;
        // fill right to left
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // valid jump: absolute difference within target and j is reachable
                if (Math.abs(nums[j] - nums[i]) <= target && dp[j] != Integer.MIN_VALUE) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }
        // return -1 if start index cannot reach end
        return dp[0] == Integer.MIN_VALUE ? -1 : dp[0];
    }
}

// Time Complexity :- O(n^2).
// Space Complexity :- O(n).
