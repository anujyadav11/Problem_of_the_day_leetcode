/*********************************************** JAVA **************************************************/

// Optimal Solution - Count stable binary arrays using dynamic programming with prefix subtraction to enforce consecutive limits efficiently.
/* "I define DP states based on how many zeros and ones are used and what the last element is. When extending the sequence, 
    I add contributions from previous states and subtract configurations that would violate the consecutive limit. This avoids an extra loop over block sizes and reduces complexity to O(zero × one)." */
class Solution {
    // Modulo constant to avoid overflow
    int M = (int)1e9 + 7;
    public int numberOfStableArrays(int zero, int one, int limit) {
        // DP table:
        // t[i][j][0] → number of stable arrays using i zeros and j ones ending with 0
        // t[i][j][1] → number of stable arrays using i zeros and j ones ending with 1
        int[][][] t = new int[zero + 1][one + 1][2];
        // Base case: arrays consisting only of zeros (up to limit)
        for (int i = 1; i <= Math.min(zero, limit); i++)
            t[i][0][0] = 1;
        // Base case: arrays consisting only of ones (up to limit)
        for (int j = 1; j <= Math.min(one, limit); j++)
            t[0][j][1] = 1;
        // Fill DP table
        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                // Skip base cases already handled
                if (i == 0 || j == 0)
                    continue;
                // Case 1: Array ends with 0
                // We append 0 to any valid array with (i-1) zeros and j ones
                int val0 = (t[i-1][j][0] + t[i-1][j][1]) % M;
                // If adding this 0 would exceed the consecutive limit,
                // subtract the invalid configurations
                if (i - 1 >= limit)
                    val0 = (val0 - t[i-1-limit][j][1] + M) % M;
                t[i][j][0] = val0;
                // Case 2: Array ends with 1
                // We append 1 to any valid array with i zeros and (j-1) ones
                int val1 = (t[i][j-1][0] + t[i][j-1][1]) % M;
                // Remove configurations where ones exceed the limit
                if (j - 1 >= limit)
                    val1 = (val1 - t[i][j-1-limit][0] + M) % M;
                t[i][j][1] = val1;
            }
        }
        // Final result = arrays ending with 0 + arrays ending with 1
        return (t[zero][one][0] + t[zero][one][1]) % M;
    }
}

// Time Complexity :-O(zero × one).
// Space Complexity :- O(zero × one).
