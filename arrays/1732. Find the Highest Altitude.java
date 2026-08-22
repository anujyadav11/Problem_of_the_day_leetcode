/*********************************************** JAVA **************************************************/

// Optimal Solution - Use a running prefix sum to track current altitude and record the maximum altitude reached.
/* “I treat the gains array as altitude changes, maintain a running altitude using a prefix sum, and keep track of the highest altitude encountered during traversal.” */

class Solution {
    public int largestAltitude(int[] gain) {
        // Highest altitude reached so far
        int maxAltitude = 0;
        // Current altitude (starts at 0)
        int currentAltitude = 0;
        // Process altitude gains/losses
        for (int altitudeChange : gain) {
            // Update current altitude
            currentAltitude += altitudeChange;
            // Track the highest altitude reached
            maxAltitude = Math.max(maxAltitude, currentAltitude);
        }
        return maxAltitude;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
