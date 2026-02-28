/*********************************************** JAVA **************************************************/

// Optimal Solution - Concatenate binary numbers using bit shifting while tracking bit-length increases at powers of two.
                    // “Instead of building binary strings, I track bit-length changes at powers of two and use left shift to simulate binary concatenation efficiently.”

class Solution {
    public int concatenatedBinary(int n) {  
        int bitLength = 0;     // Tracks number of bits needed to represent current number
        long result = 0;       // Stores concatenated value (use long to prevent overflow)
        final long MOD = (long) (1e9 + 7);  // Modulo value
        // Iterate from 1 to n
        for (int i = 1; i <= n; i++) {
            // If i is a power of 2, its bit-length increases by 1
            // (i & (i - 1)) == 0 → true only for powers of 2
            if ((i & (i - 1)) == 0)
                bitLength++;
            // Left shift current result by bitLength
            // Then add current number
            result = ((result << bitLength) + i) % MOD;
        }
        return (int) result;  // Return final result
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
