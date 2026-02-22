/*********************************************** JAVA **************************************************/

Optimal Solution - Uses bit manipulation to track positions of set bits and compute maximum gap.
                   Track indices of consecutive 1s while shifting bits; compute distance on the fly.

class Solution {
    public int binaryGap(int n) {
        // Current bit position (index from right to left)
        int curr = 0;
        // Stores previous index where bit '1' was found
        int prev = -1;
        // Stores maximum distance between consecutive 1s
        int res = 0;
        // Traverse all bits of n
        while (n > 0) {
            // If current least significant bit is 1
            if ((n & 1) > 0) {
                // If we have seen a previous 1,
                // update maximum distance
                if (prev != -1) {
                    res = Math.max(res, curr - prev);
                }
                // Update previous index to current position
                prev = curr;
            }
            // Move to next bit
            curr++;
            // Right shift n by 1
            n >>= 1;
        }
        return res;
    }
}

Time Complexity :- O(N).
Space Complexity :- O(1).
