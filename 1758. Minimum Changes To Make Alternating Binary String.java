/*********************************************** JAVA **************************************************/

// Optimal Solution - Count mismatches for both possible alternating patterns and return the minimum flips required.
                    // “Since only two alternating patterns exist, I compute mismatches for one and derive the other using n - mismatches, then return the minimum.”

class Solution {
    public int minOperations(String s) {
        int n = s.length(); // Length of string
        int startZero = 0; // Flips needed if pattern starts with '0'
        // Check mismatches assuming pattern: "010101..."
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) { // Even index → expected '0'
                if (s.charAt(i) == '1') {
                    startZero++; // Flip required
                }
            } else { // Odd index → expected '1'
                if (s.charAt(i) == '0') {
                    startZero++; // Flip required
                }
            }
        }
        // If starting with '1', flips = total length - startZero
        int startOne = n - startZero;
        // Return minimum flips of both patterns
        return Math.min(startOne, startZero);
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
