/*********************************************** JAVA **************************************************/

// BruteForce Solution - Find the first stable split by comparing the prefix maximum with the suffix minimum.
/* “I check every possible split index. For each index, I calculate the maximum value in the prefix and the minimum value in the suffix. 
    If their difference is at most k, I immediately return that index because I’m scanning from left to right.” */

class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        // Try every possible split index
        for (int i = 0; i < n; i++) {
            int maxEl = Integer.MIN_VALUE;
            int minEl = Integer.MAX_VALUE;
            // Find maximum in the left part [0 ... i]
            for (int j = 0; j <= i; j++) {
                maxEl = Math.max(maxEl, nums[j]);
            }
            // Find minimum in the right part [i ... n-1]
            for (int j = i; j <= n - 1; j++) {
                minEl = Math.min(minEl, nums[j]);
            }
            // Check if this split satisfies the condition
            if (maxEl - minEl <= k) {
                return i;
            }
        }
        return -1;
    }
}

// Time Complexity :- O(n^2).
// Space Complexity :- O(1).
