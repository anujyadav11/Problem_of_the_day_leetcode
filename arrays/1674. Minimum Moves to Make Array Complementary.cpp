/*********************************************** JAVA **************************************************/

// Optimal Solution - Minimizes moves to equalize pair sums using a difference array with three-tier range updates for 0, 1, and 2 move costs across all valid target sums.
/* "The difference array converts three overlapping range updates per pair into O(1) operations — without it each pair would need O(limit) updates. The three tiers capture the cost structure: exact match costs 0, 
    one element change costs 1, two element changes costs 2. Prefix sum then finds the optimal target sum in O(limit)." */

class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        // difference array for range updates — size 2*limit+2 covers all sums [2, 2*limit]
        int[] diff = new int[2 * limit + 2];
        for (int i = 0; i < n / 2; i++) {
            int lo = Math.min(nums[i], nums[n - i - 1]);
            int hi = Math.max(nums[i], nums[n - i - 1]);
            // range [2, 2*limit]: all pairs need at most 2 moves
            diff[2] += 2;
            diff[2 * limit + 1] -= 2;
            // range [lo+1, hi+limit]: need at most 1 move (change one element)
            diff[lo + 1] -= 1;
            diff[hi + limit + 1] += 1;
            // range [lo+hi, lo+hi]: need 0 moves (sum already achievable)
            diff[lo + hi] -= 1;
            diff[lo + hi + 1] += 1;
        }
        // prefix sum to find minimum moves across all valid sums
        int ans = n;
        int sum = 0;
        for (int i = 2; i <= 2 * limit; i++) {
            sum += diff[i];
            ans = Math.min(ans, sum);
        }
        return ans;
    }
}

// Time Complexity :- O(n * limit).
// Space Complexity :- O(limit).
