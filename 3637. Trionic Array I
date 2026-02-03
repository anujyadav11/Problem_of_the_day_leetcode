/*********************************************** JAVA **************************************************/

Optimal Solution - Greedy one-pass validation of a trionic array pattern: increasing, decreasing, then increasing again.
                   Simulate the three required monotonic phases in order and ensure the pointer reaches the end without breaking strict conditions.

class Solution {
    public boolean isTrionic(int[] nums) {
        // Length of the array
        int n = nums.length;
        // Pointer to traverse the array
        int i = 0;
        // First phase: strictly increasing sequence
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }
        // If no increase happened or reached the end too early
        if (i == 0 || i == n - 1)
            return false;
        // Second phase: strictly decreasing sequence
        while (i + 1 < n && nums[i] > nums[i + 1]) {
            i++;
        }
        // If no final increasing phase exists
        if (i == n - 1)
            return false;
        // Third phase: strictly increasing sequence again
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }
        // Valid trionic array only if fully traversed
        return i == n - 1;
    }
}

Time Complexity :- O(N).
Space Complexity :- O(1).
