/*********************************************** JAVA **************************************************/

// Optimal Solution - Used single-pass array traversal with two counters to track increasing and decreasing subarray lengths in O(n) time and O(1) space.
/* "I solve this problem using a single-pass approach. I maintain two counters representing the lengths of the current strictly increasing and strictly decreasing subarrays. 
    When the current element continues a sequence, I increment its counter and reset the other. If two adjacent elements are equal, both counters reset to one. I update the maximum length at every iteration." */

class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        // A single element is always monotonic.
        int inc = 1;
        int dec = 1;
        int res = 1;
        for (int i = 1; i < n; i++) {
            // Check for a strictly increasing sequence.
            if (nums[i] > nums[i - 1]) {
                inc++;
                dec = 1;
            }
            // Check for a strictly decreasing sequence.
            else if (nums[i] < nums[i - 1]) {
                dec++;
                inc = 1;
            }
            // Equal elements break both monotonic sequences.
            else {
                inc = 1;
                dec = 1;
            }
            // Update the maximum monotonic subarray length.
            res = Math.max(res, Math.max(inc, dec));
        }
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
