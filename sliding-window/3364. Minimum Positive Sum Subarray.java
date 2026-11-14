/*********************************************** JAVA **************************************************/

// Optimal Solution - Used brute-force enumeration with a running prefix sum to find the minimum positive subarray sum within the specified length range.
/* "I use two nested loops to enumerate all possible starting positions and subarray lengths. For each starting position, I maintain a running sum while extending the subarray up to the maximum allowed length. 
    Whenever the subarray length falls within the given range and its sum is positive, I update the minimum answer." */

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        // Store the minimum valid positive subarray sum.
        long res = Long.MAX_VALUE;
        // Convert to an array for O(1) indexed access.
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums.get(i);
        }
        // Try every possible starting position.
        for (int i = 0; i < n; i++) {
            long sum = 0;
            // Explore subarrays with length at most r.
            for (int j = i; j < n && j - i + 1 <= r; j++) {
                // Extend the current subarray.
                sum += arr[j];
                int len = j - i + 1;
                // Check whether the length is valid
                // and the sum is strictly positive.
                if (len >= l && sum > 0) {
                    res = Math.min(res, sum);
                }
            }
        }
        // Return -1 if no valid subarray exists.
        return res == Long.MAX_VALUE ? -1 : (int) res;
    }
}

// Time Complexity :- O(n^2).
// Space Complexity :- O(n).
