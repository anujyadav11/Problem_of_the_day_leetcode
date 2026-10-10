/*********************************************** JAVA **************************************************/

// Optimal Solution - Minimise the sum of squared differences using greedy reduction and frequency counting in O(n + M) time and O(M) space.
/* “I count the frequencies of absolute differences and greedily reduce the largest differences first, since reducing a larger value gives a greater reduction in its square. 
    Frequency counting allows me to process groups of equal differences efficiently without sorting the entire array.” */

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long minSumSquare = 0;
        int[] diffs = new int[100_001];
        long totalDiff = 0;
        long kSum = (long) k1 + k2;
        int currentDiff;
        int maxDiff = 0;
        // Count the frequency of each absolute difference.
        for (int i = 0; i < nums1.length; i++) {
            currentDiff = Math.abs(nums1[i] - nums2[i]);

            if (currentDiff > 0) {
                totalDiff += currentDiff;
                diffs[currentDiff]++;
                maxDiff = Math.max(maxDiff, currentDiff);
            }
        }
        // If we can eliminate every difference, the minimum sum is zero.
        if (totalDiff <= kSum) {
            return 0;
        }
        // Greedily reduce the largest differences first.
        // Moving a difference from i to i - 1 uses one operation.
        for (int i = maxDiff; i > 0 && kSum > 0; i--) {
            if (diffs[i] > 0) {
                // If operations are insufficient to reduce the entire group,
                // reduce only kSum elements by one.
                if (diffs[i] >= kSum) {
                    diffs[i] -= (int) kSum;
                    diffs[i - 1] += (int) kSum;
                    kSum = 0;
                } else {
                    // Reduce every difference in this group by one.
                    diffs[i - 1] += diffs[i];
                    kSum -= diffs[i];
                    diffs[i] = 0;
                }
            }
        }
        // Calculate the sum of squared differences using the final frequencies.
        for (int i = 0; i <= maxDiff; i++) {
            if (diffs[i] > 0) {
                minSumSquare += (long) i * i * diffs[i];
            }
        }
        return minSumSquare;
    }
}

// Time Complexity :- O(n + m).
// Space Complexity :- O(m).
