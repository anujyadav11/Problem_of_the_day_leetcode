/*********************************************** JAVA **************************************************/

// Optimal Solution - Used a sliding window with a monotonic deque to maintain minimum modification costs and count valid subarrays in linear time.
/* "I use a sliding window combined with a monotonic deque. I traverse from right to left and maintain the minimum number of increment operations needed to make the current window non-decreasing. 
    The deque maintains the relevant maximum-value blocks, allowing me to update the cost efficiently. Whenever the cost exceeds k, I shrink the window from the right. 
    For each left endpoint, all windows ending within the valid boundary are valid, so I add their count to the answer." */

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        // Monotonic deque stores indices in decreasing value order.
        Deque<Integer> dq = new ArrayDeque<>();
        // Total operations required to make the current window non-decreasing.
        long cost = 0;
        // Total number of valid subarrays.
        long answer = 0;
        // Right boundary of the current sliding window.
        int right = n - 1;
        // Expand the window from right to left.
        for (int left = n - 1; left >= 0; left--) {
            // Remove elements smaller than nums[left].
            // These elements must be raised to nums[left].
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[left]) {
                int j = dq.removeLast();
                // Find the next greater element's index,
                // or use right + 1 if no such element exists.
                int boundary = dq.isEmpty() ? right + 1 : dq.peekLast();
                // Calculate the cost of raising the affected elements.
                cost += (long) (boundary - j) * (nums[left] - nums[j]);
            }
            // Add the current index to the deque.
            dq.addLast(left);
            // Shrink the window while its cost exceeds k.
            while (cost > k) {
                // Remove the contribution of the rightmost position.
                if (dq.peekFirst() == right) {
                    dq.removeFirst();
                } else {
                    // The rightmost element contributes to the block
                    // represented by the front deque index.
                    cost -= (long) nums[dq.peekFirst()] - nums[right];
                }
                right--;
            }
            // Every subarray [left, right], [left, right-1], ...
            // is valid because the window cost is at most k.
            answer += right - left + 1;
        }
        return answer;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
