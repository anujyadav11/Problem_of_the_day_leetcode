/*********************************************** JAVA **************************************************/

// Optimal Solution - Simulate binary addition and division from LSB to MSB using carry propagation in a single pass.
                      // “Instead of converting binary to integer, I simulate operations directly on bits using carry, which keeps the solution O(n) and avoids overflow.”

class Solution {
    public int numSteps(String s) {
        int steps = 0; // Counts total operations
        int carry = 0; // Represents carry generated during addition
        // Traverse from right to left (LSB → MSB)
        for (int i = s.length() - 1; i > 0; i--) {
            int bit = s.charAt(i) - '0'; // Convert char to integer (0 or 1)
            // If current bit + carry = 1 → number becomes odd
            if (bit + carry == 1) {
                // We need:
                // 1 step for +1 (make it even)
                // 1 step for divide by 2
                steps += 2;
                carry = 1; // Addition creates carry for next bit
            } else {
                // If bit + carry is 0 or 2:
                // Just divide by 2 (right shift)
                steps += 1;
            }
        }
        // If carry remains at MSB, we need one extra step
        return steps + carry;
    }
}

// Time Complexity :- O(N).
// Space Complexity :- O(1).
