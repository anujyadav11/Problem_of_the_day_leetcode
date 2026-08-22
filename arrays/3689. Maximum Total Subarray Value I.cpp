/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximises total operation value by multiplying k operations with the global max-min difference found in a single pass.
/* "Always use 1L * before integer multiplication when the result is stored as long — without it, k * (maxEl - minEl) computes as int and silently overflows before assignment. 
    The greedy insight is that pairing max with min always dominates any other pair — provable by the exchange argument. Single-pass min/max avoids two separate stream operations." */

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int maxEl = Integer.MIN_VALUE;
        int minEl = Integer.MAX_VALUE;
        // find global max and min in a single pass
        for (int num : nums) {
            maxEl = Math.max(maxEl, num);
            minEl = Math.min(minEl, num);
        }
        // maximum total value = k * (max - min)
        return 1L * k * (maxEl - minEl);
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
