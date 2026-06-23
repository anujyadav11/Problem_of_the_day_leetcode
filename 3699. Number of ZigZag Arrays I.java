/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts zigzag arrays of length n with values in [l,r] using DP with prefix/suffix sums to efficiently transition between up/down states.
/* "The key optimization is prefix/suffix sums — without them each transition is O(m) per element giving O(n×m²) total. With prefix sums, each newUp[j] and newDown[j] computes in O(1), reducing to O(n×m). 
    The up/down separation elegantly tracks the alternating constraint — up[j] means we arrived going up so next must go down." */

class Solution {
    public int zigZagArrays(int n, int l, int r) {
        final int MOD = 1_000_000_007;
        // m = number of distinct values in [l, r]
        int m = r - l + 1;
        // up[j] = ways to form sequence ending at value j expecting next to go down
        long[] up = new long[m];
        // down[j] = ways to form sequence ending at value j expecting next to go up
        long[] down = new long[m];
        // base case: single element is valid for both directions
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);
        for (int i = 2; i <= n; i++) {
            // prefix sum of down — for newUp[j] need sum of down[0..j-1]
            long[] preDown = new long[m + 1];
            for (int j = 0; j < m; j++)
                preDown[j + 1] = (preDown[j] + down[j]) % MOD;
            // suffix sum of up — for newDown[j] need sum of up[j+1..m-1]
            long[] sufUp = new long[m + 1];
            for (int j = m - 1; j >= 0; j--)
                sufUp[j] = (sufUp[j + 1] + up[j]) % MOD;
            long[] newUp = new long[m];
            long[] newDown = new long[m];
            for (int j = 0; j < m; j++) {
                // newUp[j]: previous value < j — sum of down[0..j-1]
                newUp[j] = preDown[j];
                // newDown[j]: previous value > j — sum of up[j+1..m-1]
                newDown[j] = sufUp[j + 1];
            }
            up = newUp;
            down = newDown;
        }
        // sum all valid endings across both directions
        long ans = 0;
        for (int j = 0; j < m; j++)
            ans = (ans + up[j] + down[j]) % MOD;
        return (int) ans;
    }
}

// Time Complexity :- O(m * n). — n steps each with O(m) prefix/suffix computation
// Space Complexity :- O(m). — four arrays of size m reused per step
