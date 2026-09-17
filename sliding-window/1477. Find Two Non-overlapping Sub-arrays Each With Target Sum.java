/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds the minimum total length of two non-overlapping target-sum subarrays using sliding window and prefix minimum DP.
/* “I use a sliding window to find every subarray whose sum equals the target. Since the array contains positive numbers, 
    when the current sum exceeds the target, I move the left pointer forward and subtract those elements. */

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int i = 0;
        int j = 0;
        int currSum = 0;
        // minBest[j] = minimum valid subarray length
        // found in arr[0...j]
        int[] minBest = new int[n];
        int bestMinLen = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        while (j < n) {
            // Expand the window using the right pointer
            currSum += arr[j];
            // Shrink from the left if sum becomes too large
            while (i < j && currSum > target) {
                currSum -= arr[i++];
            }
            // Found a subarray with sum == target
            if (currSum == target) {
                int len = j - i + 1;
                // Combine with the best non-overlapping
                // subarray ending before this one starts
                if (i > 0 && minBest[i - 1] != Integer.MAX_VALUE) {
                    res = Math.min(res, len + minBest[i - 1]);
                }
                bestMinLen = Math.min(bestMinLen, len);
            }
            // Store the best subarray found so far
            minBest[j] = bestMinLen;
            j++;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

// Time Complexity :- O(n).    — each element enters and leaves the sliding window at most once.
// Space Complexity :- O(n). - minBest Array.
