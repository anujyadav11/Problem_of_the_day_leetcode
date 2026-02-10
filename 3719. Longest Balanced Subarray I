/*********************************************** JAVA **************************************************/

Optimal Solution - Brute-force solution that checks all subarrays and compares distinct even and odd counts using hash sets.
                   Fix a starting index, expand the subarray, and track distinct evens and odds; whenever their counts match, update the answer.

class Solution {
    public int longestBalanced(int[] nums) {
        // Length of the array
        int n = nums.length;
        // Variable to store the maximum balanced subarray length
        int maxLen = 0;
        // Try every possible starting index
        for (int i = 0; i < n; i++) {
            // Set to store distinct even numbers in current subarray
            Set<Integer> even = new HashSet<>();
            // Set to store distinct odd numbers in current subarray
            Set<Integer> odd = new HashSet<>();
            // Extend subarray from index i to j
            for (int j = i; j < n; j++) {
                // Classify current number as even or odd
                if (nums[j] % 2 == 0) {
                    even.add(nums[j]);
                } else {
                    odd.add(nums[j]);
                }
                // If counts of distinct evens and odds are equal
                if (even.size() == odd.size()) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        // Return the maximum balanced subarray length
        return maxLen;
    }
}

Time Complexity :- O(n ^ 2).
Space Complexity :- O(n).
