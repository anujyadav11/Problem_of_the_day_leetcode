/*********************************************** JAVA **************************************************/

// Optimal Solution - Validates a good array by checking elements 1 to n-2 appear exactly once and n-1 appears exactly twice using a frequency count array.
/* "Always prefer frequency arrays over HashMaps for bounded integer ranges — O(1) access with no hashing overhead. The key insight is that a 'good' array of length n has base = n-1, 
    so valid elements are strictly in [1, base]. Out-of-range elements can be rejected immediately before counting, saving unnecessary work." */

class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length;
        // good array has n-1 elements so base number is n-1
        int base = n - 1;
        // count frequency of each element
        int[] freq = new int[n + 1];
        for (int num : nums) {
            // any element out of valid range [1, base] is invalid
            if (num < 1 || num > base)
                return false;
            freq[num]++;
        }
        // check 1 to base-1 appear exactly once, base appears exactly twice
        for (int i = 1; i < base; i++)
            if (freq[i] != 1)
                return false;
        return freq[base] == 2;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
