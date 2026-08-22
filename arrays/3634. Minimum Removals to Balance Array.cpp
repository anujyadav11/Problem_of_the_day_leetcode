/*********************************************** JAVA **************************************************/

Optimal Solution - Two-pointer greedy solution that maximizes a valid window after sorting to minimize removals.
                   Sort the array and find the largest subarray where the max-to-min ratio satisfies the constraint; everything else must be removed.

class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        // Sort the array
        Arrays.sort(nums);
        int i = 0;
        int L = 1;
        // Two-pointer sliding window
        for (int j = 0; j < n; j++) {
            // Shrink window while condition is violated
            while (i < j && nums[j] > (long) k * nums[i]) {
                i++;
            }
            // Update longest valid window length
            L = Math.max(L, j - i + 1);
        }
        // Minimum removals = total elements - largest valid window
        return n - L;
    }
}

Time Complexity :- O(n log n).
Space Complexity :- O(1).
