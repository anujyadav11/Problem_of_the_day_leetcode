/*********************************************** JAVA **************************************************/

// Optimal Solution - Use sliding window over a circular string to compute minimum flips needed to convert it into an alternating binary string.
                  // “Since rotations are allowed, I simulate s + s and use a sliding window of size n to check flips needed for both alternating patterns.”

class Solution {
    public int minFlips(String s) {
        int n = s.length(); // Length of original string
        int result = Integer.MAX_VALUE; // Stores minimum flips
        int flip1 = 0; // Flips required for pattern "010101..."
        int flip2 = 0; // Flips required for pattern "101010..."
        int i = 0, j = 0; // Sliding window pointers
        // Traverse up to 2*n to simulate circular rotations
        while (j < 2 * n) {
            // Expected characters for both alternating patterns
            char expectedCharS1 = (j % 2 == 1) ? '1' : '0'; // pattern: 010101...
            char expectedCharS2 = (j % 2 == 1) ? '0' : '1'; // pattern: 101010...
            // Check mismatch for pattern 1
            if (s.charAt(j % n) != expectedCharS1)
                flip1++;
            // Check mismatch for pattern 2
            if (s.charAt(j % n) != expectedCharS2)
                flip2++;
            // Maintain window size <= n
            if (j - i + 1 > n) {
                // Expected characters for the element leaving window
                expectedCharS1 = (i % 2 == 1) ? '1' : '0';
                expectedCharS2 = (i % 2 == 1) ? '0' : '1';
                // Adjust flip counts
                if (s.charAt(i % n) != expectedCharS1)
                    flip1--;
                if (s.charAt(i % n) != expectedCharS2)
                    flip2--;
                i++; // Shrink window
            }
            // When window size equals n, update result
            if (j - i + 1 == n)
                result = Math.min(result, Math.min(flip1, flip2));
            j++; // Expand window
        }
        return result;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
