/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximises stone game score difference using backward DP on prefix sums, choosing between taking the current prefix sum or deferring to the next index.
/* "The minimax collapses elegantly — take = prefixSum[i] - t[i+1] directly encodes 'I gain prefixSum[i], opponent's best gain is t[i+1]'. 
    No need for separate player tracking since the score difference auto-flips. Space can be reduced to O(1) by replacing t[i+1] with a single prev variable — mention this optimisation." */

class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        // prefix sum — prefixSum[i] = sum of stones[0..i]
        int[] prefixSum = new int[n];
        prefixSum[0] = stones[0];
        for (int i = 1; i < n; i++)
            prefixSum[i] = prefixSum[i - 1] + stones[i];
        // t[i] = best score difference (current player - opponent) when next pick starts at i
        // base case: last stone must be taken with all before it
        int[] t = new int[n];
        t[n - 1] = prefixSum[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            // take: current player picks prefix sum up to i, opponent plays optimally after
            int take = prefixSum[i] - t[i + 1];
            // skip: current player defers this choice to next index
            int skip = t[i + 1];
            t[i] = Math.max(take, skip);
        }
        // answer starts from index 1 — first valid pick is at least 2 stones
        return t[1];
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
