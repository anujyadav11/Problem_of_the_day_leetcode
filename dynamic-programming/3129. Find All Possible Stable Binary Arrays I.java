/*********************************************** JAVA **************************************************/

// Optimal Solution - Count stable binary arrays using 3D dynamic programming by placing bounded blocks of zeros and ones.
                      /* "Instead of placing elements one by one, I place blocks of zeros or ones up to the given limit. 
                        My DP state tracks how many zeros and ones remain and what the last block type was. This ensures we never exceed the consecutive limit." */

class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int M = 1_000_000_007; // Modulo value for large answers
        // DP table:
        // t[onesLeft][zerosLeft][last]
        // last = 0 → last placed block was 0
        // last = 1 → last placed block was 1
        int[][][] t = new int[one + 1][zero + 1][2];
        // Base case: no elements left to place
        t[0][0][0] = 1;
        t[0][0][1] = 1;
        // Iterate through possible counts of ones and zeros left
        for (int onesLeft = 0; onesLeft <= one; onesLeft++) {
            for (int zerosLeft = 0; zerosLeft <= zero; zerosLeft++) {
                if (onesLeft == 0 && zerosLeft == 0) continue;
                // Case 1: Last placed element was 1 → we must place zeros next
                int result = 0;
                // Place a block of zeros (length from 1 to limit)
                for (int len = 1; len <= Math.min(zerosLeft, limit); len++) {
                    // Transition: remove len zeros and previous block must end with zero
                    result = (result + t[onesLeft][zerosLeft - len][0]) % M;
                }
                // Store result when last placed element is 1
                t[onesLeft][zerosLeft][1] = result;
                // Case 2: Last placed element was 0 → we must place ones next
                result = 0;
                // Place a block of ones
                for (int len = 1; len <= Math.min(onesLeft, limit); len++) {
                    // Transition: remove len ones and previous block must end with one
                    result = (result + t[onesLeft - len][zerosLeft][1]) % M;
                }
                // Store result when last placed element is 0
                t[onesLeft][zerosLeft][0] = result;
            }
        }
        // Start with a block of ones
        int startWithOne = t[one][zero][0];
        // Start with a block of zeros
        int startWithZero = t[one][zero][1];
        // Total stable arrays
        return (startWithOne + startWithZero) % M;
    }
}

// Time Complexity :- O(one × zero × limit).
// Space Complexity :- O(one × zero × 2).
