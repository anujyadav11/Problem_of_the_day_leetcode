/*********************************************** JAVA **************************************************/

// Optimal Solution - Find the first stable index in O(n) by combining a precomputed suffix minimum array with a running prefix maximum.
/* “I optimize the brute-force solution by precomputing the minimum value from every index to the end of the array. Then I scan from left to right while maintaining the maximum value seen so far. 
    At each index, I compare the prefix maximum with the precomputed suffix minimum. This reduces the solution from O(n²) to O(n).” */

class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        // Store the minimum element from each index to the end
        int[] minFromIdx = new int[n];
        int minEl = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            minEl = Math.min(minEl, nums[i]);
            minFromIdx[i] = minEl;
        }
        int maxEl = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // Maintain maximum element from 0 to i
            maxEl = Math.max(maxEl, nums[i]);
            // Check prefix max - suffix min condition
            if (maxEl - minFromIdx[i] <= k) {
                return i;
            }
        }
        return -1;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
