/*********************************************** JAVA **************************************************/

// Optimal Solution - Use binary search on time and quadratic math to compute how much height workers can reduce within a given time.

class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        // Binary search range for minimum time required
        long left = 1;                 // Minimum possible time
        long right = (long) 1e18;      // Large upper bound (safe maximum)
        // Binary search on time
        while (left < right) {
            // Midpoint of current time range
            long mid = left + (right - left) / 2;
            // Check if workers can finish within 'mid' seconds
            if (canFinish(mountainHeight, workerTimes, mid)) {
                // If possible, try smaller time
                right = mid;
            } else {
                // Otherwise increase time
                left = mid + 1;
            }
        }
        // Minimum time required
        return left;
    }
    // Helper function to check if mountain can be cleared in 'maxTime'
    private boolean canFinish(int mountainHeight, int[] workerTimes, long maxTime) {
        long totalReducedHeight = 0; // Total height reduced by all workers
        for (int time : workerTimes) {
            /*
            Each worker reduces height like:
            time * (1 + 2 + 3 + ... + k)
            Total time = time * k*(k+1)/2
            We solve:
            time * k*(k+1)/2 <= maxTime
            */
            long maxHeight =
                (long) (-1 + Math.sqrt(1 + 8 * maxTime / time)) / 2;
            // Add contribution of this worker
            totalReducedHeight += maxHeight;
            // Early stop if mountain is already cleared
            if (totalReducedHeight >= mountainHeight) {
                return true;
            }
        }
        // Check if total work is enough
        return totalReducedHeight >= mountainHeight;
    }
}

// Time Complexity :- O(W × log(1018)).
// Space Complexity :- O(1).
