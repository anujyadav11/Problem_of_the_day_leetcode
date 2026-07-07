/*********************************************** JAVA **************************************************/

// Optimal Solution - Computes product of non-zero digit concatenation and non-zero digit sum by filtering zeros during single digit traversal.
/* "x * 10 + digit is the standard digit concatenation pattern — builds the number left to right by shifting existing digits one place and appending the new digit. 
    Always use long for both accumulators since x can approach n in magnitude and x * s could overflow int. The zero-skip condition c != '0' cleanly handles zeros in both computations simultaneously." */

class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long s = 0;
        for (char c : String.valueOf(n).toCharArray()) {
            if (c != '0') {
                // build number from non-zero digits
                x = x * 10 + (c - '0');
                // sum of non-zero digits
                s += (c - '0');
            }
        }
        // product of concatenated non-zero digits number and their sum
        return x * s;
    }
}

// Time Complexity :- O(d).
// Space Complexity :- O(d).
