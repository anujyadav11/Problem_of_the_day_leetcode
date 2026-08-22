/*********************************************** JAVA **************************************************/

// Optimal Solution - Applies step-multiplications over query ranges in-place under MOD, then returns XOR of all resulting elements.
/*  "The 1L * before the multiplication is critical — without it, nums[l] * v overflows int before the cast to long can help. 
    Always widen to long before any multiplication that could exceed 2^31, then narrow back with a cast after taking MOD." */

class Solution {
    int M = (int) 1e9 + 7;
    public int xorAfterQueries(int[] nums, int[][] queries) {
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            int k = query[2];
            int v = query[3];
            // multiply every k-th element in range [l, r] by v under MOD
            while (l <= r) {
                // cast to long to prevent overflow before taking mod
                nums[l] = (int) ((1L * nums[l] * v) % M);
                // jump k steps to next target index
                l += k;
            }
        }
        int result = 0;
        // XOR all elements after all queries are applied
        for (int num : nums)
            result ^= num;
        // return XOR of all modified elements
        return result;
    }
}

// Time Complexity :- O(q × n/k).
// Space Complexity :- O(1).
