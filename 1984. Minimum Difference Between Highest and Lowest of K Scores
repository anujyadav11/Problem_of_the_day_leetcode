/*********************************************** JAVA **************************************************/

Optimal Solution - Sort the array and use a sliding window of size k to find the minimum possible difference.
                    Once sorted, the minimum difference must come from k consecutive elements.

class Solution {
    public int minimumDifference(int[] nums, int k) {
        // Total number of elements
        int n = nums.length;
        // Initialize answer with a very large value
        int minDiff = Integer.MAX_VALUE;
        // Sort the array so that elements are in ascending order
        Arrays.sort(nums);
        // Sliding window pointers
        int i = 0;            // start of window
        int j = k - 1;        // end of window
        // Move the window while it stays within bounds
        while (j < n) {
            // Minimum element in current window
            int minEl = nums[i];
            // Maximum element in current window
            int maxEl = nums[j];
            // Update the minimum difference
            minDiff = Math.min(minDiff, maxEl - minEl);
            // Slide the window forward
            i++;
            j++;
        }
        // Return the smallest difference found
        return minDiff;
    }
}

Time Complexity :- O(n log n).
Space Complexity :- O(1).
