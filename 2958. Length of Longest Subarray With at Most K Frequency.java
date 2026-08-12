/*********************************************** JAVA **************************************************/

// Optimal Solution - Find the longest subarray where every element appears at most k times using a sliding window and frequency map.
/* “I maintain a sliding window with frequencies. I expand the right pointer and shrink from the left whenever the newly added value exceeds frequency k. 
    Since both pointers move only forward, the solution runs in O(n).” */

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        // Stores frequency of each number in current window
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0;
        int maxLen = 0;
        for (int end = 0; end < nums.length; end++) {
            // Add current element to window
            map.put(nums[end], map.getOrDefault(nums[end], 0) + 1);
            // Shrink window if current element appears more than k times
            while (map.get(nums[end]) > k) {
                map.put(nums[start], map.get(nums[start]) - 1);
                start++;
            }
            // Update maximum valid window length
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}

// Time Complexity :- O(n). — each element enters and leaves the window at most once.
// Space Complexity :- O(n). — HashMap can store up to n distinct values.
