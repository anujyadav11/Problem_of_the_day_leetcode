/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts majority subarrays in O(n) using prefix score mapping (target→+1, other→-1) and cumulative frequency arrays to count valid subarray starts.
/* "This is an O(n) upgrade from O(n²) brute force. The key insight: target is majority in [i,j] iff prefix score at j > prefix score at i-1. 
    Counting positions with strictly lower score via cumulative array gives all valid starts in O(1) per position. The n+1 offset handles negative indices cleanly without a HashMap." */

class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int len = 2 * n + 2;
        // countAtSum[i] = how many times prefix sum i has been seen
        long[] countAtSum = new long[len];
        // cumulativeCount[i] = sum of countAtSum[0..i]
        long[] cumulativeCount = new long[len];
        // start at idx = n+1 to avoid negative indices (offset by n+1)
        int idx = n + 1;
        countAtSum[idx] = 1;
        cumulativeCount[idx] = 1;
        long ans = 0;
        for (int num : nums) {
            // target increments score, others decrement — like Boyer-Moore
            if (num == target)
                idx++;
            else
                idx--;
            countAtSum[idx]++;
            // subarrays ending here where target is majority:
            // current idx > previous idx means target appeared more than half
            ans += cumulativeCount[idx - 1];
            // update cumulative count at current idx
            cumulativeCount[idx] = cumulativeCount[idx - 1] + countAtSum[idx];
        }
        return ans;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
