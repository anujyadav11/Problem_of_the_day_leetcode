/*********************************************** JAVA **************************************************/

// Optimal Solution - Compute minimum circular distance to the target by checking all occurrences.
/* “I compute both direct and wrap-around distances and take the minimum for each occurrence of the target.” */

class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length; // Total number of words
        int res = Integer.MAX_VALUE; // Stores minimum distance
        // Traverse all indices
        for (int i = 0; i < n; i++) {
            // If target found
            if (words[i].equals(target)) {
                // Direct distance
                int straightDist = Math.abs(i - startIndex);
                // Circular distance (wrap-around)
                int circularDist = n - straightDist;
                // Take minimum of both directions
                res = Math.min(res, Math.min(straightDist, circularDist));
            }
        }
        // If target not found
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
